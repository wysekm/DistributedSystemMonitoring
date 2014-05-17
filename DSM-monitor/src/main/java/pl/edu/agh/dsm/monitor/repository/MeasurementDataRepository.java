package pl.edu.agh.dsm.monitor.repository;

import java.util.List;
import java.util.UUID;

import pl.edu.agh.dsm.common.dto.MeasurementDataDto;

import com.google.common.base.Predicate;

public interface MeasurementDataRepository {
	List<MeasurementDataDto> find(UUID uuid,
			Predicate<MeasurementDataDto> preconditions);

	void add(UUID uuid, MeasurementDataDto measurementDataDto);

	void remove(UUID uuid, MeasurementDataDto measurementDataDto);
}
