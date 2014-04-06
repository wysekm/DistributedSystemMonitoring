package pl.edu.agh.dsm.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.dsm.monitor.api.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@ExposesResourceFor(Measurement.class)
@RequestMapping("measurements")
public class MeasurementsController {

    @Autowired
    private EntityLinks entityLinks;


    @Autowired
    private UCMonitorMeasurementsList ucMonitorMeasurementsList;

    @Autowired
    private UCAddComplexMeasurement ucAddComplexMeasurement;

    @Autowired
    private UCMeasurementDetails ucMeasurementDetails;

    @Autowired
    private UCMeasurementDataDetails ucMeasurementDataDetails;

    @Autowired
    private UCDeleteComplexMeasurement ucDeleteComplexMeasurement;

    @RequestMapping(method = GET,value = "")
    public Resources<Measurement> getMeasurements(@RequestParam(value = "metric",defaultValue = "") String metric, @RequestParam(value = "resource",defaultValue = "") String resource)
    {
        List<Measurement> filter = ucMonitorMeasurementsList.filter(metric, resource);
        //TODO add links
        return new Resources<Measurement>(filter);
    }

    @RequestMapping(method = POST,value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMeasurement(@RequestBody Measurement measurement)
    {
        ucAddComplexMeasurement.create(measurement);
    }

    @RequestMapping(method = GET,value = "/{id}")
    public Measurement getMeasurement(@PathVariable("id")UUID uuid)
    {
        Measurement details = ucMeasurementDetails.details(uuid);
        //TODO add links
        return details;
    }

    @RequestMapping(method = GET,value = "/{id}/data")
    public Measurement getData(@PathVariable("id")UUID uuid)
    {
        Measurement details = ucMeasurementDataDetails.details(uuid);
        //TODO add links
        return details;
    }

    @RequestMapping(method = DELETE,value = "")
    public void deleteMeasurement(@PathVariable("id") UUID uuid)
    {
        ucDeleteComplexMeasurement.delete(uuid);
    }



}
