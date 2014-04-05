package pl.edu.agh.dsm.catalog.rest;

import pl.edu.agh.dsm.catalog.datastore.DataStoreException;
import pl.edu.agh.dsm.catalog.service.CatalogService;
import pl.edu.agh.dsm.common.dto.MeasurementInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
public class CatalogMeasurementController {
    private static final Logger log = LoggerFactory.getLogger(CatalogMeasurementController.class);
    private final CatalogMeasurementController controller = methodOn(CatalogMeasurementController.class);

    @Resource
    private CatalogService catalogService;

    @RequestMapping(value = "/measurements_", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<MeasurementInformation>> allMeasurements() {
        log.trace("GET request for /measurements");
        try {
            List<MeasurementInformation> measurementList = catalogService.getAllMeasurements();
            return new ResponseEntity<>(measurementList, HttpStatus.OK);
        } catch (DataStoreException e) {
            return new ResponseEntity<>(e.getErrorCode());
        }
    }
}
