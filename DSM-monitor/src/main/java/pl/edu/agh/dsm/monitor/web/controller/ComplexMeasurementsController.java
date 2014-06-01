package pl.edu.agh.dsm.monitor.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexType;
import pl.edu.agh.dsm.monitor.core.usecase.GetAvailableComplexTypes;
import pl.edu.agh.dsm.monitor.core.usecase.GetComplexMeasurementDetails;
import pl.edu.agh.dsm.monitor.web.infrastructure.assembler.ComplexMeasurementAssemblerSupport;
import pl.edu.agh.dsm.monitor.web.infrastructure.assembler.ComplexTypesAssemblerSupport;

import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;

/**
 * Created by Tom on 2014-05-30.
 */
@RestController
@ExposesResourceFor(ComplexMeasurement.class)
@RequestMapping("complex")
public class ComplexMeasurementsController {

	@Autowired
	GetComplexMeasurementDetails getComplexMeasurementDetailsUC;

	@Autowired
	GetAvailableComplexTypes getAvailableComplexTypesUC;

	@Autowired
	ComplexMeasurementAssemblerSupport assemblerSupport;

	@Autowired
	ComplexTypesAssemblerSupport typeAssemblerSupport;

	@RequestMapping(method = GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<ComplexMeasurement> getDetails(@PathVariable("id") UUID uuid) {
		ComplexMeasurement details = getComplexMeasurementDetailsUC.getDetails(uuid);
		return assemblerSupport.addLinks(details);
	}

	@RequestMapping(method = GET, value = "/types", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<ComplexType>> getAvailableComplexTypes() {
		return typeAssemblerSupport.addLinks(getAvailableComplexTypesUC.getAvailableComplexTypes());
	}
}
