package pl.edu.agh.dsm.sensor.monitored;

import pl.edu.agh.dsm.sensor.monitored.impl.MonitoredCpu;

public class MonitoredFactory {

	public MonitoredResource createMonitoredResource(String metricName)
			throws MonitoringException {
		if (MonitoredCpu.METRIC_NAME.equals(metricName)) {
			return new MonitoredCpu();
		} else if (MonitoredActualMem.METRIC_NAME.equals(metricName)) {
			return new MonitoredActualMem();
		} else if (MonitoredActualFreeMem.METRIC_NAME.equals(metricName)) {
			return new MonitoredActualFreeMem();
		} else if (MonitoredPercentageFreeMem.METRIC_NAME.equals(metricName)) {
			return new MonitoredPercentageFreeMem();
		} else if (MonitoredPercentageUsedMem.METRIC_NAME.equals(metricName)) {
			return new MonitoredPercentageUsedMem();
		} else {
			throw new MonitoringException("Unknown metric: " + metricName);
		}
	}
}
