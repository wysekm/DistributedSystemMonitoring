package pl.edu.agh.dsm.monitor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.security.AutorizationContext;
import pl.edu.agh.dsm.monitor.service.ComplexMeasurementsService;

@Component
public class MeasurementResourceAssemblerSupport extends
		AbstractResourceAssemblerSupport<MeasurementDto> {

	private EntityLinks entityLinks;
	private ComplexMeasurementsService complexMeasurementsService;
	private AutorizationContext autorizationContext;

	@Autowired
	public MeasurementResourceAssemblerSupport(EntityLinks entityLinks,
			ComplexMeasurementsService complexMeasurementsService,
			AutorizationContext autorizationContext) {
		super();
		this.entityLinks = entityLinks;
		this.complexMeasurementsService = complexMeasurementsService;
		this.autorizationContext = autorizationContext;
	}

	@Override
	public Resource<MeasurementDto> addLinks(MeasurementDto measurementDto) {
		// TODO add links for Measurements
		// ucDeleteComplexMeasurement.havePermission(measurement.getId());
		return new Resource<>(measurementDto);
	}
}
