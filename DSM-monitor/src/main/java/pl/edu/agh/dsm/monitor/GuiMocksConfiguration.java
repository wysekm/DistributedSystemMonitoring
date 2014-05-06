package pl.edu.agh.dsm.monitor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;

import pl.edu.agh.dsm.common.annotations.GuiMockComponent;
import pl.edu.agh.dsm.monitor.mocks.SensorActivityMock;

@Configuration
@ComponentScan(useDefaultFilters = false, includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = GuiMockComponent.class), value = {
		"pl.edu.agh.dsm.monitor.mocks" })
@Profile("mockComponents")
public class GuiMocksConfiguration {

	@Bean
	public SensorActivityMock getSensorActivityMock() {
		return new SensorActivityMock();
	}
}
