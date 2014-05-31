package pl.edu.agh.dsm.monitor.web.infrastructure.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurementsService;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;
import pl.edu.agh.dsm.monitor.core.model.user.ApplicationUser;
import pl.edu.agh.dsm.monitor.core.usecase.AddSensorMeasurement;
import pl.edu.agh.dsm.monitor.core.usecase.CreateComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.usecase.DeleteComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.usecase.GetComplexMeasurementDetails;
import pl.edu.agh.dsm.monitor.web.view.dto.MeasurementDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MeasurementResourceAssemblerSupport extends
		AbstractResourceAssemblerSupport<MeasurementDto> {

	@Autowired
	private EntityLinks entityLinks;

	@Autowired
	GetComplexMeasurementDetails getComplexMeasurementDetailsUC;

	@Autowired
	DeleteComplexMeasurement deleteComplexMeasurementUC;

	@Autowired
	CreateComplexMeasurement createComplexMeasurementUC;

	@Override
	public Resource<MeasurementDto> addLinks(MeasurementDto dto, ApplicationUser user) {

		UUID uuid = dto.getId();
		List<Link> links = new ArrayList<>();
		String mhref = entityLinks.linkToCollectionResource(MeasurementDto.class).getHref();
		String chref = entityLinks.linkToCollectionResource(ComplexMeasurement.class).getHref();
		Link selfLink = entityLinks.linkToSingleResource(MeasurementDto.class, uuid);
		links.add(selfLink);
		links.add(new Link(selfLink.getHref() + "/data{?limit,value}", "data"));
		if(getComplexMeasurementDetailsUC.isMeasurementComplex(uuid).isPossible()) {
			links.add(new Link(chref + "/" + uuid, "complexDetails"));
			if(deleteComplexMeasurementUC.canDelete(uuid, user).isPossible()) {
				links.add(new Link(selfLink.getHref(), "delete"));	//TODO change to deleteComplex
			}
			if(createComplexMeasurementUC.canCreate(user).isPossible()) {
				links.add(new Link(mhref, "addComplex"));
			}
		}
		return new Resource<>(dto, links);
	}
}
