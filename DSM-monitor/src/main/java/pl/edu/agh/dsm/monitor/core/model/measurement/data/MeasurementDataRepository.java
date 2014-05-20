package pl.edu.agh.dsm.monitor.core.model.measurement.data;

import java.util.List;
import java.util.UUID;

import com.google.common.base.Predicate;

public interface MeasurementDataRepository {
	List<MeasurementData> find(UUID uuid,
			Predicate<MeasurementData> preconditions);

	void add(UUID uuid, MeasurementData measurementDataDto);

	void remove(UUID uuid, MeasurementData measurementDataDto);
}
