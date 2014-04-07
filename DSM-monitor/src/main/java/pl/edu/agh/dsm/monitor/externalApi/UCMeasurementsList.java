package pl.edu.agh.dsm.monitor.externalApi;

import pl.edu.agh.dsm.monitor.dto.Measurement;

import java.util.List;

public interface UCMeasurementsList {
    public List<Measurement> filter(String metric, String resource);

}
