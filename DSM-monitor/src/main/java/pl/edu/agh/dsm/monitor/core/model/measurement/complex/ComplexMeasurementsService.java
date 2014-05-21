package pl.edu.agh.dsm.monitor.core.model.measurement.complex;

import java.util.UUID;

import org.springframework.stereotype.Service;

import pl.edu.agh.dsm.monitor.core.model.user.ApplicationUser;

@Service
public class ComplexMeasurementsService {
	
	public void create(ComplexMeasurement measurement) {
/*		ApplicationUser applicationUser = autorizationContext.getActiveUser();
		MeasurementDto measurementDto = complexMeasurementTaskFactory.create(
				complexMeasurementDto, applicationUser);
		measurementRepository.save(measurementDto);
		catalogueProxy.addMeasurement(measurementDto);*/
	}

	public void delete(UUID uuid) {
/*		check(canDelete(uuid));
		measurementRepository.remove(uuid);
		catalogueProxy.removeMeasurement(uuid);*/
	}

	public boolean canDelete(UUID uuid, ApplicationUser user) {
		/*boolean havePerm = measurementPermisionResolver.canRemove(uuid);
		return ActionPossibility.makeDecision(havePerm,
				"Podany uzytkownik nie moze usunac tego pomiaru");*/
		return false;
	}

	public ComplexMeasurement getDetails(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isComplex(UUID uuid) {
		// TODO Auto-generated method stub
		return false;
	}

}
