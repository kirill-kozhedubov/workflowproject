package iq.ven.workflow.controllers;

import iq.ven.workflow.controllers.requests.ChildCreationRequest;
import iq.ven.workflow.controllers.requests.ChildrenSearchRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/children")
public class ChildrenController {

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createChild(Model model) {
        return "CreateChild";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String saveChild(Model model, ChildCreationRequest childRequest) {
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
