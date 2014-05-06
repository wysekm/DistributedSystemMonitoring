package pl.edu.agh.dsm.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

import pl.edu.agh.dsm.common.annotations.GuiMockComponent;

@Configuration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = GuiMockComponent.class)}, 
		value = {"pl.edu.agh.dsm.catalog", "pl.edu.agh.dsm.common" })
@Import({ MocksConfiguration.class })
@EnableEntityLinks
@EnableHypermediaSupport(type = HypermediaType.HAL)
@ImportResource("security-context.xml")
public class CatalogueConfig {

	// use -Dspring.profiles.active="mockComponents" for mockImpl
	public static void main(String[] args) {
		SpringApplication.run(CatalogueConfig.class, args);
	}
}
