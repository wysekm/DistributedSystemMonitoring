package pl.edu.agh.dsm.catalog.core.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.catalog.core.infrastructure.annotation.UseCase;
import pl.edu.agh.dsm.catalog.core.model.resource.ResourcesService;
import pl.edu.agh.dsm.catalog.core.model.resource.SystemResource;

@UseCase
public class GetResources {
	
	@Autowired
	ResourcesService resourceService;
	
	public List<SystemResource> getList() {
		return resourceService.getList();
	}
}
