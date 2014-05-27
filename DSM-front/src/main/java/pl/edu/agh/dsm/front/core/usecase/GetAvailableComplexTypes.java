package pl.edu.agh.dsm.front.core.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import pl.edu.agh.dsm.front.core.infrastructure.UseCase;
import pl.edu.agh.dsm.front.core.model.rest.RestClientService;
import pl.edu.agh.dsm.front.core.model.rest.dto.ComplexTypeDto;

import java.util.Collection;

/**
 * Created by Tom on 2014-05-26.
 */
//@UseCase
public class GetAvailableComplexTypes {

	@Autowired
	RestClientService restClientService;

	public Collection<Resource<ComplexTypeDto>> getAvailableComplexTypes(String monitorAddress) {
		String complexOptionUri = monitorAddress + "/complex";
		return restClientService.getAvailableComplexTypes(complexOptionUri);
	}
}
