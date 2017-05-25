package iq.ven.workflow.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/u")
public class UserController {

    @Secured("ROLE_REGULAR_USER")
    @RequestMapping(method = RequestMethod.GET)
    public String userDashboard(Model model) {
        return "Dashboard";
    }

    @Secured("ROLE_REGULAR_USER")
    @RequestMapping(value = "/add-file", method = RequestMethod.GET)
    public String addFile(Model model) {
        return "addFile";
    }

    @Secured("ROLE_REGULAR_USER")
    @RequestMapping(value = "/share-file", method = RequestMethod.GET)
    public String shareFile(Model model) {
        return "shareFile";
    }

    @Secured("ROLE_REGULAR_USER")
    @RequestMapping(value = "/download-file", method = RequestMethod.GET)
    public String downloadFile(Model model) {
        return "addChild";
    }

}
