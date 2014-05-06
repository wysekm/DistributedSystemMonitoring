package pl.edu.agh.dsm.common.repository.predicate;

import com.google.common.base.Predicate;

import pl.edu.agh.dsm.common.dto.MeasurementDto;

//fabryka predykatow do filtrowania wynikow repozytorium
public interface MeasurementPredicateFactory {
	Predicate<MeasurementDto> createForMeasurement(String metric,
			String resource);
}
