package pl.edu.agh.dsm.catalog.core.model.measurement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.agh.dsm.catalog.core.infrastructure.InternalErrorException;

import com.google.common.base.Predicate;

@Service
public class MeasurementService {
	
	@Autowired
	MeasurementPredicateFactory precondictionFactory;
	
	@Autowired
	MeasurementRepository measurementRepository;
	
	public List<Measurement> getMeasurements(String metric, String resource)
			throws InternalErrorException {
		Predicate<Measurement> preconditions = precondictionFactory
				.createForMeasurement(metric, resource);
		return measurementRepository.findAll(preconditions);
	}

	public void deleteMeasurement(UUID id) throws InternalErrorException {
		measurementRepository.remove(id);
	}

	public Measurement addMeasurement(Measurement measurement)
			throws InternalErrorException {
		measurementRepository.save(measurement);
		return measurement;
	}
}
