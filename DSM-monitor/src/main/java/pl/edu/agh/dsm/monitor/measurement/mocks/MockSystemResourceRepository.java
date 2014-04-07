package pl.edu.agh.dsm.monitor.measurement.mocks;

import pl.edu.agh.dsm.monitor.annotations.MockComponent;
import pl.edu.agh.dsm.monitor.dto.SystemResourceDto;
import pl.edu.agh.dsm.monitor.measurement.SystemResourceRepository;

import java.util.Collections;
import java.util.List;

@MockComponent
public class MockSystemResourceRepository implements SystemResourceRepository {

    @Override
    public List<SystemResourceDto> findAll() {
        return Collections.emptyList();
    }
}
