package pl.edu.agh.dsm.monitor.web.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.core.model.resource.SystemResource;
import pl.edu.agh.dsm.monitor.web.view.dto.MeasurementDto;

@Component
public class SystemResourceAssemblerSupport extends
		AbstractResourceAssemblerSupport<SystemResource> {

	private EntityLinks entityLinks;

	@Autowired
	public SystemResourceAssemblerSupport(EntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@Override
	public Resource<SystemResource> addLinks(SystemResource dto) {
		Resource <SystemResource> resource = new Resource<>(dto);
		String href = entityLinks
				.linkToCollectionResource(MeasurementDto.class).getHref();
		href += "?resource=" + dto.getName();
		Link link = new Link(href, "measurements");
		resource.add(link);
		return resource;
	}
}
