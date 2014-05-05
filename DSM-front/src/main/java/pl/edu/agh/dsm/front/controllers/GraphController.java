package pl.edu.agh.dsm.front.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Sakushu on 2014-04-26.
 */

@Controller
@RequestMapping("/graph/")
public class GraphController {

    @Deprecated
    @RequestMapping(method = RequestMethod.GET)
    public String get() {
        return "graph";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView post(@ModelAttribute("rsc") ResourcesController.RST_KT_MSL_list rsc) {
        ModelAndView mv = new ModelAndView("plot");
        ResourcesController.RST_KT_MSL_list rsc_nd = new ResourcesController.RST_KT_MSL_list();
        for (ResourcesController.RST_KT_MSL_cls rm : rsc.getList()) {
            if (!rm.getSelected())
                continue;
            rsc_nd.getList().add(rm);
        }
        mv.addObject("rsc", rsc_nd);
        return mv;
    }
}
