package pl.edu.agh.dsm.catalog.web.infrastructure;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.catalog.web.view.dto.MeasurementDto;

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
