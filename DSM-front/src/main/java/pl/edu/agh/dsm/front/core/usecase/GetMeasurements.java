package pl.edu.agh.dsm.front.core.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import pl.edu.agh.dsm.front.core.infrastructure.UseCase;
import pl.edu.agh.dsm.front.core.model.rest.DetailedMeasurementService;
import pl.edu.agh.dsm.front.core.model.rest.RestClientService;
import pl.edu.agh.dsm.front.core.model.rest.UserCredentials;
import pl.edu.agh.dsm.front.core.model.rest.dto.MeasurementDto;

import java.util.Collection;

/**
 * Created by Tom on 2014-05-26.
 */
//@UseCase
public class GetMeasurements {

	@Autowired
	DetailedMeasurementService detailedMeasurementService;

	public Collection<Resource<MeasurementDto>> getMeasurements(
			String metric, String resource, UserCredentials user) {
		return detailedMeasurementService.getDetailedMeasurements(metric, resource, user);
	}
}
