package pl.edu.agh.dsm.front.service;

import java.util.Collection;

import org.springframework.hateoas.Resource;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.dto.SystemResourceDto;

public interface CatalogueRestClientService {
	Collection<Resource<MeasurementDto>> getMeasurements(String metricName, String resourceName);
	Collection<Resource<SystemResourceDto>> getSystemResources();
}
