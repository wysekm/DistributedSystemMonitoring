package pl.edu.agh.dsm.front.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.dto.SystemResourceDto;

@Service
public class CatalogueRestClientServiceImpl implements CatalogueRestClientService {
	
	@Value("${measurements.uri}")
	String measurementsUri;
	
	@Value("${resources.uri}")
	String resourcesUri;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public Collection<Resource<MeasurementDto>> getMeasurements(String metricName, String resourceName) {
		ParameterizedTypeReference<Resources<Resource<MeasurementDto>>> typeRef = 
				new ParameterizedTypeReference<Resources<Resource<MeasurementDto>>>() {};
		return restTemplate.exchange(measurementsUri, HttpMethod.GET, null, typeRef, metricName, resourceName).
				getBody().getContent();
	}

	@Override
	public Collection<Resource<SystemResourceDto>> getSystemResources() {
		ParameterizedTypeReference<Resources<Resource<SystemResourceDto>>> typeRef = 
				new ParameterizedTypeReference<Resources<Resource<SystemResourceDto>>>() {};
		return restTemplate.exchange(resourcesUri, HttpMethod.GET, null, typeRef).
				getBody().getContent();
	}
}
