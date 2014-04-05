package pl.edu.agh.dsm.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

@Configuration
@EnableAutoConfiguration
@ComponentScan(excludeFilters =
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
)
@EnableEntityLinks
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class CatalogueConfig {


    public static void main(String[] args) {
        SpringApplication.run(CatalogueConfig.class, args);
    }
}
