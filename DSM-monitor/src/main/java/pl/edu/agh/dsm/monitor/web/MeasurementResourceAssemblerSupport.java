package pl.edu.agh.dsm.monitor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.security.AutorizationContext;
import pl.edu.agh.dsm.monitor.externalApi.UCDeleteComplexMeasurement;

@Component
public class MeasurementResourceAssemblerSupport extends AbstractResourceAssemblerSupport<MeasurementDto> {


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
    public Resource<MeasurementDto> addLinks(MeasurementDto measurementDto) {
        //TODO add links for Measurements
//        ucDeleteComplexMeasurement.havePermission(measurement.getId());
        return new Resource<>(measurementDto);
    }
}
