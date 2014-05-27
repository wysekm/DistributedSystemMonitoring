package pl.edu.agh.dsm.front.core.infrastructure;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.dsm.front.core.model.rest.UserCredentials;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 2014-05-25.
 */
@Component
public class RestTemplateFactory {

	private static RestTemplate restTemplateNoAuth;

	public RestTemplate create() {
		if(restTemplateNoAuth == null) {
			restTemplateNoAuth = create(null);
		}
		return restTemplateNoAuth;
	}

	public RestTemplate create(UserCredentials user) {
		return restTemplate(user);
	}

	ClientHttpRequestFactory requestFactory(UserCredentials user) {
		HttpClientBuilder clientBuilder = HttpClients.custom();
		if(user != null) {
			BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			credentialsProvider.setCredentials(AuthScope.ANY,
					new UsernamePasswordCredentials(
							user.getUsername(), user.getPassword()));
			clientBuilder.setDefaultCredentialsProvider(credentialsProvider);
		}
		HttpClient httpClient = clientBuilder.build();
		return new HttpComponentsClientHttpRequestFactory(httpClient);
	}

	RestTemplate restTemplate(UserCredentials user) {
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(halConverter());
		RestTemplate restTemplate = new RestTemplate(requestFactory(user));
		restTemplate.setMessageConverters(converters);
		return restTemplate;
	}

	MappingJackson2HttpMessageConverter halConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(MediaType
				.parseMediaTypes("application/hal+json,application/json"));
		converter.setObjectMapper(objectMapper());
		return converter;
	}

	ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.registerModule(new Jackson2HalModule());
		return mapper;
	}
}
