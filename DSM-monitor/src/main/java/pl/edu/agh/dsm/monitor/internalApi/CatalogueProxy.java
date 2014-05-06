package pl.edu.agh.dsm.monitor.internalApi;

import pl.edu.agh.dsm.common.dto.MeasurementDto;

import java.util.UUID;

public interface CatalogueProxy {
	void addMeasurement(MeasurementDto uuid);

	void removeMeasurement(UUID uuid);
}
