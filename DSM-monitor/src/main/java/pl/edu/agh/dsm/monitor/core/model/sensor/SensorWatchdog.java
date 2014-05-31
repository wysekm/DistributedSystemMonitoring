package pl.edu.agh.dsm.monitor.core.model.sensor;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.MeasurementService;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurementsService;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.DataLimit;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;

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
		List<Measurement> list = measurementService.getList(null, null);
		for (int i = 0; i < list.size(); i++) {
		if(!complexMeasurementsService.isComplex(list.get(i).getId())){
		List<MeasurementData> data = measurementService.getData(list.get(i).getId(), DataLimit.last, 1);
			if(!data.isEmpty()){
				if(System.currentTimeMillis() - data.get(0).getTimestamp() > TIMEOUT) {
					measurementService.deleteMeasurement(list.get(i).getId());
					}
				} 
			}
		}
	}
}
