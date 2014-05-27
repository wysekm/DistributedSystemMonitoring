package pl.edu.agh.dsm.front.core.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.dsm.front.core.model.rest.RestClientService;
import pl.edu.agh.dsm.front.core.model.rest.RestClientServiceException;
import pl.edu.agh.dsm.front.core.model.rest.UserCredentials;
import pl.edu.agh.dsm.front.core.model.rest.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.front.core.model.rest.dto.ComplexTypeDto;
import pl.edu.agh.dsm.front.core.model.rest.dto.MeasurementDto;
import pl.edu.agh.dsm.front.core.model.rest.dto.SystemResourceDto;

import java.util.Collection;

/**
 * Created by Tom on 2014-05-26.
 */
@Service
public class RestClientServiceImpl implements RestClientService {

	static final Logger log = LoggerFactory.getLogger(RestClientServiceImpl.class);

	// TODO put in messages properties
	String catalogueConnectionErrorMsg = "Catalogue Service is temporary unavailable. Please try again later.";

	@Autowired
	RestTemplateFactory restTemplateFactory;

	@Override
	public Collection<Resource<MeasurementDto>> getCatalogueMeasurements(String uri, String metricName, String resourceName) {
		ParameterizedTypeReference<Resources<Resource<MeasurementDto>>> typeRef =
				new ParameterizedTypeReference<Resources<Resource<MeasurementDto>>>() {};
		RestTemplate restTemplate = restTemplateFactory.create();
		try {
			return restTemplate.exchange(uri, HttpMethod.GET, null,
					typeRef, metricName, resourceName).getBody().getContent();
		} catch(RestClientException e) {
			log.error("Couldn't get catalogue measurements:" + e.getMessage());
			throw new RestClientServiceException(catalogueConnectionErrorMsg,e);
		}
	}

	@Override
	public Resource<MeasurementDto> getMonitorMeasurement(String uri, UserCredentials user) {
		ParameterizedTypeReference<Resource<MeasurementDto>> typeRef =
				new ParameterizedTypeReference<Resource<MeasurementDto>>() {};
		RestTemplate restTemplate = restTemplateFactory.create(user);
		return restTemplate.exchange(uri, HttpMethod.GET, null, typeRef).getBody();
	}

	@Override
	public Collection<Resource<SystemResourceDto>> getSystemResources(String uri) {
		ParameterizedTypeReference<Resources<Resource<SystemResourceDto>>> typeRef =
				new ParameterizedTypeReference<Resources<Resource<SystemResourceDto>>>() {};
		RestTemplate restTemplate = restTemplateFactory.create();
		try {
			return restTemplate.exchange(uri, HttpMethod.GET, null, typeRef)
					.getBody().getContent();
		} catch(RestClientException e) {
			log.error("Couldn't get resources:" + e.getMessage());
			throw new RestClientServiceException(catalogueConnectionErrorMsg,e);
		}
	}

	@Override
	public Collection<Resource<ComplexTypeDto>> getAvailableComplexTypes(String uri) {
		ParameterizedTypeReference<Resources<Resource<ComplexTypeDto>>> typeRef =
				new ParameterizedTypeReference<Resources<Resource<ComplexTypeDto>>>() {};
		RestTemplate restTemplate = restTemplateFactory.create();
		return restTemplate.exchange(uri, HttpMethod.OPTIONS, null, typeRef).
				getBody().getContent();
	}

	@Override
	public Resource<ComplexMeasurementDto> getComplexDetails(String uri) {
		ParameterizedTypeReference<Resource<ComplexMeasurementDto>> typeRef =
				new ParameterizedTypeReference<Resource<ComplexMeasurementDto>>() {};
		RestTemplate restTemplate = restTemplateFactory.create();
		return restTemplate.exchange(uri, HttpMethod.GET, null, typeRef).
				getBody();
	}

	@Override
	public void addMeasurement(String uri, ComplexMeasurementDto measurement, UserCredentials user) {
		RestTemplate restTemplate = restTemplateFactory.create(user);
		restTemplate.postForObject(uri, measurement, Object.class);
	}

	@Override
	public void deleteMeasurement(String deleteUri, UserCredentials user) {
		RestTemplate restTemplate = restTemplateFactory.create(user);
		restTemplate.delete(deleteUri);
	}
}
