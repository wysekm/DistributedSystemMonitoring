package pl.edu.agh.dsm.catalog.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.agh.dsm.catalog.core.model.measurement.Measurement;
import pl.edu.agh.dsm.catalog.core.model.user.ApplicationUser;
import pl.edu.agh.dsm.catalog.core.usecase.AddMeasurement;
import pl.edu.agh.dsm.catalog.core.usecase.DeleteMeasurement;
import pl.edu.agh.dsm.catalog.core.usecase.GetMeasurements;
import pl.edu.agh.dsm.catalog.web.infrastructure.MeasurementDtoConverter;
import pl.edu.agh.dsm.catalog.web.infrastructure.MeasurementResourceAssemblerSupport;
import pl.edu.agh.dsm.catalog.web.view.dto.MeasurementDto;

@RestController
@ExposesResourceFor(MeasurementDto.class)
@RequestMapping("measurements")
public class MeasurementsController {

	@Autowired
	AddMeasurement addMeasurementUC;
	
	@Autowired
	DeleteMeasurement deleteMeasurementUC;
	
	@Autowired
	GetMeasurements getMeasurementUC;
	
	@Autowired
	MeasurementResourceAssemblerSupport assmeblerSupport;
	
	@Autowired
	MeasurementDtoConverter measurementConverter;

	@RequestMapping(method = GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MeasurementDto>> getMeasurements(
			@RequestParam(value = "metric", defaultValue = "") String metric,
			@RequestParam(value = "resource", defaultValue = "") String resource) {

		List<Measurement> list = getMeasurementUC.getList(metric, resource);
		List<MeasurementDto> dtoList = measurementConverter.convertMeasurements(list);
		return assmeblerSupport.addLinks(dtoList);
	}

	@RequestMapping(method = POST, value = "", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Resource<MeasurementDto> addMeasurement(
			@RequestBody Measurement measurement,
			HttpServletRequest request) {
		//String userName = request.getRemoteUser();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("adding measurement for user: " + userName);
		ApplicationUser applicationUser = null;
		if(userName != null) applicationUser = new ApplicationUser(userName);
		addMeasurementUC.addMeasurement(measurement, applicationUser);
		MeasurementDto dto = measurementConverter.convertMeasurement(measurement);
		return assmeblerSupport.addLinks(dto);
	}

	@RequestMapping(method = DELETE, value = "/{id}")
	public void deleteMeasurement(
			@PathVariable("id") UUID uuid) {
			//@AuthenticationPrincipal User user) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("adding measurement for user: " + userName);
		ApplicationUser applicationUser = null;
		if(userName != null) applicationUser = new ApplicationUser(userName);
		deleteMeasurementUC.deleteMeasurement(uuid, applicationUser);
	}
}
