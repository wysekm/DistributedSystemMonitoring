package pl.edu.agh.dsm.front.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.agh.dsm.front.models.MetricModel;
import pl.edu.agh.dsm.front.models.MetricModelSet;
import pl.edu.agh.dsm.front.system.RMap;
import pl.edu.agh.dsm.front.system.RestClient;
import pl.edu.agh.dsm.front.system.RestKeys;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sakushu on 2014-04-26.
 */
@Controller
@RequestMapping("/monitors/")
public class MonitorController {
    @Autowired
    RestClient RST_KT_MSL;

    @Autowired
    RestKeys restKeys;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get() {
        final ModelAndView mv = new ModelAndView("monitors");
        final MetricModelSet metrics = new MetricModelSet();
        RST_KT_MSL.send(null, new RestClient.RestCallback() {
            @Override
            public void onCallback(HashMap data) {
//                mv.addObject(restKeys.getMeasurements(), data.get(restKeys.getMeasurements()));
                List<HashMap> msr = (List) (data.get(restKeys.getMeasurements()));
                for (int i = 0; i < msr.size(); i++) {
                    RMap m = new RMap(msr.get(i));
                    MetricModel mt = new MetricModel();
                    mt.setSelected(false);
                    mt.setDetails(m.step("_links").step("details").getString("href"));
                    mt.setSelf(m.step("_links").step("self").getString("href"));
                    mt.setName(m.getString("resource"));
                    mt.setMetric(m.getString("metric"));
                    metrics.getModelSet().add(mt);
                }
            }
        });
        mv.addObject("measurements", metrics);
        return mv;
    }
}
