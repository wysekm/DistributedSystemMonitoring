package pl.edu.agh.dsm.front.mocks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

@Configuration
@EnableEntityLinks
@EnableHypermediaSupport(type = HypermediaType.HAL)
@Profile("mockComponents")
public class MocksConfiguration {

	@Bean
	public MeasurementControllerMock controllerMock() {
		return new MeasurementControllerMock();
	}
	
	@Primary
	@Bean
	public CatalogueRestClientServiceMock restMock() {
		return new CatalogueRestClientServiceMock();
	}
}
