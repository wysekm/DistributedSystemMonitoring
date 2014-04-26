package pl.edu.agh.dsm.sensor.monitored;

import pl.edu.agh.dsm.sensor.monitored.impl.MonitoredCpu;

public class MonitoredFactory {

	public MonitoredResource createMonitoredResource(String metricName)
			throws MonitoringException {
		if (MonitoredCpu.METRIC_NAME.equals(metricName)) {
			return new MonitoredCpu();
		} else {
			throw new MonitoringException("Unknown metric: " + metricName);
		}
	}
}
