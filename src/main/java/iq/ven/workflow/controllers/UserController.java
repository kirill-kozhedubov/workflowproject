package iq.ven.workflow.controllers;

import iq.ven.workflow.controllers.requests.CreateFileRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/u")
public class UserController {

    @Secured("ROLE_REGULAR_USER")
    @RequestMapping(method = RequestMethod.GET)
    public String userDashboard(Model model) {
        return "Dashboard";
    }

    @Secured("ROLE_REGULAR_USER")
    @RequestMapping(value = "/file/create", method = RequestMethod.GET)
    public String addFile(Model model, CreateFileRequest createFileRequest) {
        return "AddFile";
    }

    @Secured("ROLE_REGULAR_USER")
    @RequestMapping(value = "/file/share/{fileId}", method = RequestMethod.GET)
    public String shareFile(Model model) {
        return "ShareFile";
    }

    @Secured("ROLE_REGULAR_USER")
    @RequestMapping(value = "/file/{fileId}", method = RequestMethod.GET)
    public String viewFileProject(Model model) {
        return "FileProject";
    }

    @Secured("ROLE_REGULAR_USER")
    @RequestMapping(value = "/download/{fileId}", method = RequestMethod.GET)
    @ResponseBody
    public String downloadFile(Model model) {
        return "file";
    }

}
