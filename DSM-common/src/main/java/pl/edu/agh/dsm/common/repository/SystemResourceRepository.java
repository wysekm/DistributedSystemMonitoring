package pl.edu.agh.dsm.common.repository;

import pl.edu.agh.dsm.common.dto.SystemResourceDto;

import java.util.List;

//repozytorium zasobów. Zwraca liste unikalnych zasobów
public interface SystemResourceRepository {
	List<SystemResourceDto> findAll();
}
