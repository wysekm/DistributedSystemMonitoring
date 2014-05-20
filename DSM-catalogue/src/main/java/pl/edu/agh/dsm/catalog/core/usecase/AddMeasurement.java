package pl.edu.agh.dsm.catalog.core.usecase;

import static pl.edu.agh.dsm.catalog.core.infrastructure.InternalErrorException.check;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.catalog.core.infrastructure.ActionPossibility;
import pl.edu.agh.dsm.catalog.core.infrastructure.annotation.UseCase;
import pl.edu.agh.dsm.catalog.core.model.measurement.Measurement;
import pl.edu.agh.dsm.catalog.core.model.measurement.MeasurementService;
import pl.edu.agh.dsm.catalog.core.model.user.ApplicationUser;
import pl.edu.agh.dsm.catalog.core.model.user.UserAuthorization;

@UseCase
public class AddMeasurement {

	@Autowired
	UserAuthorization userAuthorization;
	
	@Autowired
	MeasurementService measurementService;
	
	public void addMeasurement(Measurement measurement, ApplicationUser user) {
		check(canAdd(user));
		measurementService.addMeasurement(measurement);
	}
	
	public ActionPossibility canAdd(ApplicationUser user) {
		return userAuthorization.isAuthorized(user);
	}
}
