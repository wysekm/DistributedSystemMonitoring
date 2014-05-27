package pl.edu.agh.dsm.front.core.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Resource;
import pl.edu.agh.dsm.front.core.infrastructure.UseCase;
import pl.edu.agh.dsm.front.core.model.rest.RestClientService;
import pl.edu.agh.dsm.front.core.model.rest.dto.SystemResourceDto;

import java.util.Collection;

/**
 * Created by Tom on 2014-05-26.
 */
//@UseCase
public class GetSystemResources {

	@Autowired
	RestClientService restClientService;

	@Value("${resources.uri}")
	String resourcesUri;

	public Collection<Resource<SystemResourceDto>> getResources() {
		return restClientService.getSystemResources(resourcesUri);
	}
}
