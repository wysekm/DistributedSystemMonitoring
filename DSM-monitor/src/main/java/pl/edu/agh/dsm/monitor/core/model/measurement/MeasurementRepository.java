package pl.edu.agh.dsm.monitor.core.model.measurement;

import java.util.List;
import java.util.UUID;

import com.google.common.base.Predicate;

//proste repozytorium zasobów. Przechowuje zaosoby w aplikacji
public interface MeasurementRepository {
	List<Measurement> findAll(Predicate<Measurement> preconditions);

	Measurement find(UUID uuid);

	void remove(UUID uuid);

	void save(Measurement measurementDto);
}
