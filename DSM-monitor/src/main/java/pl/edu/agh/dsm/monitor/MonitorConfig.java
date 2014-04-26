package pl.edu.agh.dsm.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import pl.edu.agh.dsm.common.annotations.MockComponent;
import pl.edu.agh.dsm.common.security.MockAutorizationContext;
import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

@Configuration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = MockComponent.class) }, value = {
		"pl.edu.agh.dsm.monitor.measurement.impl",
		"pl.edu.agh.dsm.common.measurement.impl" })
@Import({ MocksConfiguration.class })
@EnableEntityLinks
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class MonitorConfig {

	// use -Dspring.profiles.active="mockComponents" for mockImpl
	public static void main(String[] args) {
		SpringApplication.run(MonitorConfig.class, args);
	}

	@Bean
	public MockAutorizationContext getAutorizationContext() {
		return new MockAutorizationContext();
	}
}
