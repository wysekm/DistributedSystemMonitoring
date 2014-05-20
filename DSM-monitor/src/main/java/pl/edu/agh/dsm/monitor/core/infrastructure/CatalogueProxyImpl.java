package pl.edu.agh.dsm.monitor.core.infrastructure;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import pl.edu.agh.dsm.monitor.core.model.measurement.CatalogueProxy;
import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;

@Component
public class CatalogueProxyImpl implements CatalogueProxy{

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
		HttpClientBuilder clientBuilder = HttpClients.custom();
		BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, 
				new UsernamePasswordCredentials(monitorUsername, monitorPassoword));
		clientBuilder.setDefaultCredentialsProvider(credentialsProvider);
		HttpClient httpClient = clientBuilder.build();
		ClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory(httpClient);
		restTemplate = new RestTemplate(rf);
	}

	@Override
	public void addMeasurement(Measurement dto) {
		logger.debug("send info about new measurement with id {}", dto);
		restTemplate.postForObject(catalogueMeasurementsUri, dto, Object.class);
	}

	@Override
	public void removeMeasurement(UUID uuid) {
		logger.debug("send info about removed measurement with id {}", uuid);
		String uri = catalogueMeasurementsUri + "/" + uuid;
		restTemplate.delete(uri);
	}
}
