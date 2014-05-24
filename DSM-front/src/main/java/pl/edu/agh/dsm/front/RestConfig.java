package pl.edu.agh.dsm.front;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class RestConfig {

	@Bean
	public RestTemplate restTemplate() {
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(halConverter());
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);
		return restTemplate;
	}

	public MappingJackson2HttpMessageConverter halConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(MediaType
				.parseMediaTypes("application/hal+json,application/json"));
		converter.setObjectMapper(objectMapper());
		return converter;
	}

	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.registerModule(new Jackson2HalModule());
		return mapper;
	}
}
