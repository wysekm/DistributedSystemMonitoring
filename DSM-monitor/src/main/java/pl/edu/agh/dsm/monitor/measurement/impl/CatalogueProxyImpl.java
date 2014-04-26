package pl.edu.agh.dsm.monitor.measurement.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.monitor.measurement.CatalogueProxy;

import java.util.UUID;

@Component
public class CatalogueProxyImpl implements CatalogueProxy {

	static final Logger logger = LoggerFactory
			.getLogger(CatalogueProxyImpl.class);

	@Override
	public void addMeasurement(MeasurementDto uuid) {
		logger.debug("send info about new measurement with id {}", uuid);
	}

	@Override
	public void removeMeasurement(UUID uuid) {
		logger.debug("send info about removed measurement with id {}", uuid);
	}
}
