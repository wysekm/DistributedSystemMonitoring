package pl.edu.agh.dsm.monitor.measurement.mocks;

import pl.edu.agh.dsm.common.annotations.GuiMockComponent;
import pl.edu.agh.dsm.monitor.measurement.MeasurementPermisionResolver;

import java.util.UUID;

@GuiMockComponent
public class MockMeasurementPermisionResolver implements
		MeasurementPermisionResolver {

	@Override
	public boolean canRemove(UUID uuid) {
		return false;
	}
}
