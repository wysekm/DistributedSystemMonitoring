package pl.edu.agh.dsm.monitor.measurement;

import pl.edu.agh.dsm.monitor.dto.SystemResource;

import java.util.List;

public interface SystemResourceRepository {
    List<SystemResource> findAll();
}
