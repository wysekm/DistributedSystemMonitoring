package pl.edu.agh.dsm.monitor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;
import pl.edu.agh.dsm.common.security.AutorizationContext;
import pl.edu.agh.dsm.monitor.dto.Measurement;
import pl.edu.agh.dsm.monitor.dto.SystemResource;
import pl.edu.agh.dsm.monitor.externalApi.UCDeleteComplexMeasurement;

@Component
public class SystemResourceAssemblerSupport extends AbstractResourceAssemblerSupport<SystemResource> {


    private EntityLinks entityLinks;

    @Autowired
    public SystemResourceAssemblerSupport(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @Override
    public Resource<SystemResource> addLinks(SystemResource measurement) {
        //TODO add link to filter


        return new Resource<>(measurement);
    }
}
