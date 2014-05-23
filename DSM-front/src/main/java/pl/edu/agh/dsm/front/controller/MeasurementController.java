package pl.edu.agh.dsm.front.controller;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.client.RestClientException;
import pl.edu.agh.dsm.front.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.front.dto.GraphInput;
import pl.edu.agh.dsm.front.dto.MeasurementDto;
import pl.edu.agh.dsm.front.service.CatalogueRestClientService;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class MeasurementController {

	@Value("${measurement.refresh.interval}")
	int refreshInterval;
	
	@Autowired
	CatalogueRestClientService restClient;
	
	@ModelAttribute("resources")
	@RequestMapping("/measurements")
	public Collection<Resource<MeasurementDto>> measurementsPage(
			@RequestParam(value = "metric", required = false, defaultValue = "") String metric,
			@RequestParam(value = "resource", required = false, defaultValue = "") String resource,
			@AuthenticationPrincipal User user,
			Model model) {
		model.addAttribute("refreshInterval", refreshInterval);
		model.addAttribute("graphInput", new GraphInput());
		String error = null;
		try {
			Collection<Resource<MeasurementDto>> result =
					restClient.getMeasurements(metric, resource, user);
			setAvailableComplexTypes(result, model);
			fillComplexDetails(result);
			return result;
		} catch(RestClientException e) {
			if(e.getRootCause() instanceof ConnectException) {
				error = "Catalogue Service is temporary unavailable. Please try again later.";
			} else {
				error = e.getMessage();
			}
		} catch (Exception e) {
			error = e.getMessage();
		}
		model.addAttribute("error", error);
		return new ArrayList<>();
	}

	private void fillComplexDetails(Collection<Resource<MeasurementDto>> result) {
		for(Resource<MeasurementDto> measurement : result) {
			Link complexDetails = measurement.getLink("complexDetails");
			if(complexDetails != null) {
				ComplexMeasurementDto complexMeasurement =
						restClient.getComplexDetails(complexDetails.getHref()).getContent();
				measurement.getContent().setComplexDetails(complexMeasurement);
			}
		}
	}

	private void setAvailableComplexTypes(
			Collection<Resource<MeasurementDto>> measurements,
			Model model) throws MalformedURLException {
		if(measurements.isEmpty()) return;
		String details = measurements.iterator().next().getLink("details").getHref();
		String monitorAddress = new URL(details).getHost();
		model.addAttribute("complexTypes", restClient.getAvailableComplexTypes(monitorAddress));
	}
}
