package iq.ven.workflow.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String start(Model model) {
        return "index";
    }

    @Secured("ROLE_ANONYMOUS")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String logIn(Model model) {
        return "login";
    }

}