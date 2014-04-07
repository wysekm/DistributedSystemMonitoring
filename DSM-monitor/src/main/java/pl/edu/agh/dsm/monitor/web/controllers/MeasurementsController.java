package pl.edu.agh.dsm.monitor.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.dsm.monitor.dto.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.dto.ComplexMeasurementParam;
import pl.edu.agh.dsm.monitor.dto.Measurement;
import pl.edu.agh.dsm.monitor.dto.MeasurementData;
import pl.edu.agh.dsm.monitor.externalApi.*;
import pl.edu.agh.dsm.monitor.web.MeasurementResourceAssemblerSupport;

import java.util.List;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@ExposesResourceFor(Measurement.class)
@RequestMapping("measurements")
public class MeasurementsController {


    @Autowired
    private MeasurementResourceAssemblerSupport assemblerSupport;

    @Autowired
    private UCMeasurementsList ucMonitorMeasurementsList;

    @Autowired
    private UCAddComplexMeasurement ucAddComplexMeasurement;

    @Autowired
    private UCMeasurementDetails ucMeasurementDetails;

    @Autowired
    private UCMeasurementDataDetails ucMeasurementDataDetails;

    @Autowired
    private UCDeleteComplexMeasurement ucDeleteComplexMeasurement;



    @RequestMapping(method = GET,value = "")
    public Resources<Resource<Measurement>> getMeasurements(@RequestParam(value = "metric",defaultValue = "") String metric, @RequestParam(value = "resource",defaultValue = "") String resource)
    {
        List<Measurement> filter = ucMonitorMeasurementsList.filter(metric, resource);
        return assemblerSupport.addLinks(filter);
    }

    @RequestMapping(method = POST,value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMeasurement(@RequestBody ComplexMeasurement complex)
    {
        ucAddComplexMeasurement.create(complex);
    }

    @RequestMapping(method = GET,value = "/{id}")
    public Resource<Measurement> getMeasurement(@PathVariable("id")UUID uuid)
    {
        Measurement details = ucMeasurementDetails.details(uuid);
        return assemblerSupport.addLinks(details);
    }


    @RequestMapping(method = GET,value = "/{id}/data")
    public List<MeasurementData> getData(@PathVariable("id") UUID uuid , @RequestParam(value = "limit",defaultValue = "all") String limit, @RequestParam(value = "value",defaultValue = "") String value)
    {
        return ucMeasurementDataDetails.details(uuid,limit,value);
    }

    @RequestMapping(method = DELETE,value = "/{id}")
    public void deleteMeasurement(@PathVariable("id") UUID uuid)
    {
        ucDeleteComplexMeasurement.delete(uuid);
    }



}
