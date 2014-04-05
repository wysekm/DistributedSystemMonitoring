package pl.edu.agh.dsm.sensor.impl;

import pl.edu.agh.dsm.sensor.iface.MonitoredResource;
import pl.edu.agh.dsm.sensor.iface.MonitoringException;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class CpuUsage implements MonitoredResource {

    private Sigar sigar;
    private int cpu = -1;

    public CpuUsage() {
        sigar = new Sigar();
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    @Override
    public Double checkResource() throws MonitoringException {
        try {
            if (cpu < 0) {
                CpuPerc cpuPerc = sigar.getCpuPerc();
                return cpuPerc.getCombined();
            } else {
                try {
                    CpuPerc[] cpuPercs = sigar.getCpuPercList();
                    return cpuPercs[cpu].getCombined();
                } catch (IndexOutOfBoundsException ex) {
                    throw new MonitoringException("CPU index provided in plugin configuration (" + cpu
                            + ") does not correspond to existing CPU.", ex, true);
                }
            }
        } catch (SigarException ex) {
            throw new MonitoringException("Sigar exception", ex, false);
        }
    }
}
