package pl.edu.agh.dsm.front.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.agh.dsm.front.models.MetricModel;
import pl.edu.agh.dsm.front.models.MetricModelSet;
import pl.edu.agh.dsm.front.models.ResourceModel;
import pl.edu.agh.dsm.front.models.ResourceModelSet;
import pl.edu.agh.dsm.front.system.RMap;
import pl.edu.agh.dsm.front.system.RestClient;

import java.util.HashMap;

/**
 * Created by Sakushu on 2014-04-26.
 */
@Controller
@RequestMapping("/res/")
public class ResourcesController {
    @Autowired
    RestClient rst_mt_msg;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView postResource(@ModelAttribute("measurements") MetricModelSet modelSet) {
        final ModelAndView mv = new ModelAndView("resources");
        final ResourceModelSet resources = new ResourceModelSet();
        for (MetricModel i : modelSet.getModelSet()) {
            if(!i.getSelected())
                continue;
            rst_mt_msg.send(null, new RestClient.RestCallback() {
                @Override
                public void onCallback(HashMap data) {
                    RMap r = new RMap(data);
                    resources.getRscSet().add(
                            new ResourceModel(
                                    false, r.getString("resource"),
                                    r.step("_links").step("data").getString("href"),
                                    r.step("_links").step("self").getString("href"),
                                    r.getString("unit"),
                                    r.getString("metric")
                            )
                    );
                }
            });
        }
        mv.addObject("rsc", resources);
        return mv;
    }
}
