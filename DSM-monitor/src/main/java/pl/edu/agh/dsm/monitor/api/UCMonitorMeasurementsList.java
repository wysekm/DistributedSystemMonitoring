package pl.edu.agh.dsm.monitor.api;

import pl.edu.agh.dsm.monitor.Measurement;

import java.util.List;

public interface UCMonitorMeasurementsList {
    public List<Measurement> filter(String metric, String resource);

}
