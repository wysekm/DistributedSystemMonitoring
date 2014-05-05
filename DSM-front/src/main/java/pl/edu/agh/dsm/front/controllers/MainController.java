package pl.edu.agh.dsm.front.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Sakushu on 2014-04-19.
 */

@RestController
public class MainController {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ModelAndView getIndex(Model model) {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        return "main";
    }
}
