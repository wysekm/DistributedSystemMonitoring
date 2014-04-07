package pl.edu.agh.dsm.monitor.web;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;
import pl.edu.agh.dsm.common.security.AutorizationContext;
import pl.edu.agh.dsm.monitor.dto.Measurement;
import pl.edu.agh.dsm.monitor.externalApi.UCDeleteComplexMeasurement;

import java.util.List;

@Component
public class MeasurementResourceAssemblerSupport extends AbstractResourceAssemblerSupport<Measurement> {


    private EntityLinks entityLinks;
    private UCDeleteComplexMeasurement ucDeleteComplexMeasurement;
    private AutorizationContext autorizationContext;


    @Autowired
    public MeasurementResourceAssemblerSupport(AutorizationContext autorizationContext, EntityLinks entityLinks, UCDeleteComplexMeasurement ucDeleteComplexMeasurement) {
        this.autorizationContext = autorizationContext;
        this.entityLinks = entityLinks;
        this.ucDeleteComplexMeasurement = ucDeleteComplexMeasurement;
    }

    @Override
    public Resource<Measurement> addLinks(Measurement measurement) {
        //TODO add links for Measurements
//        ucDeleteComplexMeasurement.havePermission(measurement.getId());
        return new Resource<>(measurement);
    }
}
