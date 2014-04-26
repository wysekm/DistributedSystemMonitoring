package pl.edu.agh.dsm.front.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.agh.dsm.front.models.UserModel;

/**
 * Created by Sakushu on 2014-04-24.
 */

@Controller("/login/")
public class LoginController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("user", new UserModel());
        return mv;
    }

}
