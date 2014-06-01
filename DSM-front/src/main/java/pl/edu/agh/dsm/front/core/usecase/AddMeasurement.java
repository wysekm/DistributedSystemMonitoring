package pl.edu.agh.dsm.front.core.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.dsm.front.core.model.rest.RestClientService;
import pl.edu.agh.dsm.front.core.model.rest.UserCredentials;
import pl.edu.agh.dsm.front.core.model.rest.dto.ComplexMeasurementOutDto;

/**
 * Created by Tom on 2014-05-26.
 */
//@UseCase
public class AddMeasurement {

	@Autowired
	RestClientService restClientService;

	public void addMeasurement(String addUri, ComplexMeasurementOutDto measurement, UserCredentials user) {
		restClientService.addMeasurement(addUri, measurement, user);
	}
}
