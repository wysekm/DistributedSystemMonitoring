package pl.edu.agh.dsm.catalog.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Predicate;

import pl.edu.agh.dsm.common.InternalErrorException;
import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.measurement.MeasurementPredicateFactory;
import pl.edu.agh.dsm.common.measurement.MeasurementRepository;

@Service
public class MeasurementsServiceImpl implements MeasurementsService {

	@Autowired
	MeasurementRepository measurementRepository;
	@Autowired
	MeasurementPredicateFactory preconditionFactory;

	@Override
	public List<MeasurementDto> getMeasurements(String metric, String resource)
			throws InternalErrorException {

		Predicate<MeasurementDto> preconditions = preconditionFactory
				.createForMeasurement(metric, resource);
		return measurementRepository.findAll(preconditions);
	}

	@Override
	public void deleteMeasurement(UUID id) throws InternalErrorException {
		measurementRepository.remove(id);
	}

	@Override
	public MeasurementDto addMeasurement(MeasurementDto measurement)
			throws InternalErrorException {
		measurementRepository.save(measurement);
		return measurement;
	}

}
