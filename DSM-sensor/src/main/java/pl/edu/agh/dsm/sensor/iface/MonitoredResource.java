package pl.edu.agh.dsm.sensor.iface;

public interface MonitoredResource {

    Double checkResource() throws MonitoringException;

}
