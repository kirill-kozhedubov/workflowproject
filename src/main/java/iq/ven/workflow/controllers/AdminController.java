package iq.ven.workflow.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/add-child", method = RequestMethod.GET)
    public String addChild(Model model) {
        return "addChild";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/children", method = RequestMethod.GET)
    public String viewAndSearchChildren(Model model) {
        return "viewAndSearchChildren";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/user-create", method = RequestMethod.GET)
    public String createUser(Model model) {
        return "createUser";
    }

}
