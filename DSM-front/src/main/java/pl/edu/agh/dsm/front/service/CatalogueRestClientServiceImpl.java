package pl.edu.agh.dsm.front.service;

import java.util.Collection;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.edu.agh.dsm.front.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.front.dto.ComplexTypeDto;
import pl.edu.agh.dsm.front.dto.MeasurementDto;
import pl.edu.agh.dsm.front.dto.SystemResourceDto;

@Service
public class CatalogueRestClientServiceImpl implements CatalogueRestClientService {
	
	@Value("${measurements.uri}")
	String measurementsUri;
	
	@Value("${resources.uri}")
	String resourcesUri;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public Collection<Resource<MeasurementDto>> getMeasurements(String metricName, String resourceName, User user) {
		ParameterizedTypeReference<Resources<Resource<MeasurementDto>>> typeRef = 
				new ParameterizedTypeReference<Resources<Resource<MeasurementDto>>>() {};
		HttpHeaders headers = prepareHeaderWithAuth(user);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		return restTemplate.exchange(measurementsUri, HttpMethod.GET, request, typeRef, metricName, resourceName).
				getBody().getContent();
	}

	@Override
	public Collection<Resource<SystemResourceDto>> getSystemResources() {
		ParameterizedTypeReference<Resources<Resource<SystemResourceDto>>> typeRef = 
				new ParameterizedTypeReference<Resources<Resource<SystemResourceDto>>>() {};
		return restTemplate.exchange(resourcesUri, HttpMethod.GET, null, typeRef).
				getBody().getContent();
	}

	@Override
	public Collection<Resource<ComplexTypeDto>> getAvailableComplexTypes(String monitorAddress) {
		ParameterizedTypeReference<Resources<Resource<ComplexTypeDto>>> typeRef =
				new ParameterizedTypeReference<Resources<Resource<ComplexTypeDto>>>() {};
		String complexOptionUri = monitorAddress + "/complex";
		return restTemplate.exchange(complexOptionUri, HttpMethod.OPTIONS, null, typeRef).
				getBody().getContent();
	}

	@Override
	public Resource<ComplexMeasurementDto> getComplexDetails(String detailsUri) {
		ParameterizedTypeReference<Resource<ComplexMeasurementDto>> typeRef =
				new ParameterizedTypeReference<Resource<ComplexMeasurementDto>>() {};
		return restTemplate.exchange(detailsUri, HttpMethod.GET, null, typeRef).
				getBody();
	}

	private static HttpHeaders prepareHeaderWithAuth(User user) {
		HttpHeaders headers = new HttpHeaders();
		if(user != null) {
			String base64Creds = getBase64Creds(user.getUsername(), user.getPassword());
			headers.add("Authorization", "Basic " + base64Creds);
		}
		return headers;
	}

	private static String getBase64Creds(String username, String password) {
		String plainCreds = username + ":" + password;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		return new String(base64CredsBytes);
	}


}
