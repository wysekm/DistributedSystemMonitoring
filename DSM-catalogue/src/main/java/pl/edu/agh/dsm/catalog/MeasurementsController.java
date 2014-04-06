package pl.edu.agh.dsm.catalog;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@ExposesResourceFor(Measurement.class)
@RequestMapping("measurements")
public class MeasurementsController {

    @Autowired
    private EntityLinks entityLinks;

    @RequestMapping(method = GET,value = "")
    public Resources<Measurement> getMeasurements()
    {

        Measurement some = new Measurement("some", "dsds");
        Resource element = new Resource(some);
        element.add(new Link("http://wp.pl","wplink"));
        element.add(entityLinks.linkToSingleResource(some.getClass(),"fdfd"));
        ImmutableList of = ImmutableList.of(element);

        return new Resources<Measurement>(of);
    }

    @RequestMapping(method = POST,value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Measurement> addMeasurement()
    {
        return ImmutableList.of(new Measurement("some","dsds"));
    }

    @RequestMapping(method = DELETE,value = "")
    public List<Measurement> deleteMeasurement()
    {
        return ImmutableList.of(new Measurement("some","dsds"));
    }

}
