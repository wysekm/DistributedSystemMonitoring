package pl.edu.agh.dsm.monitor.service;

import pl.edu.agh.dsm.monitor.dto.SimpleMeasurementDataDto;

public interface SensorDataReceiverService {

	public void processSensorData(SimpleMeasurementDataDto data);
}
