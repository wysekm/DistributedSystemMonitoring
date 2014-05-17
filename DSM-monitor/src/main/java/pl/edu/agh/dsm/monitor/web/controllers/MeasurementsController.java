package pl.edu.agh.dsm.monitor.web.controllers;

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

import pl.edu.agh.dsm.common.dto.DataLimit;
import pl.edu.agh.dsm.common.dto.MeasurementDataDto;
import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.monitor.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.monitor.service.ComplexMeasurementsService;
import pl.edu.agh.dsm.monitor.service.MeasurementsService;
import pl.edu.agh.dsm.monitor.web.MeasurementResourceAssemblerSupport;

@RestController
@ExposesResourceFor(MeasurementDto.class)
@RequestMapping("measurements")
public class MeasurementsController {

	@Autowired
	private MeasurementResourceAssemblerSupport assemblerSupport;

	@Autowired
	private MeasurementsService measurementService;
	
	@Autowired
	private ComplexMeasurementsService complexMeasurementService;

	@RequestMapping(method = GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MeasurementDto>> getMeasurements(
			@RequestParam(value = "metric", defaultValue = "") String metric,
			@RequestParam(value = "resource", defaultValue = "") String resource) {
		List<MeasurementDto> list = measurementService.getList(metric, resource);
		return assemblerSupport.addLinks(list);
	}

	@RequestMapping(method = POST, value = "")
	@ResponseStatus(HttpStatus.CREATED)
	public void addMeasurement(@RequestBody ComplexMeasurementDto complex) {
		complexMeasurementService.create(complex);
	}

	@RequestMapping(method = GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MeasurementDto> getMeasurement(@PathVariable("id") UUID uuid) {
		MeasurementDto details = measurementService.getDetails(uuid);
		return assemblerSupport.addLinks(details);
	}

	@RequestMapping(method = GET, value = "/{id}/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MeasurementDataDto> getData(
			@PathVariable("id") UUID uuid,
			@RequestParam(value = "limit", defaultValue = "all") DataLimit limit,
			@RequestParam(value = "value", defaultValue = "1") int value) {
		return measurementService.getData(uuid, limit, value);
	}

	@RequestMapping(method = DELETE, value = "/{id}")
	public void deleteMeasurement(@PathVariable("id") UUID uuid) {
		complexMeasurementService.delete(uuid);
	}
	
	//TODO getComplexDetails
}
