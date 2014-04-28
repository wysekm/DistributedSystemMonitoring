package pl.edu.agh.dsm.sensor;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.dsm.sensor.udp.SensorSocket;
import pl.edu.agh.dsm.sensor.udp.UdpValueUpdate;
import pl.edu.agh.dsm.sensor.monitored.MonitoredResource;
import pl.edu.agh.dsm.sensor.monitored.MonitoringException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class SensorThread implements Runnable {

	private final Logger log = LoggerFactory.getLogger(SensorThread.class);
	private final MonitoredResource resource;
	private final SensorConfiguration configuration;
	private final SensorSocket socket;

	public SensorThread(MonitoredResource resource,
			SensorConfiguration configuration, SensorSocket sensorSocket) {
		this.resource = resource;
		this.configuration = configuration;
		this.socket = sensorSocket;
	}

	@Override
	public void run() {
		log.trace("Checking value of resource " + resource.getId() + "");
		Double result;
		try {
			result = resource.checkValue();
			announceNewValue(resource.getId(), resource.getMetric(), result);
			log.trace("Successfully checked value of resource "
					+ resource.getId() + ", got result: " + result);
		} catch (MonitoringException ex) {
			if (ex.isFatal()) {
				log.error(
						"MonitoredResource failure, most likely due to malformed configuration,"
								+ " see MonitoringException message and stacktrace for cause: ",
						ex);
				throw new RuntimeException("thread is kill. no.");
			} else {
				log.error(
						"Non-fatal MonitoringException caught while executing resource "
								+ "value update for sensor " + resource.getId()
								+ ": ", ex);
			}
		}
	}

	private void announceNewValue(String resourceId, String metric,
			Double resourceValue) {
		log.trace("Sending update to monitor server (resource id: "
				+ resourceId + ")");
		UdpValueUpdate val = new UdpValueUpdate();
		val.setId(resourceId);
		val.setResource(configuration.getResource());
		val.setMetric(metric);
		val.setTimestamp(new Date());
		val.setValue(resourceValue);
		socket.sendData(val);
	}
}
