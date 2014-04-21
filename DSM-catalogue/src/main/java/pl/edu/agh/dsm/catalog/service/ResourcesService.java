package pl.edu.agh.dsm.catalog.service;

import java.util.List;

import pl.edu.agh.dsm.common.dto.SystemResourceDto;

public interface ResourcesService {
	
	List<SystemResourceDto> getResources();
}
