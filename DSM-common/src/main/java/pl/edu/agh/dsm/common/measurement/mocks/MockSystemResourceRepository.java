package pl.edu.agh.dsm.common.measurement.mocks;

import pl.edu.agh.dsm.common.annotations.GuiMockComponent;
import pl.edu.agh.dsm.common.dto.SystemResourceDto;
import pl.edu.agh.dsm.common.measurement.SystemResourceRepository;

import java.util.Collections;
import java.util.List;

@GuiMockComponent
public class MockSystemResourceRepository implements SystemResourceRepository {

	@Override
	public List<SystemResourceDto> findAll() {
		return Collections.emptyList();
	}
}
