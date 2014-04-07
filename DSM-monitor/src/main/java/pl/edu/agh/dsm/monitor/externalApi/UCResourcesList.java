package pl.edu.agh.dsm.monitor.externalApi;

import pl.edu.agh.dsm.monitor.dto.Measurement;
import pl.edu.agh.dsm.monitor.dto.SystemResource;

import java.util.List;

public interface UCResourcesList {
    public List<SystemResource> list();

}
