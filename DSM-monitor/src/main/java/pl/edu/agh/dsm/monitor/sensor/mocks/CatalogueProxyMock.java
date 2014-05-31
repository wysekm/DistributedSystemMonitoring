package pl.edu.agh.dsm.monitor.sensor.mocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.dsm.monitor.core.infrastructure.CatalogueProxyImpl;
import pl.edu.agh.dsm.monitor.core.infrastructure.InternalErrorException;
import pl.edu.agh.dsm.monitor.core.model.measurement.CatalogueProxy;
import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;

import java.util.UUID;

/**
 * Created by Tom on 2014-05-31.
 */
public class CatalogueProxyMock implements CatalogueProxy {

	static final Logger log = LoggerFactory.getLogger(CatalogueProxyMock.class);

	@Autowired
	CatalogueProxyImpl catalogueProxy;

	@Override
	public void addMeasurement(Measurement uuid) {
		try {
			catalogueProxy.addMeasurement(uuid);
		} catch (InternalErrorException e) {
			log.warn(e.getMessage());
		}
	}

	@Override
	public void removeMeasurement(UUID uuid) {
		try {
			catalogueProxy.removeMeasurement(uuid);
		} catch (InternalErrorException e) {
			log.warn(e.getMessage());
		}
	}
}
