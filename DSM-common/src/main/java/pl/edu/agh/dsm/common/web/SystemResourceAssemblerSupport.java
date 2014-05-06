package pl.edu.agh.dsm.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.dto.SystemResourceDto;
import pl.edu.agh.dsm.common.web.AbstractResourceAssemblerSupport;

@Component
public class SystemResourceAssemblerSupport extends
		AbstractResourceAssemblerSupport<SystemResourceDto> {

	private EntityLinks entityLinks;

	@Autowired
	public SystemResourceAssemblerSupport(EntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@Override
	public Resource<SystemResourceDto> addLinks(SystemResourceDto dto) {
		Resource <SystemResourceDto> resource = new Resource<>(dto);
		String href = entityLinks
				.linkToCollectionResource(MeasurementDto.class).getHref();
		href += "?resource=" + dto.getName();
		Link link = new Link(href, "measurements");
		resource.add(link);
		return resource;
	}
}
