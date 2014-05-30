package pl.edu.agh.dsm.monitor.core.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import pl.edu.agh.dsm.monitor.core.infrastructure.ActionPossibility;
import pl.edu.agh.dsm.monitor.core.infrastructure.InternalErrorException;
import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.UseCase;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurementsService;
import pl.edu.agh.dsm.monitor.core.model.user.ApplicationUser;

@UseCase("UC_PF_MT4F")
public class DeleteComplexMeasurement {

	@Autowired
	ComplexMeasurementsService complexMeasurementService;
	
	public void delete(UUID uuid, ApplicationUser user) {
		InternalErrorException.check(canDelete(uuid, user));
		complexMeasurementService.delete(uuid);
	}
	
	public ActionPossibility canDelete(UUID uuid, ApplicationUser user) {
		if(user == null) {
			return ActionPossibility.forFalse("Only logged users can delete measurements", HttpStatus.UNAUTHORIZED);
		}
		boolean possible = complexMeasurementService.getDetails(uuid).getCreatedBy().equals(user);
		String reason = String.format("User [%s]  didn't create measurement [%s]", user, uuid);
		return new ActionPossibility(possible, reason, HttpStatus.FORBIDDEN);
	}
}
