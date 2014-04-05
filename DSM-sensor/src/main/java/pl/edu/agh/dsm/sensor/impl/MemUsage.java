package pl.edu.agh.dsm.sensor.impl;

import pl.edu.agh.dsm.sensor.iface.MonitoredResource;
import pl.edu.agh.dsm.sensor.iface.MonitoringException;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

@SuppressWarnings("UnusedDeclaration")
public class MemUsage implements MonitoredResource {

    private boolean percentage = false;
    private Sigar sigar;
    private boolean used = false;

    public MemUsage() {
        sigar = new Sigar();
    }

    public void setPercentage(boolean percentage) {
        this.percentage = percentage;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public Double checkResource() throws MonitoringException {
        try {
            Double retVal;
            Mem mem = sigar.getMem();
            if (percentage && used) {
                retVal = mem.getUsedPercent();
            } else if (percentage) {
                retVal = mem.getFreePercent();
            } else if (used) {
                retVal = mem.getUsed() * 1.0;
            } else {
                retVal = mem.getFree() * 1.0;
            }
            return retVal;
        } catch (SigarException ex) {
            throw new MonitoringException("Sigar exception", ex, false);
        }
    }
}
