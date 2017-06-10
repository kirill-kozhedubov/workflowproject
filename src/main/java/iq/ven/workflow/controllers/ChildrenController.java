package iq.ven.workflow.controllers;

import iq.ven.workflow.models.requests.ChildCreationRequest;
import iq.ven.workflow.models.requests.ChildrenSearchRequest;
import iq.ven.workflow.models.requests.ParentRequestWrapper;
import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/children")
public class ChildrenController {
    private static final Logger LOGGER = Logger.getLogger(ChildrenController.class);


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createChild(Model model, @ModelAttribute("child") ChildCreationRequest childRequest) {

        return "CreateChild";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String saveChild(@ModelAttribute("child") ChildCreationRequest childRequest, HttpServletRequest request) {
        LOGGER.info("Got from client " + childRequest);
        return "successful";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/add-parents/{id}", method = RequestMethod.GET)
    public String addParents(Model model) {
        return "AddParentsToChild";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/add-parents-post/{id}", method = RequestMethod.POST)
    public String addParentsPost(ParentRequestWrapper parentRequestWrapper) {
        return "AddParentsToChild";
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
