package pl.edu.agh.dsm.monitor.sensor.mocks;

import java.util.Random;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.MockComponent;
import pl.edu.agh.dsm.monitor.sensor.controller.SensorController;
import pl.edu.agh.dsm.monitor.sensor.view.dto.SensorDataDto;

@MockComponent
public class SensorActivityMock implements Runnable {

	static final Logger logger = LoggerFactory.getLogger(SensorActivityMock.class);
	
	private Thread thread;
	
	@Autowired
	private SensorController sensorController;
	
	private UUID[] uuids = { UUID.fromString("0cb419a8-aa9b-41ba-ad33-4869eaa6581b"), 
			UUID.fromString("ad3a085b-a849-491f-9708-821ba02ae7a6") , 
			UUID.fromString("51428fa6-4a2d-47f4-940f-fa8d9cd916ca") };
	private String[] metrics = { "mem", "mem", "cpu" };
	private String[] resources = { "localhost", "zeus", "zeus" };
	private String[] units = { "MB", "MB", "%" };
	private Random random = new Random();
	
	@PostConstruct
	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		logger.debug("Started Sensor Mock");
		try {
			while(true) {
				SensorDataDto data = generateMeasurement();
				sensorController.processSensorData(data);
				Thread.sleep(4000);
			}
		} catch (InterruptedException e) {
		} finally {
			logger.debug("Sensor Mock Ended");
		}
	}
	
	private SensorDataDto generateMeasurement() {
		SensorDataDto dto =  new SensorDataDto();
		int draw = random.nextInt(3);
		dto.setId(uuids[draw]);
		dto.setMetric(metrics[draw]);
		dto.setResource(resources[draw]);
		dto.setUnit(units[draw]);
		dto.setTimestamp(new java.util.Date().getTime());
		dto.setValue(new Random().nextInt(100));
		return dto;
	}

	@PreDestroy
	public void destroy() {
		thread.interrupt();
	}
}
