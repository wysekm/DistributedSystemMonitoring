package pl.edu.agh.dsm.monitor.measurement.mocks;

import org.springframework.stereotype.Component;
import pl.edu.agh.dsm.monitor.annotations.MockComponent;
import pl.edu.agh.dsm.monitor.dto.SystemResource;
import pl.edu.agh.dsm.monitor.measurement.SystemResourceRepository;

import java.util.Collections;
import java.util.List;

@MockComponent
public class MockSystemResourceRepository implements SystemResourceRepository {

    @Override
    public List<SystemResource> findAll() {
        return Collections.emptyList();
    }
}
