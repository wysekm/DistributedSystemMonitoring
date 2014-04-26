package pl.edu.agh.dsm.front.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.agh.dsm.front.interfaces.HasName;
import pl.edu.agh.dsm.front.models.MonitorsModel;
import pl.edu.agh.dsm.front.models.SearchModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sakushu on 2014-04-26.
 */

@Controller
@RequestMapping("/rsc/")
public class SearchController {
    private List<MonitorsModel> models = new ArrayList<MonitorsModel>();

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getSearch(){
        ModelAndView mv = new ModelAndView("search");
        mv.addObject("search", new SearchModel());
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView searchPhrase(@ModelAttribute("search") SearchModel searchModel){
        ModelAndView mv = new ModelAndView("found_rsc");
        mv.addObject("search", searchModel);
        System.out.println(models);
        mv.addObject("rsc", models);
        // setRSClist(...)
        return mv;
    }

    public void setRSClist(List<MonitorsModel> models){
        this.models = models;
    }
}
