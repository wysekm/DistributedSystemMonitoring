package pl.edu.agh.dsm.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableEntityLinks
@EnableHypermediaSupport(type = HypermediaType.HAL)
//@ImportResource("security-context.xml")
//@EnableWebMvcSecurity
@PropertySource("application.properties")
@Import(SecurityConfig.class)
public class CatalogueConfig {

	// use -Dspring.profiles.active="mockComponents" for mockImpl
	public static void main(String[] args) {
		SpringApplication.run(CatalogueConfig.class, args);
	}
}
