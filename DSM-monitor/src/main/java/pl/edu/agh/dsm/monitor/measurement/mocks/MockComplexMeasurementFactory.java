package pl.edu.agh.dsm.monitor.measurement.mocks;

import pl.edu.agh.dsm.common.security.ApplicationUser;
import pl.edu.agh.dsm.monitor.annotations.MockComponent;
import pl.edu.agh.dsm.monitor.dto.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.dto.Measurement;
import pl.edu.agh.dsm.monitor.externalApi.impl.ComplexMeasurementFactory;

import java.util.UUID;

@MockComponent
public class MockComplexMeasurementFactory implements ComplexMeasurementFactory {

    @Override
    public Measurement create(ComplexMeasurement complexMeasurement, ApplicationUser applicationUser) {
        return new Measurement(UUID.randomUUID(), "zeus", complexMeasurement.getType(),"%");
    }
}
