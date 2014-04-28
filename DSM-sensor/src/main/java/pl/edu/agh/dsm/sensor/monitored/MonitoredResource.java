package pl.edu.agh.dsm.sensor.monitored;

import org.hyperic.sigar.Sigar;

import java.util.UUID;

public abstract class MonitoredResource {

	private String metric;
	private String id;
	protected Sigar sigar;

	public MonitoredResource(String metric) {
		this.metric = metric;
		this.id = UUID.randomUUID().toString();
		this.sigar = new Sigar();
	}

	public abstract double checkValue() throws MonitoringException;

	public String getId() {
		return id;
	}

	public String getMetric() {
		return metric;
	}

}
