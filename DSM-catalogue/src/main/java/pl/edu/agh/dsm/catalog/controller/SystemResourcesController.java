package pl.edu.agh.dsm.catalog.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.dto.SystemResourceDto;
import pl.edu.agh.dsm.common.service.ResourcesService;

@RestController
@ExposesResourceFor(SystemResourceDto.class)
@RequestMapping("resources")
public class SystemResourcesController {

	@Autowired
	private ResourcesService service;

	@Autowired
	private EntityLinks entityLinks;

	@RequestMapping(method = GET, value = "")
	public Resources<Resource<SystemResourceDto>> getResources() {

		List<SystemResourceDto> list = service.getList();
		List<Resource<SystemResourceDto>> resources = new ArrayList<>();
		for (SystemResourceDto resource : list) {
			Resource<SystemResourceDto> element = new Resource<>(resource);
			element.add(constructLink(resource));
			resources.add(element);
		}
		return new Resources<Resource<SystemResourceDto>>(resources);
	}

	private Link constructLink(SystemResourceDto resource) {
		String href = entityLinks
				.linkToCollectionResource(MeasurementDto.class).getHref();
		href += "?resource=" + resource.getName();
		return new Link(href, "measurements");
	}

}
