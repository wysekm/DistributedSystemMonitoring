package pl.edu.agh.dsm.monitor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.web.AbstractResourceAssemblerSupport;

@Component
public class MeasurementResourceAssemblerSupport extends
		AbstractResourceAssemblerSupport<MeasurementDto> {

	@Autowired
	private EntityLinks entityLinks;
	
	//private ComplexMeasurementsService complexMeasurementsService;
	//private AutorizationContext autorizationContext;

	@Override
	public Resource<MeasurementDto> addLinks(MeasurementDto dto) {
		// TODO add links for add, delete, details of complex measurements
		// ucDeleteComplexMeasurement.havePermission(measurement.getId());
		
		Resource <MeasurementDto> resource = new Resource<>(dto);
		Link selfLink = entityLinks.linkToSingleResource(dto.getClass(), dto.getId());
		String href = entityLinks.linkToCollectionResource(MeasurementDto.class).getHref();
		href += "/" + dto.getId() + "/data";
		Link dataLink = new Link(href, "data");
		resource.add(selfLink);
		resource.add(dataLink);
		return resource;
	}
}
