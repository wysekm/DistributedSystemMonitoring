package pl.edu.agh.dsm.monitor.measurement;

import pl.edu.agh.dsm.monitor.dto.SystemResourceDto;

import java.util.List;

//repozytorium zasobów. Zwraca liste unikalnych zasobów
public interface SystemResourceRepository {
    List<SystemResourceDto> findAll();
}
