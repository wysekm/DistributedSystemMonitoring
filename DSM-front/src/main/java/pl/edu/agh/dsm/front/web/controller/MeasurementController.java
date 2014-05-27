package pl.edu.agh.dsm.front.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.agh.dsm.front.core.model.rest.UserCredentials;
import pl.edu.agh.dsm.front.core.model.rest.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.front.core.model.rest.dto.MeasurementDto;
import pl.edu.agh.dsm.front.core.usecase.AddMeasurement;
import pl.edu.agh.dsm.front.core.usecase.DeleteMeasurement;
import pl.edu.agh.dsm.front.core.usecase.GetAvailableComplexTypes;
import pl.edu.agh.dsm.front.core.usecase.GetMeasurements;
import pl.edu.agh.dsm.front.web.Infrastructure.ComplexConverter;
import pl.edu.agh.dsm.front.web.Infrastructure.UserConverter;
import pl.edu.agh.dsm.front.web.view.dto.GraphInput;
import pl.edu.agh.dsm.front.web.view.dto.MeasurementInput;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("measurements")
public class MeasurementController {

	static final Logger log = LoggerFactory.getLogger(MeasurementController.class);

	@Value("${measurement.refresh.interval}")
	int refreshInterval;

	@Autowired
	GetAvailableComplexTypes getAvailableComplexTypesUC;

	@Autowired
	GetMeasurements getMeasurementsUC;

	@Autowired
	AddMeasurement addMeasurementUC;

	@Autowired
	DeleteMeasurement deleteMeasurementUC;
	
	@ModelAttribute("resources")
	@RequestMapping(method = GET, value = "")
	public Collection<Resource<MeasurementDto>> measurementsPage(
			@RequestParam(value = "metric", required = false, defaultValue = "") String metric,
			@RequestParam(value = "resource", required = false, defaultValue = "") String resource,
			@AuthenticationPrincipal User user,
			Model model) {
		model.addAttribute("refreshInterval", refreshInterval);
		model.addAttribute("measurementInput", new MeasurementInput());
		model.addAttribute("graphInput", new GraphInput());
		try {
			UserCredentials userCreds = UserConverter.convert(user);
			Collection<Resource<MeasurementDto>> result =
					getMeasurementsUC.getMeasurements(metric, resource, userCreds);
			setAvailableComplexTypes(result, model);
			return result;
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return new ArrayList<>();
		}
	}

	@RequestMapping(method = POST, value = "/add")
	@ModelAttribute("resources")
	public Collection<Resource<MeasurementDto>> addMeasurement(
			@ModelAttribute(value="measurementInput") MeasurementInput measurementInput,
			@AuthenticationPrincipal User user,
			Model model) {
		try {
			String addUri = measurementInput.getAddUri();
			ComplexMeasurementDto complexDetails = ComplexConverter.convert(measurementInput.getComplexDetails());
			UserCredentials userCreds = UserConverter.convert(user);
			addMeasurementUC.addMeasurement(addUri, complexDetails, userCreds);
			return measurementsPage("", "", user, model);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return new ArrayList<>();
		}
	}

	@RequestMapping(method = GET, value = "/delete")
	@ModelAttribute("resources")
	public Collection<Resource<MeasurementDto>> deleteMeasurement(
			@RequestParam(value = "deleteUri", required = true) String deleteUri,
			@AuthenticationPrincipal User user,
			Model model) {
		try {
			UserCredentials userCreds = UserConverter.convert(user);
			deleteMeasurementUC.deleteMeasurement(deleteUri, userCreds);
			return measurementsPage("", "", user, model);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return new ArrayList<>();
		}
	}

	private void setAvailableComplexTypes(
			Collection<Resource<MeasurementDto>> measurements,
			Model model) throws MalformedURLException {
		if(measurements.isEmpty()) return;
		String details = measurements.iterator().next().getLink("self").getHref();
		String monitorAddress = new URL(details).getHost();
		model.addAttribute("complexTypes",
				getAvailableComplexTypesUC.getAvailableComplexTypes(monitorAddress));
	}

	private String extractDetailsUri(ComplexMeasurementDto complexDetails) throws MalformedURLException {
		String details = complexDetails.getMeasurement();
		String monitorAddress = new URL(details).getHost();
		return monitorAddress + "/measurements";
	}
}
