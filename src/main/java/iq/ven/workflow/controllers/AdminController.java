package iq.ven.workflow.controllers;

import iq.ven.workflow.controllers.requests.UserCreateRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public String createUser(Model model) {
        return "CreateUser";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/user/save-user", method = RequestMethod.GET)
    @ResponseBody
    public String createUserPost(Model model, UserCreateRequest userCreateRequest) {
        return "successful";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String viewUser(Model model) {
        return "ViewUser";
    }


}
