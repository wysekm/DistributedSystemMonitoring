package pl.edu.agh.dsm.front.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.dsm.front.core.usecase.*;

/**
 * Configuration of usecase beans. These beans are created manually
 * because automatic component scan prevents bean overriding
 * which is needed to mock the usecase implementation.
 * <p>
 * (Yes, it could have been solved by creating base interfaces for
 * usecases and making different implementation for mock and for production,
 * but it would generate tons of classes)
 *
 * Created by Tom on 2014-05-27.
 */
@Configuration
public class UsecaseConfig {

	@Bean
	public AddMeasurement addMeasurement(){
		return new AddMeasurement();
	}

	@Bean
	public DeleteMeasurement deleteMeasurement(){
		return new DeleteMeasurement();
	}

	@Bean
	public GetAvailableComplexTypes getAvailableComplexTypes() {
		return new GetAvailableComplexTypes();
	}

	@Bean
	public GetMeasurements getMeasurements() {
		return new GetMeasurements();
	}

	@Bean
	public GetSystemResources getSystemResources(){
		return new GetSystemResources();
	}
}
