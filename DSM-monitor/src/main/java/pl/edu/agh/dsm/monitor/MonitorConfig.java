package pl.edu.agh.dsm.monitor;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import pl.edu.agh.dsm.common.annotations.GuiMockComponent;
import pl.edu.agh.dsm.common.security.MockAutorizationContext;
import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

@Configuration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = GuiMockComponent.class) },
		value = { "pl.edu.agh.dsm.monitor", "pl.edu.agh.dsm.common" })
@Import({ GuiMocksConfiguration.class })
@EnableEntityLinks
@EnableHypermediaSupport(type = HypermediaType.HAL)
@PropertySources({
	@PropertySource("monitor.properties"),
	@PropertySource("application.properties")
})
@ImportResource("udp-client-context.xml")
@EnableConfigurationProperties()
public class MonitorConfig {

	// use -Dspring.profiles.active="mockComponents" for mockImpl
	public static void main(String[] args) throws IOException {
		SpringApplication.run(MonitorConfig.class, args);
	}

	@Bean
	public MockAutorizationContext getAutorizationContext() {
		return new MockAutorizationContext();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
