package pl.edu.agh.dsm.front.core.infrastructure;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.dsm.front.core.model.rest.UserCredentials;

import javax.xml.transform.Source;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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
		final byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		final String authHeader = "Basic " + new String( encodedAuth );
		request.getHeaders().add( "Authorization", authHeader );
		return execution.execute( request, body );
	}
}

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

	RestTemplate restTemplate(UserCredentials user) {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(halConverter());
		if(user != null) {
			final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
			interceptors.add(new BasicAuthInterceptor(user.getUsername(), user.getPassword()));
			restTemplate.setInterceptors(interceptors);
		}
		restTemplate.setMessageConverters(converters);
		return restTemplate;
	}

	MappingJackson2HttpMessageConverter halConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
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
