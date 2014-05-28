package pl.edu.agh.dsm.monitor.core.model.sensor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.edu.agh.dsm.monitor.core.model.measurement.MeasurementService;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurementsService;

/**
 * Created by Tom on 2014-05-28.
 */
@Component
public class SensorWatchdog {

	static final Logger log = LoggerFactory.getLogger(SensorWatchdog.class);

	/**
	 * timeout in milliseconds
	 */
	public static final int TIMEOUT = 30000;

	@Autowired
	MeasurementService measurementService;

	@Autowired
	ComplexMeasurementsService complexMeasurementsService;

	@Scheduled(fixedDelay = TIMEOUT)
	public void execute() {
		// TODO implement this
	}
}
