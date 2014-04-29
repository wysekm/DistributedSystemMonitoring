package pl.edu.agh.dsm.catalog.web;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.web.AbstractResourceAssemblerSupport;

@Component
public class MeasurementResourceAssemblerSupport extends
		AbstractResourceAssemblerSupport<MeasurementDto> {

	@Override
	public Resource<MeasurementDto> addLinks(MeasurementDto dto) {
		Resource<MeasurementDto> resource = new Resource<>(dto);
		String href = dto.getMonitor() + "/measurements/"
				+ dto.getId();
		Link link =  new Link(href, "details");
		resource.add(link);
		return resource;
	}
}
