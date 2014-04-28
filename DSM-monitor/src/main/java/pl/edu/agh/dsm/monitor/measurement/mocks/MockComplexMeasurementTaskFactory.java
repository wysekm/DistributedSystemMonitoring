package pl.edu.agh.dsm.monitor.measurement.mocks;

import pl.edu.agh.dsm.common.annotations.GuiMockComponent;
import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.security.ApplicationUser;
import pl.edu.agh.dsm.monitor.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.monitor.measurement.ComplexMeasurementTaskFactory;

import java.util.UUID;

@GuiMockComponent
public class MockComplexMeasurementTaskFactory implements
		ComplexMeasurementTaskFactory {

	@Override
	public MeasurementDto create(ComplexMeasurementDto complexMeasurementDto,
			ApplicationUser applicationUser) {
		return new MeasurementDto(UUID.randomUUID(), "zeus",
				complexMeasurementDto.getType(), "%", "http://localhost:8081");
	}
}
