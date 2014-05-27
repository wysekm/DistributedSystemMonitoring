package pl.edu.agh.dsm.front.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.agh.dsm.front.core.model.rest.dto.SystemResourceDto;
import pl.edu.agh.dsm.front.core.usecase.GetSystemResources;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ResourceController {
	
	@Autowired
	GetSystemResources getSystemResourcesUC;
	
	@ModelAttribute("resources")
	@RequestMapping("/resources")
	public Collection<Resource<SystemResourceDto>> resourcePage(Model model) {
		try {
			return getSystemResourcesUC.getResources();
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return new ArrayList<>();
		}
	}
}
