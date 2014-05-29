package pl.edu.agh.dsm.monitor.core.infrastructure.repository;

import org.springframework.stereotype.Component;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurementsRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by Tom on 2014-05-29.
 */
@Component
public class ComplexMeasurementRepositoryImpl implements ComplexMeasurementsRepository {

	@Override
	public List<ComplexMeasurement> findAll() {
		return null;
	}

	@Override
	public ComplexMeasurement find(UUID uuid) {
		return null;
	}

	@Override
	public void remove(UUID uuid) {

	}

	@Override
	public void save(ComplexMeasurement measurement) {

	}
}
