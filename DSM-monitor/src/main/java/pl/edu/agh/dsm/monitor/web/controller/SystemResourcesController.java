package pl.edu.agh.dsm.monitor.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.agh.dsm.monitor.core.model.resource.SystemResource;
import pl.edu.agh.dsm.monitor.core.usecase.GetResources;
import pl.edu.agh.dsm.monitor.web.infrastructure.assembler.SystemResourceAssemblerSupport;

@RestController
@ExposesResourceFor(SystemResource.class)
@RequestMapping("resources")
public class SystemResourcesController {

	@Autowired
	private SystemResourceAssemblerSupport assemblerSupport;

	@Autowired
	GetResources getResourcesUC;

	@RequestMapping(method = GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<SystemResource>> getResources() {
		List<SystemResource> list = getResourcesUC.getList();
		return assemblerSupport.addLinks(list);
	}

}
