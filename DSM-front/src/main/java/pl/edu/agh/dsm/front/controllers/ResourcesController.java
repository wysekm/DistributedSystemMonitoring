package pl.edu.agh.dsm.front.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.agh.dsm.front.system.RMap;
import pl.edu.agh.dsm.front.system.RestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sakushu on 2014-04-26.
 */
@Controller
@RequestMapping("/res/")
public class ResourcesController {
    @Autowired
    RestClient RST_MT_MSG;

    @Autowired
    RestClient RST_KT_RSL;

    @Autowired
    RestClient RST_KT_MSL;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getResources() {
        final ModelAndView mv = new ModelAndView("resources_list");
        final ResourceNamesSet resources = new ResourceNamesSet();

        RST_KT_RSL.send(null, new RestClient.RestCallback() {
            @Override
            public void onCallback(HashMap data) {
                RMap d = new RMap(data);
                List<HashMap> res_x = d.getAsList("resources");
                for (HashMap rx : res_x) {
                    RMap k = new RMap(rx);
                    resources.getRscSet().add(new ResourcesNames(k.getString("name"), k.step("_links").step("measurements").getString("href")));
                }
            }
        });

        mv.addObject("measurements", resources);
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, value = "measurements/")
    public ModelAndView getMeasurements(@ModelAttribute("measurements") ResourceNamesSet resourcesNames) {
        final ModelAndView mv = new ModelAndView("resources");
        final RST_KT_MSL_list resources = new RST_KT_MSL_list();
        for (ResourcesNames i : resourcesNames.getRscSet()) {
            if (!i.getSelected())
                continue;
            HashMap<String, String> params = new HashMap<>();
            params.put("resource", i.getsName());

            RST_KT_MSL.send(null, new RestClient.RestCallback() {
                @Override
                public void onCallback(HashMap data) {
                    RMap r = new RMap(data);
                    List<HashMap> rx = r.getAsList("measurements");
                    for (HashMap d : rx) {
                        RMap dta = new RMap(d);
                        RST_KT_MSL_cls item = new RST_KT_MSL_cls();
                        item.setDetails(dta.step("_links").step("details").getString("href"));
                        item.setMetric(dta.getString("metric"));
                        item.setResource(dta.getString("resource"));
                        item.setSelf(dta.step("_links").step("self").getString("href"));
                        resources.getList().add(item);
                    }
                }
            }, params);
        }
        mv.addObject("measurements", resources);
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "measurements/")
    public ModelAndView getMeasurementsGET() {
        final ModelAndView mv = new ModelAndView("resources");
        final RST_KT_MSL_list resources = new RST_KT_MSL_list();

        RST_KT_MSL.send(null, new RestClient.RestCallback() {
            @Override
            public void onCallback(HashMap data) {
                RMap r = new RMap(data);
                List<HashMap> rx = r.getAsList("measurements");
                for (HashMap d : rx) {
                    RMap dta = new RMap(d);
                    RST_KT_MSL_cls item = new RST_KT_MSL_cls();
                    item.setDetails(dta.step("_links").step("details").getString("href"));
                    item.setMetric(dta.getString("metric"));
                    item.setResource(dta.getString("resource"));
                    item.setSelf(dta.step("_links").step("self").getString("href"));
                    resources.getList().add(item);
                }
            }
        });

        mv.addObject("measurements", resources);
        return mv;
    }

    @RequestMapping(value = "measurements/search/", method = RequestMethod.GET)
    public ModelAndView getSearchMeasurements() {
        ModelAndView mv = new ModelAndView("search");
        return mv;
    }

    @RequestMapping(value = "measurements/search/", method = RequestMethod.POST)
    public ModelAndView getSearchMeasurementsResults(@RequestParam("metric") String metric, @RequestParam("resource") String resource) {
        HashMap<String, String> params = new HashMap<>();
        params.put("resource", resource);
        params.put("metric", metric);

        final ModelAndView mv = new ModelAndView("resources");
        final RST_KT_MSL_list resources = new RST_KT_MSL_list();

        RST_KT_MSL.send(null, new RestClient.RestCallback() {
            @Override
            public void onCallback(HashMap data) {
                RMap r = new RMap(data);
                List<HashMap> rx = r.getAsList("measurements");
                for (HashMap d : rx) {
                    RMap dta = new RMap(d);
                    RST_KT_MSL_cls item = new RST_KT_MSL_cls();
                    item.setDetails(dta.step("_links").step("details").getString("href"));
                    item.setMetric(dta.getString("metric"));
                    item.setResource(dta.getString("resource"));
                    item.setSelf(dta.step("_links").step("self").getString("href"));
                    resources.getList().add(item);
                }
            }
        }, params);
        mv.addObject("measurements", resources);
        return mv;
    }

    static public class RST_KT_MSL_list {
        List<RST_KT_MSL_cls> list = new ArrayList<RST_KT_MSL_cls>();

        public List<RST_KT_MSL_cls> getList() {
            return list;
        }

        public void setList(List<RST_KT_MSL_cls> lst) {
            this.list = lst;
        }
    }

    static public class RST_KT_MSL_cls {
        private String resource;
        private String metric;
        private String self;
        private String details;
        private Boolean selected = false;

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

        public String getResource() {
            return resource;
        }

        public void setResource(String resource) {
            this.resource = resource;
        }

        public String getMetric() {
            return metric;
        }

        public void setMetric(String metric) {
            this.metric = metric;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public Boolean getSelected() {
            return selected;
        }

        public void setSelected(Boolean selected) {
            this.selected = selected;
        }
    }

    static public class ResourcesNames {
        private String sName;
        private String measurements;
        private Boolean selected = false;

        public ResourcesNames() {
        }

        public ResourcesNames(String name, String measurements) {
            this.sName = name;
            this.measurements = measurements;
        }

        public String getMeasurements() {
            return measurements;
        }

        public void setMeasurements(String measurements) {
            this.measurements = measurements;
        }

        public Boolean getSelected() {
            return selected;
        }

        public void setSelected(Boolean selected) {
            this.selected = selected;
        }

        public String getsName() {
            return sName;
        }

        public void setsName(String sName) {
            this.sName = sName;
        }
    }

    static public class ResourceNamesSet {
        List<ResourcesNames> rscSet = new ArrayList<ResourcesNames>();

        public List<ResourcesNames> getRscSet() {
            return rscSet;
        }

        public void setRscSet(List<ResourcesNames> rscSet) {
            this.rscSet = rscSet;
        }
    }
}
