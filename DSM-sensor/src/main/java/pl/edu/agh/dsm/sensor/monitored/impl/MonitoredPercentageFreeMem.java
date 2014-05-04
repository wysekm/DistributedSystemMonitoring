package pl.edu.agh.dsm.sensor.monitored.impl;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import pl.edu.agh.dsm.sensor.monitored.MonitoredResource;
import pl.edu.agh.dsm.sensor.monitored.MonitoringException;

public class MonitoredPercentageFreeMem extends MonitoredResource {

	public static final String METRIC_NAME = "percentFreeMem";

	public MonitoredPercentageFreeMem() {
		super(METRIC_NAME);
	}

	@Override
	public double checkValue() throws MonitoringException {
		try {
			return new Sigar().getMem().getFreePercent();
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
