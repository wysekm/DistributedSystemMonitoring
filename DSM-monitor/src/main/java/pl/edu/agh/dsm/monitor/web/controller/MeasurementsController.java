package pl.edu.agh.dsm.monitor.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.DataLimit;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;
import pl.edu.agh.dsm.monitor.core.model.user.ApplicationUser;
import pl.edu.agh.dsm.monitor.core.usecase.CreateComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.usecase.DeleteComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.usecase.GetMeasurementData;
import pl.edu.agh.dsm.monitor.core.usecase.GetMeasurementDetails;
import pl.edu.agh.dsm.monitor.core.usecase.GetMeasurements;
import pl.edu.agh.dsm.monitor.web.infrastructure.MeasurementDtoConverter;
import pl.edu.agh.dsm.monitor.web.infrastructure.assembler.MeasurementResourceAssemblerSupport;
import pl.edu.agh.dsm.monitor.web.view.dto.MeasurementDto;

@RestController
@ExposesResourceFor(MeasurementDto.class)
@RequestMapping("measurements")
public class MeasurementsController {

	// TODO autowired powinno być przy konstruktorze (albo zrobić settery)
	
	@Autowired
	private MeasurementResourceAssemblerSupport assemblerSupport;
	
	@Autowired
	MeasurementDtoConverter measurementConverter;

	@Autowired
	GetMeasurementDetails getMeasurementDetailsUC;
	@Autowired
	GetMeasurements getMeasurementsUC;
	@Autowired
	GetMeasurementData getMeasurementDataUC;
	@Autowired
	CreateComplexMeasurement createComplexMeasurementUC;
	@Autowired
	DeleteComplexMeasurement deleteComplexMeasurementUC;

	@RequestMapping(method = GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MeasurementDto>> getMeasurements(
			@RequestParam(value = "metric", defaultValue = "") String metric,
			@RequestParam(value = "resource", defaultValue = "") String resource) {
		List<Measurement> list = getMeasurementsUC.getList(metric, resource);
		List<MeasurementDto> dtoList = measurementConverter.convertMeasurements(list);
		return assemblerSupport.addLinks(dtoList);
	}

	@RequestMapping(method = GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MeasurementDto> getMeasurement(@PathVariable("id") UUID uuid) {
		Measurement details = getMeasurementDetailsUC.getDetails(uuid);
		MeasurementDto dto = measurementConverter.convertMeasurement(details);
		return assemblerSupport.addLinks(dto);
	}

	@RequestMapping(method = GET, value = "/{id}/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MeasurementData> getData(
			@PathVariable("id") UUID uuid,
			@RequestParam(value = "limit", defaultValue = "all") DataLimit limit,
			@RequestParam(value = "value", defaultValue = "1") int value) {
		return getMeasurementDataUC.getData(uuid, limit, value);
	}
	
	// TODO po stworzeniu nowego elementu controller powinien zwracać ten element
	// Powinien zwrócić measurement Details
	@RequestMapping(method = POST, value = "")
	@ResponseStatus(HttpStatus.CREATED)
	public void createMeasurement(@RequestBody ComplexMeasurement complex) {
		ApplicationUser user = null; //get User;
		createComplexMeasurementUC.create(complex, user);
	}

	@RequestMapping(method = DELETE, value = "/{id}")
	public void deleteMeasurement(@PathVariable("id") UUID uuid) {
		ApplicationUser user = null; //get User;
		deleteComplexMeasurementUC.delete(uuid, user);
	}
	
	//TODO getComplexDetails
}
