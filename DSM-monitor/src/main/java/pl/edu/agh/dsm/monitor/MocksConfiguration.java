package pl.edu.agh.dsm.monitor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;

import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.MockComponent;
import pl.edu.agh.dsm.monitor.sensor.mocks.SensorActivityMock;

@Configuration
@ComponentScan(useDefaultFilters = false, includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = MockComponent.class), value = {
		"pl.edu.agh.dsm.monitor.mocks" })
@Profile("mockComponents")
public class MocksConfiguration {

	@Bean
	public SensorActivityMock getSensorActivityMock() {
		return new SensorActivityMock();
	}
}
