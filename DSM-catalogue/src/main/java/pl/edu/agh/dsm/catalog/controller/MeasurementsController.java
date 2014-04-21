package pl.edu.agh.dsm.catalog.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.agh.dsm.catalog.service.MeasurementsService;
import pl.edu.agh.dsm.common.dto.MeasurementDto;

@RestController
@ExposesResourceFor(MeasurementDto.class)
@RequestMapping("measurements")
public class MeasurementsController {

	@Autowired
	private MeasurementsService service;

	@Autowired
	private EntityLinks entityLinks;

	@RequestMapping(method = GET, value = "")
	public Resources<MeasurementDto> getMeasurements(
			@RequestParam(value = "metric", defaultValue = "") String metric,
			@RequestParam(value = "resource", defaultValue = "") String resource) {

		List<MeasurementDto> list = service.getMeasurements(metric, resource);
		return new Resources<MeasurementDto>(list);
	}

	@Secured("ROLE_MONITOR")
	@RequestMapping(method = POST, value = "")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody
	MeasurementDto addMeasurement(@RequestBody MeasurementDto measurement) {

		MeasurementDto result = service.addMeasurement(measurement);
		return result;
	}

	@Secured("ROLE_MONITOR")
	@RequestMapping(method = DELETE, value = "/{id}")
	public void deleteMeasurement(@PathVariable("id") UUID uuid) {
		service.deleteMeasurement(uuid);
	}

}
