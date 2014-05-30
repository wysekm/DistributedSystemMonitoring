package pl.edu.agh.dsm.monitor.web.infrastructure.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.web.view.dto.MeasurementDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class ComplexMeasurementAssemblerSupport extends
		AbstractResourceAssemblerSupport<ComplexMeasurement> {

	private EntityLinks entityLinks;

	@Autowired
	public ComplexMeasurementAssemblerSupport(EntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@Override
	public Resource<ComplexMeasurement> addLinks(ComplexMeasurement dto) {
		String mhref = entityLinks.linkToCollectionResource(MeasurementDto.class).getHref();
		List<Link> links = new ArrayList<>();
		links.add(new Link(mhref + "/" + dto.getBaseMeasurementId(), "baseMeasurement"));
		links.add(new Link(mhref + "/" + dto.getId(), "measurementDetails"));
		links.add(entityLinks.linkToSingleResource(ComplexMeasurement.class, dto.getId()));
		return new Resource<>(dto, links);
	}
}
