package pl.edu.agh.dsm.common.measurement.mocks;

import com.google.common.base.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.edu.agh.dsm.common.annotations.GuiMockComponent;
import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.measurement.MeasurementRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@GuiMockComponent
public class MockMeasurementRepository implements MeasurementRepository {

	Logger logger = LoggerFactory.getLogger(MockMeasurementRepository.class);

	@Override
	public List<MeasurementDto> findAll(Predicate<MeasurementDto> preconditions) {
		return Collections.emptyList();
	}

	@Override
	public MeasurementDto find(UUID uuid) {
		return new MeasurementDto();
	}

	@Override
	public void remove(UUID uuid) {
		logger.debug("removed measurement from repo - {}", uuid);
	}

	@Override
	public void save(MeasurementDto measurementDto) {

		// To change body of implemented methods use File | Settings | File
		// Templates.
	}

}
