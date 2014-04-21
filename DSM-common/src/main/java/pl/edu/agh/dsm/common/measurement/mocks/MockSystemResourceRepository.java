package pl.edu.agh.dsm.common.measurement.mocks;

import pl.edu.agh.dsm.common.dto.SystemResourceDto;
import pl.edu.agh.dsm.common.measurement.SystemResourceRepository;
import pl.edu.agh.dsm.common.annotations.MockComponent;

import java.util.Collections;
import java.util.List;

@MockComponent
public class MockSystemResourceRepository implements SystemResourceRepository {

    @Override
    public List<SystemResourceDto> findAll() {
        return Collections.emptyList();
    }
}
