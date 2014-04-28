package pl.edu.agh.dsm.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.agh.dsm.common.dto.SystemResourceDto;
import pl.edu.agh.dsm.common.measurement.SystemResourceRepository;

@Service
public class ResourcesServiceImpl implements ResourcesService {

	@Autowired
	private SystemResourceRepository resourceRepository;

	@Override
	public List<SystemResourceDto> getResources() {
		return resourceRepository.findAll();
	}

}
