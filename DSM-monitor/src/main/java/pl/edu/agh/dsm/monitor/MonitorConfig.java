package pl.edu.agh.dsm.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import pl.edu.agh.dsm.common.security.MockAutorizationContext;
import pl.edu.agh.dsm.monitor.annotations.MockComponent;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

@Configuration
@EnableAutoConfiguration
@ComponentScan(excludeFilters =
        {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = MockComponent.class)
        }
)
@Import({MocksConfiguration.class})
@EnableEntityLinks
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class MonitorConfig {

    //use -Dspring.profiles.active="mockComponents" for mockImpl
    public static void main(String[] args) {
        SpringApplication.run(MonitorConfig.class, args);
    }

    @Bean
    public MockAutorizationContext getAutorizationContext()
    {
        return new MockAutorizationContext();
    }
}