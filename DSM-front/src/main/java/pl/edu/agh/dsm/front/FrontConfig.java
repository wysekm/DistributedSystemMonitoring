package pl.edu.agh.dsm.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import pl.edu.agh.dsm.front.mocks.MocksConfiguration;

@ComponentScan( value = {"pl.edu.agh.dsm.front.controller", "pl.edu.agh.dsm.front.service"} )
@EnableAutoConfiguration
@Import({ RestConfig.class, ThymeLeafConfig.class, MocksConfiguration.class })
@PropertySource("application.properties")
public class FrontConfig {

	// use -Dspring.profiles.active="mockComponents" for mockImpl
	public static void main(String[] args) {
		SpringApplication.run(FrontConfig.class, args);
	}
}
