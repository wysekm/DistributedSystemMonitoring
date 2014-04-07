package pl.edu.agh.dsm.monitor.measurement.mocks;

import pl.edu.agh.dsm.common.security.ApplicationUser;
import pl.edu.agh.dsm.monitor.annotations.MockComponent;
import pl.edu.agh.dsm.monitor.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.monitor.dto.MeasurementDto;
import pl.edu.agh.dsm.monitor.measurement.ComplexMeasurementFactory;

import java.util.UUID;

@MockComponent
public class MockComplexMeasurementFactory implements ComplexMeasurementFactory {

    @Override
    public MeasurementDto create(ComplexMeasurementDto complexMeasurementDto, ApplicationUser applicationUser) {
        return new MeasurementDto(UUID.randomUUID(), "zeus", complexMeasurementDto.getType(),"%");
    }
}
