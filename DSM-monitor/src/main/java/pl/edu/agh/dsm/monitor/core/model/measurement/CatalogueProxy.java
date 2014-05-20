package pl.edu.agh.dsm.monitor.core.model.measurement;

import java.util.UUID;

public interface CatalogueProxy {
	void addMeasurement(Measurement uuid);

	void removeMeasurement(UUID uuid);
}
