package pl.edu.agh.dsm.front.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.agh.dsm.front.models.ResourceModel;
import pl.edu.agh.dsm.front.models.ResourceModelSet;

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
    public ModelAndView post(@ModelAttribute("rsc") ResourceModelSet rsc) {
        ModelAndView mv = new ModelAndView("plot");
        ResourceModelSet rsc_nd = new ResourceModelSet();
        for (ResourceModel rm : rsc.getRscSet()) {
            if (rm.getSelected())
                rsc_nd.getRscSet().add(rm);
        }
        mv.addObject("rsc", rsc_nd);
        return mv;
    }
}
