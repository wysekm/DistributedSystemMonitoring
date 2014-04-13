package pl.edu.agh.dsm.monitor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.common.dto.SystemResourceDto;

@Component
public class SystemResourceAssemblerSupport extends AbstractResourceAssemblerSupport<SystemResourceDto> {


    private EntityLinks entityLinks;

    @Autowired
    public SystemResourceAssemblerSupport(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @Override
    public Resource<SystemResourceDto> addLinks(SystemResourceDto measurement) {
        //TODO add link to filter


        return new Resource<>(measurement);
    }
}
