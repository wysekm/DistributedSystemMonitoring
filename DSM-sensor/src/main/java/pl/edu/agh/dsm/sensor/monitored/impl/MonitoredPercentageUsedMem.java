package pl.edu.agh.dsm.sensor.monitored.impl;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import pl.edu.agh.dsm.sensor.monitored.MonitoredResource;
import pl.edu.agh.dsm.sensor.monitored.MonitoringException;

public class MonitoredPercentageUsedMem extends MonitoredResource {

	public static final String METRIC_NAME = "percentUsedMem";

	public MonitoredPercentageUsedMem() {
		super(METRIC_NAME);
	}

	@Override
	public double checkValue() throws MonitoringException {
		try {
			return new Sigar().getMem().getUsedPercent();
		} catch (SigarException ex) {
			throw new MonitoringException(
					"SigarException caught while performing resource check: ",
					ex
			);
		}
	}

	@Override
	public String getUnit() {
		return "%";
	}

}
