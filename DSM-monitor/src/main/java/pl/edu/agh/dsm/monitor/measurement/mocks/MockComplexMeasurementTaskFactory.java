package pl.edu.agh.dsm.monitor.measurement.mocks;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.security.ApplicationUser;
import pl.edu.agh.dsm.monitor.annotations.MockComponent;
import pl.edu.agh.dsm.monitor.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.monitor.measurement.ComplexMeasurementTaskFactory;

import java.util.UUID;

@MockComponent
public class MockComplexMeasurementTaskFactory implements ComplexMeasurementTaskFactory {

    @Override
    public MeasurementDto create(ComplexMeasurementDto complexMeasurementDto, ApplicationUser applicationUser) {
        return new MeasurementDto(UUID.randomUUID(), "zeus", complexMeasurementDto.getType(),"%");
    }
}
