package pl.edu.agh.dsm.monitor;

import org.springframework.context.annotation.*;

import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.MockComponent;
import pl.edu.agh.dsm.monitor.core.model.measurement.CatalogueProxy;
import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.sensor.mocks.CatalogueProxyMock;
import pl.edu.agh.dsm.monitor.sensor.mocks.SensorActivityMock;

import java.util.UUID;

@Configuration
@ComponentScan(useDefaultFilters = false, includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = MockComponent.class), value = {
		"pl.edu.agh.dsm.monitor.mocks" })
@Profile("mockComponents")
public class MocksConfiguration {

	@Bean
	public SensorActivityMock getSensorActivityMock() {
		return new SensorActivityMock();
	}

	@Bean
	@Primary
	public CatalogueProxy catalogProxy() {
		return new CatalogueProxyMock();
	}
}
