package pl.edu.agh.dsm.monitor.measurement;

import pl.edu.agh.dsm.monitor.dto.SystemResourceDto;

import java.util.List;

public interface SystemResourceRepository {
    List<SystemResourceDto> findAll();
}
