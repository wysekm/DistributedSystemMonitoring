package pl.edu.agh.dsm.front.mocks;

import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.annotation.*;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import pl.edu.agh.dsm.front.core.infrastructure.UseCase;
import pl.edu.agh.dsm.front.core.model.rest.UserCredentials;
import pl.edu.agh.dsm.front.core.model.rest.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.front.core.model.rest.dto.ComplexMeasurementOutDto;
import pl.edu.agh.dsm.front.core.usecase.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Configuration
@EnableEntityLinks
@EnableHypermediaSupport(type = HypermediaType.HAL)
@ComponentScan( value = {"pl.edu.agh.dsm.front.web" },
		excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = UseCase.class))
@Profile("mockComponents")
public class MocksConfiguration {

	@Bean
	public MeasurementControllerMock controllerMock() {
		return new MeasurementControllerMock();
	}

	@Bean
	public MockRepository mockRepository() {
		return new MockRepository();
	}

	@Bean
	public MockImplementation mockImplementation() {
		return new MockImplementation();
	}

	@Primary
	@Bean
	public AddMeasurement addMeasurement() throws Exception {
		return (AddMeasurement) mockUsecase(AddMeasurement.class, "addMeasurement",
				String.class, ComplexMeasurementOutDto.class, UserCredentials.class);
	}

	@Primary
	@Bean
	public DeleteMeasurement deleteMeasurement() throws Exception {
		return (DeleteMeasurement) mockUsecase(DeleteMeasurement.class, "deleteMeasurement",
				String.class, UserCredentials.class);
	}

	@Primary
	@Bean
	public GetAvailableComplexTypes getAvailableComplexTypes() throws Exception {
		return (GetAvailableComplexTypes) mockUsecase(GetAvailableComplexTypes.class, "getAvailableComplexTypes",
				String.class);
	}

	@Primary
	@Bean
	public GetMeasurements getMeasurements() throws Exception {
		return (GetMeasurements) mockUsecase(GetMeasurements.class,"getMeasurements",
				String.class, String.class, UserCredentials.class);
	}

	@Primary
	@Bean
	public GetSystemResources getSystemResources() throws Exception {
		return (GetSystemResources) mockUsecase(GetSystemResources.class, "getResources");
	}

	private Object mockUsecase(Class<?> mockClass, final String mockMethodName, final Class<?> ... argsTypes)
			throws Exception {
		Method realMethod = mockClass.getDeclaredMethod(mockMethodName, argsTypes);
		Object mockObject = mock(mockClass);
		Object args[] = new Object[argsTypes.length];
		for(int i=0; i<args.length; i++) {
			Class<?> type = argsTypes[i];
			args[i] = any(type);
		}
		when(realMethod.invoke(mockObject, args)).then(mockAnswer(mockMethodName, argsTypes));
		return mockObject;
	}

	private Answer mockAnswer(final String mockMethodName, Class<?> ... argsTypes) throws NoSuchMethodException {
		final Method mockMethod = MockImplementation.class.getDeclaredMethod(mockMethodName, argsTypes);
		return new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				return mockMethod.invoke(mockImplementation(), args);
			}
		};
	}
}
