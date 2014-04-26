package pl.edu.agh.dsm.monitor.measurement.mocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.edu.agh.dsm.common.annotations.MockComponent;
import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.monitor.measurement.CatalogueProxy;

import java.util.UUID;

@MockComponent
public class MockCatalogueProxy implements CatalogueProxy {

	static final Logger logger = LoggerFactory
			.getLogger(MockCatalogueProxy.class);

	@Override
	public void addMeasurement(MeasurementDto uuid) {
		logger.debug("send info about new measurement with id {}", uuid);
	}

	@Override
	public void removeMeasurement(UUID uuid) {
		logger.debug("send info about removed measurement with id {}", uuid);
	}
}
