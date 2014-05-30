package pl.edu.agh.dsm.monitor;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.MockComponent;

@Configuration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = MockComponent.class) },
		value = { "pl.edu.agh.dsm.monitor", "pl.edu.agh.dsm.common" })
@Import({ MocksConfiguration.class, SecurityConfig.class })
@EnableEntityLinks
@EnableHypermediaSupport(type = HypermediaType.HAL)
@PropertySource("application.properties")
@ImportResource("udp-client-context.xml")
@EnableScheduling
@EnableConfigurationProperties()
public class MonitorConfig {

	// use -Dspring.profiles.active="mockComponents" for mockImpl
	public static void main(String[] args) throws IOException {
		SpringApplication.run(MonitorConfig.class, args);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(3);
		return taskScheduler;
	}

}
