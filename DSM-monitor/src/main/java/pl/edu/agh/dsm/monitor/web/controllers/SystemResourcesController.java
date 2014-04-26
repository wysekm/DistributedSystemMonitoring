package pl.edu.agh.dsm.monitor.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.agh.dsm.common.dto.SystemResourceDto;
import pl.edu.agh.dsm.monitor.externalApi.UCResourcesList;
import pl.edu.agh.dsm.monitor.web.SystemResourceAssemblerSupport;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@ExposesResourceFor(SystemResourceDto.class)
@RequestMapping("resources")
public class SystemResourcesController {

	@Autowired
	private SystemResourceAssemblerSupport assemblerSupport;

	@Autowired
	UCResourcesList ucResourcesList;

	@RequestMapping(method = GET, value = "")
	public Resources<Resource<SystemResourceDto>> getResources() {
		List<SystemResourceDto> filter = ucResourcesList.list();
		return assemblerSupport.addLinks(filter);
	}

}
