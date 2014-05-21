package pl.edu.agh.dsm.catalog.core.usecase;

import static pl.edu.agh.dsm.catalog.core.infrastructure.InternalErrorException.check;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.catalog.core.infrastructure.ActionPossibility;
import pl.edu.agh.dsm.catalog.core.infrastructure.annotation.UseCase;
import pl.edu.agh.dsm.catalog.core.model.measurement.MeasurementService;
import pl.edu.agh.dsm.catalog.core.model.user.ApplicationUser;
import pl.edu.agh.dsm.catalog.core.model.user.UserAuthorization;

@UseCase
public class DeleteMeasurement {
	
	@Autowired
	UserAuthorization userAuthorization;
	
	@Autowired
	MeasurementService measurementService;
	
	public void deleteMeasurement(UUID id, ApplicationUser user) {
		check(canDelete(user));
		measurementService.deleteMeasurement(id);
	}
	
	public ActionPossibility canDelete(ApplicationUser user) {
		return userAuthorization.isAuthorized(user);
	}
}
