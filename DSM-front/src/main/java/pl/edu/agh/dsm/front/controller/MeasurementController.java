package pl.edu.agh.dsm.front.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.front.dto.GraphInput;
import pl.edu.agh.dsm.front.service.CatalogueRestClientService;

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
			Model model) {
		model.addAttribute("refreshInterval", refreshInterval);
		model.addAttribute("graphInput", new GraphInput());
		return restClient.getMeasurements(metric, resource);
	}
}
