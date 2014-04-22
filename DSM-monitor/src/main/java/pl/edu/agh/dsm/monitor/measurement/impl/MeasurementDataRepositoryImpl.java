package pl.edu.agh.dsm.monitor.measurement.impl;

import com.google.common.base.Predicate;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.measurement.MeasurementDataRepository;

import java.util.List;
import java.util.UUID;


public class MeasurementDataRepositoryImpl implements MeasurementDataRepository {
    @Override
    public List<MeasurementDataDto> find(UUID uuid, Predicate<MeasurementDataDto> preconditions) {

        return null;
    }
}
