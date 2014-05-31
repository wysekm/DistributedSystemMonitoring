package pl.edu.agh.dsm.monitor.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.DataLimit;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;
import pl.edu.agh.dsm.monitor.core.model.user.ApplicationUser;
import pl.edu.agh.dsm.monitor.core.usecase.*;
import pl.edu.agh.dsm.monitor.web.infrastructure.MeasurementDtoConverter;
import pl.edu.agh.dsm.monitor.web.infrastructure.UserConverter;
import pl.edu.agh.dsm.monitor.web.infrastructure.assembler.MeasurementResourceAssemblerSupport;
import pl.edu.agh.dsm.monitor.web.view.dto.ComplexMeasurementInDto;
import pl.edu.agh.dsm.monitor.web.view.dto.MeasurementDto;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.*;

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
			@RequestParam(value = "resource", defaultValue = "") String resource,
			@AuthenticationPrincipal User user) {
		ApplicationUser applicationUser = UserConverter.convert(user);
		List<Measurement> list = getMeasurementsUC.getList(metric, resource);
		List<MeasurementDto> dtoList = measurementConverter.convertMeasurements(list);
		return assemblerSupport.addLinks(dtoList, applicationUser);
	}

	@RequestMapping(method = GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MeasurementDto> getMeasurement(
			@PathVariable("id") UUID uuid,
			@AuthenticationPrincipal User user) {
		ApplicationUser applicationUser = UserConverter.convert(user);
		Measurement details = getMeasurementDetailsUC.getDetails(uuid);
		MeasurementDto dto = measurementConverter.convertMeasurement(details);
		return assemblerSupport.addLinks(dto, applicationUser);
	}

	@RequestMapping(method = GET, value = "/{id}/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MeasurementData> getData(
			@PathVariable("id") UUID uuid,
			@RequestParam(value = "limit", defaultValue = "all") DataLimit limit,
			@RequestParam(value = "value", defaultValue = "1") int value) {
		return getMeasurementDataUC.getData(uuid, limit, value);
	}

	@RequestMapping(method = POST, value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<MeasurementDto> createMeasurement(
			@RequestBody ComplexMeasurementInDto inDto,
			@AuthenticationPrincipal User user) {
		ApplicationUser applicationUser = UserConverter.convert(user);
		ComplexMeasurement complex = measurementConverter.convertComplexMeasurement(inDto);
		Measurement measurement = createComplexMeasurementUC.create(complex, applicationUser);
		MeasurementDto outDto = measurementConverter.convertMeasurement(measurement);
		return assemblerSupport.addLinks(outDto, applicationUser);
	}

	@RequestMapping(method = DELETE, value = "/{id}")
	public void deleteMeasurement(
			@PathVariable("id") UUID uuid,
			@AuthenticationPrincipal User user) {
		ApplicationUser applicationUser = UserConverter.convert(user);
		deleteComplexMeasurementUC.delete(uuid, applicationUser);
	}
}
