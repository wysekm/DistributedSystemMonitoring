package pl.edu.agh.dsm.monitor.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.monitor.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.externalApi.*;
import pl.edu.agh.dsm.monitor.measurement.DataLimit;
import pl.edu.agh.dsm.monitor.web.MeasurementResourceAssemblerSupport;

import java.util.List;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@ExposesResourceFor(MeasurementDto.class)
@RequestMapping("measurements")
public class MeasurementsController {

	@Autowired
	private MeasurementResourceAssemblerSupport assemblerSupport;

	@Autowired
	private UCMeasurementsList ucMonitorMeasurementsList;

	@Autowired
	private UCAddComplexMeasurement ucAddComplexMeasurement;

	@Autowired
	private UCMeasurementDetails ucMeasurementDetails;

	@Autowired
	private UCMeasurementDataDetails ucMeasurementDataDetails;

	@Autowired
	private UCDeleteComplexMeasurement ucDeleteComplexMeasurement;

	@RequestMapping(method = GET, value = "")
	public Resources<Resource<MeasurementDto>> getMeasurements(
			@RequestParam(value = "metric", defaultValue = "") String metric,
			@RequestParam(value = "resource", defaultValue = "") String resource) {
		List<MeasurementDto> filter = ucMonitorMeasurementsList.filter(metric,
				resource);
		return assemblerSupport.addLinks(filter);
	}

	@RequestMapping(method = POST, value = "")
	@ResponseStatus(HttpStatus.CREATED)
	public void addMeasurement(@RequestBody ComplexMeasurementDto complex) {
		ucAddComplexMeasurement.create(complex);
	}

	@RequestMapping(method = GET, value = "/{id}")
	public Resource<MeasurementDto> getMeasurement(@PathVariable("id") UUID uuid) {
		MeasurementDto details = ucMeasurementDetails.details(uuid);
		return assemblerSupport.addLinks(details);
	}

	@RequestMapping(method = GET, value = "/{id}/data")
	public List<MeasurementDataDto> getData(
			@PathVariable("id") UUID uuid,
			@RequestParam(value = "limit", defaultValue = "all") DataLimit limit,
			@RequestParam(value = "value", defaultValue = "0") int value) {
		return ucMeasurementDataDetails.details(uuid, limit, value);
	}

	@RequestMapping(method = DELETE, value = "/{id}")
	public void deleteMeasurement(@PathVariable("id") UUID uuid) {
		ucDeleteComplexMeasurement.delete(uuid);
	}

}
