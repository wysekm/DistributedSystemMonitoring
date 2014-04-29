package pl.edu.agh.dsm.monitor.mocks;

import java.util.Random;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.common.annotations.GuiMockComponent;
import pl.edu.agh.dsm.monitor.dto.SimpleMeasurementDataDto;
import pl.edu.agh.dsm.monitor.service.MeasurementsService;

@GuiMockComponent
public class SensorActivityMock implements Runnable {

	static final Logger logger = LoggerFactory.getLogger(SensorActivityMock.class);
	
	private Thread thread;
	
	@Autowired
	private MeasurementsService measurementService;
	
	private UUID[] uuids = { UUID.fromString("0cb419a8-aa9b-41ba-ad33-4869eaa6581b"), 
			UUID.fromString("ad3a085b-a849-491f-9708-821ba02ae7a6") , 
			UUID.fromString("51428fa6-4a2d-47f4-940f-fa8d9cd916ca") };
	private String[] metrics = { "mem", "mem", "cpu" };
	private String[] resources = { "localhost", "zeus", "zeus" };
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
				SimpleMeasurementDataDto data = generateMeasurement();
				measurementService.addNewData(data);
				Thread.sleep(4000);
			}
		} catch (InterruptedException e) {
		} finally {
			logger.debug("Sensor Mock Ended");
		}
	}
	
	private SimpleMeasurementDataDto generateMeasurement() {
		SimpleMeasurementDataDto dto =  new SimpleMeasurementDataDto();
		int draw = random.nextInt(3);
		dto.setId(uuids[draw]);
		dto.setMetric(metrics[draw]);
		dto.setResource(resources[draw]);
		dto.setTimestamp(new java.util.Date().getTime());
		dto.setValue(new Random().nextLong());
		return dto;
	}

	@PreDestroy
	public void destroy() {
		thread.interrupt();
	}
}
