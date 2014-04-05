package pl.edu.agh.dsm.rest;

import pl.edu.agh.dsm.common.dto.Measurement;
import pl.edu.agh.dsm.datastore.DataStoreException;
import pl.edu.agh.dsm.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
public class MeasurementController {
    private static final Logger log = LoggerFactory.getLogger(MeasurementController.class);
    private final MeasurementController controller = methodOn(MeasurementController.class);

    @Resource
    private DataService dataService;

    @RequestMapping(value = "/measurements", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Measurement>> allMeasurements() {
        log.trace("GET request for /measurements");
        try {
            List<Measurement> measurementList = dataService.getAllMeasurements();
            for (Measurement m : measurementList) {
                m.add(linkTo(controller.lastValueForMetricAndResource(m.getResource(), m.getMetric())).withRel("last"));
                m.add(linkTo(controller.allValuesForMetricAndResource(m.getResource(), m.getMetric())).withRel("all"));
            }
            return new ResponseEntity<>(measurementList, HttpStatus.OK);
        } catch (DataStoreException e) {
            return new ResponseEntity<>(e.getErrorCode());
        }
    }

    @RequestMapping(value = "/measurements/{resource}/{metric}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpEntity<List<Measurement>> allValuesForMetricAndResource(@PathVariable String resource, @PathVariable String metric) {
        log.trace("GET request for /measurements/" + resource + "/" + metric);
        try {
            List<Measurement> measurementList = dataService.getAllValuesForSensor(resource, metric);
            for (Measurement m : measurementList) {
                m.add(linkTo(controller.lastValueForMetricAndResource(m.getResource(), m.getMetric())).withRel("last"));
                m.add(linkTo(controller.allValuesForMetricAndResource(m.getResource(), m.getMetric())).withRel("all"));
            }
            return new ResponseEntity<>(measurementList, HttpStatus.OK);
        } catch (DataStoreException e) {
            return new ResponseEntity<>(e.getErrorCode());
        }
    }


    @RequestMapping(value = "/measurements/{resource}/{metric}/last", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpEntity<Measurement> lastValueForMetricAndResource(@PathVariable String resource, @PathVariable String metric) {
        log.trace("GET request for /measurements/" + resource + "/" + metric);
        try {
            Measurement m = dataService.getLastValueForSensor(resource, metric);
            m.add(linkTo(controller.lastValueForMetricAndResource(m.getResource(), m.getMetric())).withRel("last"));
            m.add(linkTo(controller.allValuesForMetricAndResource(m.getResource(), m.getMetric())).withRel("all"));
            return new ResponseEntity<>(m, HttpStatus.OK);
        } catch (DataStoreException e) {
            return new ResponseEntity<>(e.getErrorCode());
        }
    }

}
