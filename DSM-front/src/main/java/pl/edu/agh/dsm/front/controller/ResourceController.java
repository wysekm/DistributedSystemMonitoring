package pl.edu.agh.dsm.front.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.edu.agh.dsm.common.dto.SystemResourceDto;
import pl.edu.agh.dsm.front.service.CatalogueRestClientService;

@Controller
public class ResourceController {
	
	@Autowired
	CatalogueRestClientService restClient;
	
	@ModelAttribute("resources")
	@RequestMapping("/resources")
	public Collection<Resource<SystemResourceDto>> resourcePage() {
		return restClient.getSystemResources();
	}
}
