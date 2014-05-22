package pl.edu.agh.dsm.front.controller;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.client.RestClientException;
import pl.edu.agh.dsm.front.dto.SystemResourceDto;
import pl.edu.agh.dsm.front.service.CatalogueRestClientService;

@Controller
public class ResourceController {
	
	@Autowired
	CatalogueRestClientService restClient;
	
	@ModelAttribute("resources")
	@RequestMapping("/resources")
	public Collection<Resource<SystemResourceDto>> resourcePage(Model model) {
		String error = null;
		try {
			return restClient.getSystemResources();
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
}
