package pl.edu.agh.dsm.sensor.monitored.impl;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import pl.edu.agh.dsm.sensor.monitored.MonitoredResource;
import pl.edu.agh.dsm.sensor.monitored.MonitoringException;

public class MonitoredActualMem extends MonitoredResource {

	public static final String METRIC_NAME = "actualMemUsage";

	public MonitoredActualMem() {
		super(METRIC_NAME);
	}

	@Override
	public double checkValue() throws MonitoringException {
		try {
			return new Sigar().getMem().getActualUsed();
		} catch (SigarException ex) {
			throw new MonitoringException(
					"SigarException caught while performing resource check: ",
					ex
			);
		}
	}

	@Override
	public String getUnit() {
		return "MB";
	}

}
