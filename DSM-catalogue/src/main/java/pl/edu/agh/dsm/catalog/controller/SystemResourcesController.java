package pl.edu.agh.dsm.catalog.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.agh.dsm.common.dto.SystemResourceDto;
import pl.edu.agh.dsm.common.service.ResourcesService;
import pl.edu.agh.dsm.common.web.SystemResourceAssemblerSupport;

@RestController
@ExposesResourceFor(SystemResourceDto.class)
@RequestMapping("resources")
public class SystemResourcesController {
	
	@Autowired
	private SystemResourceAssemblerSupport assemblerSupport;

	@Autowired
	ResourcesService resourceService;

	@RequestMapping(method = GET, value = "")
	public Resources<Resource<SystemResourceDto>> getList() {
		List<SystemResourceDto> list = resourceService.getList();
		return assemblerSupport.addLinks(list);
	}

}
