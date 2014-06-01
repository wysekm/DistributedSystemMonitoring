package pl.edu.agh.dsm.monitor.core.model.measurement.complex;

import com.google.common.base.Predicate;

import java.util.List;
import java.util.UUID;

/**
 * Created by Tom on 2014-05-29.
 */
public interface ComplexMeasurementsRepository {

	List<ComplexMeasurement> find(Predicate<ComplexMeasurement> preconditions);

	ComplexMeasurement find(UUID uuid);

	void remove(UUID uuid);

	void save(ComplexMeasurement measurement);

}
