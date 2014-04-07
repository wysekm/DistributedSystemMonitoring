package pl.edu.agh.dsm.monitor;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import pl.edu.agh.dsm.monitor.annotations.MockComponent;

@Configuration
@ComponentScan(useDefaultFilters = false,includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = MockComponent.class))
@Profile("mockComponents")
public class MocksConfiguration {

}
