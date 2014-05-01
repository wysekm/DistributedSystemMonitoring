package pl.edu.agh.dsm.sensor.monitored;

import java.util.UUID;

public abstract class MonitoredResource {

	private String metric;
	private String id;

	public MonitoredResource(String metric) {
		this.metric = metric;
		this.id = UUID.randomUUID().toString();
	}

	public abstract double checkValue() throws MonitoringException;

	public String getId() {
		return id;
	}

	public String getMetric() {
		return metric;
	}

	public abstract String getUnit();

}
