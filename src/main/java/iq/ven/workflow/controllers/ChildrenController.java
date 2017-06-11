package iq.ven.workflow.controllers;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.DateProducer;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.text.TextProducer;
import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.models.Child;
import iq.ven.workflow.models.Districts;
import iq.ven.workflow.models.Parent;
import iq.ven.workflow.models.ParentTypes;
import iq.ven.workflow.models.requests.ChildCreationRequest;
import iq.ven.workflow.models.requests.ChildrenSearchRequest;
import iq.ven.workflow.models.requests.ParentRequest;
import iq.ven.workflow.models.requests.ParentRequestWrapper;
import iq.ven.workflow.services.ObjectFromRequestBuilder;
import iq.ven.workflow.services.ValidationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Controller
@RequestMapping("/children")
public class ChildrenController {
    private static final Logger LOGGER = Logger.getLogger(ChildrenController.class);
    @Autowired
    ChildrenDAO childrenDAO;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @ModelAttribute("child")
    ChildCreationRequest getChildCreationRequest() {
        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        TextProducer textProducer = fairy.textProducer();
        DateProducer dateProducer = fairy.dateProducer();
        ChildCreationRequest childReq = new ChildCreationRequest();
        childReq.setBasicFirstName(person.getFirstName());
        childReq.setBasicLastName(person.getLastName());
        childReq.setBasicMiddleName(person.getMiddleName());
        childReq.setBasicDateOfBirth(person.getDateOfBirth().toDate());
        childReq.setClarifiedFirstName(person.getFirstName());
        childReq.setClarifiedLastName(person.getLastName());
        childReq.setClarifiedMiddleName(person.getMiddleName());
        childReq.setClarifiedDateOfBirth(person.getDateOfBirth().toDate());
        childReq.setPersonalRecordCode(textProducer.word(1));
        childReq.setEnteredDate(dateProducer.randomDateBetweenYears(2000, 2015).toDate());
        childReq.setRetireDate(dateProducer.randomDateBetweenYears(2000, 2015).toDate());
        childReq.setDistrict(BigInteger.ONE);
        childReq.setAddress(person.getAddress().getAddressLine1());
        childReq.setBirthPlace(textProducer.word(1));
        childReq.setOccupation(textProducer.word(1));
        childReq.setFromCame(textProducer.word(1));
        childReq.setWhenCame(dateProducer.randomDateBetweenYears(2000, 2015).toDate());
        childReq.setDetentionAddress(person.getAddress().getAddressLine1());
        childReq.setDetentionDate(dateProducer.randomDateBetweenYears(2000, 2007).toDate());
        childReq.setDetainedFor(textProducer.paragraph(3));
        childReq.setDetainedBy(person.getFullName());
        childReq.setJudgedOrDetainedInfo(textProducer.paragraph(3));
        childReq.setNotes(textProducer.paragraph(3));
        childReq.setDutyOfficer(person.getFullName());


        return childReq;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createChild(Model model) {
        getChildCreationRequest();
        model.addAttribute("districts", Arrays.asList(Districts.values()));
        return "CreateChild";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveChild(@ModelAttribute("child") ChildCreationRequest childRequest,
                            // @RequestParam("file") MultipartFile file,
                            HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            // LOGGER.info("Got from client " + file);
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
    public String addParents(@ModelAttribute("parents") ParentRequestWrapper parentRequest, Model model, @PathVariable BigInteger id) {
        model.addAttribute("parentTypes", Arrays.asList(ParentTypes.values()));
        model.addAttribute("childId", id);
        return "AddParentsToChild";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/add-parents-post", method = RequestMethod.POST)
    public String addParentsPost(@ModelAttribute("parents") ParentRequestWrapper parentRequest, RedirectAttributes redirectAttributes) {

        boolean parentInserted = true;
        for (ParentRequest parentRequestItem : parentRequest.getParentRequestList()) {
            Parent parent = ObjectFromRequestBuilder.buildObjectFromRequest(parentRequestItem);
            if (ValidationService.validate(parent)) {
                LOGGER.info("parent got saved " + parentRequestItem);
                childrenDAO.addParentToChild(parentRequest.getChildId(), parent);
            } else {
                LOGGER.info("parent not saved " + parentRequestItem);
            }
            // LOGGER.info("got parent from client " + parent + " parent id " + parentRequest.getChildId());
        }
        return "redirect:/children/upload-files/" + parentRequest.getChildId();
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/upload-files/{id}", method = RequestMethod.GET)
    public String uploadChildrenFiles(Model model, @PathVariable BigInteger id) {
        model.addAttribute("childId", id);
        return "UploadChildrenFiles";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/upload-files-post", method = RequestMethod.POST)
    public String uploadChildrenFilesPost(@RequestParam(value = "photo", required = false) MultipartFile photo,
                                          @RequestParam(value = "file", required = false) MultipartFile[] file,
                                          @RequestParam("childId") BigInteger childId) {

        LOGGER.info("childId " + childId);
        LOGGER.info("photo " + photo.getOriginalFilename() + "   size:" + photo.getSize());
        for (MultipartFile multipartFile : file) {

            LOGGER.info("file " + multipartFile.getOriginalFilename() + "   size:" + multipartFile.getSize());
        }

        if (photo.getSize() > 0) {
            childrenDAO.updateChildsPhoto(childId, photo);
        }
        for (MultipartFile multipartFile : file) {
            if (multipartFile.getSize() > 0) {
                childrenDAO.addFileToChild(childId, multipartFile);
            }
        }

        return "redirect:/children/child/" + childId;
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
