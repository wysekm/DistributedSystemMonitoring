package pl.edu.agh.dsm.catalog.service;

import java.util.List;
import java.util.UUID;

import pl.edu.agh.dsm.common.InternalErrorException;
import pl.edu.agh.dsm.common.dto.MeasurementDto;

public interface MeasurementsService {

	List<MeasurementDto> getMeasurements(String metric, String resource) throws InternalErrorException;
	void deleteMeasurement(UUID id) throws InternalErrorException;
	MeasurementDto addMeasurement(MeasurementDto measurement) throws InternalErrorException;
}
