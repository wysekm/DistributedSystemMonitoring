package pl.edu.agh.dsm.monitor.core.infrastructure;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.dsm.monitor.core.model.measurement.CatalogueProxy;
import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class BasicAuthInterceptor implements ClientHttpRequestInterceptor {

	private final String username;
	private final String password;

	public BasicAuthInterceptor( String username, String password ) {
		this.username = username;
		this.password = password;
	}

	@Override
	public ClientHttpResponse intercept( HttpRequest request, byte[] body, ClientHttpRequestExecution execution ) throws IOException {
		final String auth = username + ":" + password;
		final byte[] encodedAuth = Base64.encodeBase64( auth.getBytes( Charset.forName("US-ASCII") ) );
		final String authHeader = "Basic " + new String( encodedAuth );
		request.getHeaders().add( "Authorization", authHeader );
		return execution.execute( request, body );
	}
}

@Component
public class CatalogueProxyImpl implements CatalogueProxy {

	static final Logger logger = LoggerFactory
			.getLogger(CatalogueProxyImpl.class);

	@Value("${catalogue.measurements.address}")
	String catalogueMeasurementsUri;
	
	@Value("${monitor.username}")
	String monitorUsername;
	
	@Value("${monitor.password}")
	String monitorPassoword;
	
	RestTemplate restTemplate;
	
	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
		final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add( new BasicAuthInterceptor( monitorUsername, monitorPassoword ) );
		restTemplate.setInterceptors( interceptors );
	}

	@Override
	public void addMeasurement(Measurement dto) {
		logger.debug("send info about new measurement with id {}", dto);
		try {
			restTemplate.postForObject(catalogueMeasurementsUri, dto, Object.class);
		} catch (RestClientException e) {
			throw new InternalErrorException("Cannot connect to the Catalog service", HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@Override
	public void removeMeasurement(UUID uuid) {
		logger.debug("send info about removed measurement with id {}", uuid);
		String uri = catalogueMeasurementsUri + "/" + uuid;
		try {
			restTemplate.delete(uri);
		} catch (RestClientException e) {
			throw new InternalErrorException("Cannot connect to the Catalog service", HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
