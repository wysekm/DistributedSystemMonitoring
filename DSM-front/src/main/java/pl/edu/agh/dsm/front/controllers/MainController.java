package pl.edu.agh.dsm.front.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Sakushu on 2014-04-19.
 */

@Controller
@RequestMapping("/")
public class MainController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        return "main";
    }
}
