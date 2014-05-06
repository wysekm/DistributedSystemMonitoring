package pl.edu.agh.dsm.catalog.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.access.annotation.Secured;

import pl.edu.agh.dsm.common.InternalErrorException;
import pl.edu.agh.dsm.common.dto.MeasurementDto;

public interface MeasurementsService {

	List<MeasurementDto> getMeasurements(String metric, String resource)
			throws InternalErrorException;

	@Secured({ "ROLE_MONITOR" })
	void deleteMeasurement(UUID id) throws InternalErrorException;

	@Secured({ "ROLE_MONITOR" })
	MeasurementDto addMeasurement(MeasurementDto measurement)
			throws InternalErrorException;
}
