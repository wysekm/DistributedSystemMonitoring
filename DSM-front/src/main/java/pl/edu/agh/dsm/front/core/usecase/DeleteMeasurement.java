package pl.edu.agh.dsm.front.core.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.dsm.front.core.infrastructure.UseCase;
import pl.edu.agh.dsm.front.core.model.rest.RestClientService;
import pl.edu.agh.dsm.front.core.model.rest.UserCredentials;

/**
 * Created by Tom on 2014-05-26.
 */
//@UseCase
public class DeleteMeasurement {

	@Autowired
	RestClientService restClientService;

	public void deleteMeasurement(String deleteUri, UserCredentials user) {
		restClientService.deleteMeasurement(deleteUri, user);
	}
}
