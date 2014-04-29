package pl.edu.agh.dsm.monitor.mocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.edu.agh.dsm.common.annotations.GuiMockComponent;
import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.monitor.internalApi.CatalogueProxy;

import java.util.UUID;

@GuiMockComponent
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
