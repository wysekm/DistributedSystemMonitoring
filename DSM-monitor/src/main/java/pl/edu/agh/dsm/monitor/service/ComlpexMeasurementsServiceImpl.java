package pl.edu.agh.dsm.monitor.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import pl.edu.agh.dsm.common.ActionPossibility;
import pl.edu.agh.dsm.monitor.dto.ComplexMeasurementDto;

@Service
public class ComlpexMeasurementsServiceImpl implements
		ComplexMeasurementsService {
	
	@Override
	public void create(ComplexMeasurementDto measurement) {
/*		ApplicationUser applicationUser = autorizationContext.getActiveUser();
		MeasurementDto measurementDto = complexMeasurementTaskFactory.create(
				complexMeasurementDto, applicationUser);
		measurementRepository.save(measurementDto);
		catalogueProxy.addMeasurement(measurementDto);*/
	}

	@Override
	public void delete(UUID uuid) {
/*		check(canDelete(uuid));
		measurementRepository.remove(uuid);
		catalogueProxy.removeMeasurement(uuid);*/
	}

	@Override
	public ActionPossibility canDelete(UUID uuid) {
		/*boolean havePerm = measurementPermisionResolver.canRemove(uuid);
		return ActionPossibility.makeDecision(havePerm,
				"Podany uzytkownik nie moze usunac tego pomiaru");*/
		return null;
	}

	@Override
	public ComplexMeasurementDto getDetails(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isComplex(UUID uuid) {
		// TODO Auto-generated method stub
		return false;
	}

}
