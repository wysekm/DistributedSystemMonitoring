package pl.edu.agh.dsm.monitor;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;

import pl.edu.agh.dsm.common.annotations.GuiMockComponent;

@Configuration
@ComponentScan(useDefaultFilters = false, includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = GuiMockComponent.class), value = {
		"pl.edu.agh.dsm.monitor.measurement.mocks",
		"pl.edu.agh.dsm.common.measurement.mocks" })
@Profile("mockComponents")
public class GuiMocksConfiguration {

}
