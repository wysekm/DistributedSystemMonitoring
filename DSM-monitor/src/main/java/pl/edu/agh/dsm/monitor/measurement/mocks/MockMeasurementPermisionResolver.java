package pl.edu.agh.dsm.monitor.measurement.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.common.annotations.MockComponent;
import pl.edu.agh.dsm.common.security.AutorizationContext;
import pl.edu.agh.dsm.monitor.measurement.MeasurementPermisionResolver;

import java.util.UUID;

@MockComponent
public class MockMeasurementPermisionResolver implements
		MeasurementPermisionResolver {

	@Override
	public boolean canRemove(UUID uuid) {
		return false;
	}
}
