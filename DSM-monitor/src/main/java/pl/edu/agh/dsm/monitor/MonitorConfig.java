package pl.edu.agh.dsm.monitor;

import java.io.IOException;
import java.util.Properties;

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
	
	static Properties properties;

	// use -Dspring.profiles.active="mockComponents" for mockImpl
	public static void main(String[] args) throws IOException {
		properties = new Properties();
		properties.load(MonitorConfig.class.getClassLoader().getResourceAsStream("monitor.properties"));
		
		SpringApplication.run(MonitorConfig.class, args);
	}

	@Bean
	public MockAutorizationContext getAutorizationContext() {
		return new MockAutorizationContext();
	}
	
	public static String getCatalogueAddress() {
		return properties.getProperty("catalogue.address");
	}
}
