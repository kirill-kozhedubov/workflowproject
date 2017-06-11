package iq.ven.workflow.controllers;

import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.models.Child;
import iq.ven.workflow.models.Districts;
import iq.ven.workflow.models.Parent;
import iq.ven.workflow.models.requests.ChildCreationRequest;
import iq.ven.workflow.models.requests.ChildrenSearchRequest;
import iq.ven.workflow.models.requests.ParentRequest;
import iq.ven.workflow.models.requests.ParentRequestWrapper;
import iq.ven.workflow.services.ObjectFromRequestBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Arrays;

@Controller
@RequestMapping("/children")
public class ChildrenController {
    private static final Logger LOGGER = Logger.getLogger(ChildrenController.class);
    @Autowired
    ChildrenDAO childrenDAO;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createChild(Model model, @ModelAttribute("child") ChildCreationRequest childRequest) {
        model.addAttribute("districts", Arrays.asList(Districts.values()));
        return "CreateChild";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveChild(@ModelAttribute("child") ChildCreationRequest childRequest, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            LOGGER.info("Got from client " + childRequest);
            Child childFromRequest = ObjectFromRequestBuilder.buildObjectFromRequest(childRequest);
            Child child = childrenDAO.saveChildToDB(childFromRequest);
            return "redirect:/children/add-parents/" + child.getChildId();
        } catch (NullPointerException e) {
            LOGGER.info("Null pointer in inserting child " + childRequest, e);
            redirectAttributes.addFlashAttribute("errorMessage", "Особиста справа не була збережена, бо поля були введені не вірно");
            return "redirect:/children/create";
        }

    }


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/add-parents/{id}", method = RequestMethod.GET)
    public String addParents(Model model, @PathVariable BigInteger id) {

        return "AddParentsToChild";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/add-parents-post/{id}", method = RequestMethod.POST)
    public String addParentsPost(@PathVariable BigInteger id, ParentRequestWrapper parentRequestWrapper) {
        for (ParentRequest parentRequest : parentRequestWrapper.getParentRequestList()) {
            Parent parent = ObjectFromRequestBuilder.buildObjectFromRequest(id, parentRequest);
            childrenDAO.addParentToChild(id, parent);
        }

        return "redirect:/children/upload-files/" + id;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/upload-files/{id}", method = RequestMethod.GET)
    public String uploadChildrenFiles(Model model) {
        return "UploadChildrenFiles";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/upload-files-post/{id}", method = RequestMethod.POST)
    public String uploadChildrenFilesPost() {
        return "successful";
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/child/{id}", method = RequestMethod.GET)
    public String childView(Model model) {
        return "child";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewAndSearchChildren(Model model, ChildrenSearchRequest childrenSearchRequest) {
        return "ViewAndSearchChildren";
    }


}
