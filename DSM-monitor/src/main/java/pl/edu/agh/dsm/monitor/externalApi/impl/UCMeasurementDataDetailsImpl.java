package pl.edu.agh.dsm.monitor.externalApi.impl;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.externalApi.UCMeasurementDataDetails;
import pl.edu.agh.dsm.monitor.annotations.UseCase;
import pl.edu.agh.dsm.monitor.measurement.DataLimit;
import pl.edu.agh.dsm.monitor.measurement.MeasurementDataRepository;
import pl.edu.agh.dsm.monitor.measurement.MeasurementPredicateFactory;

import java.util.List;
import java.util.UUID;

@UseCase("UC_PF_MT4C")
public class UCMeasurementDataDetailsImpl implements UCMeasurementDataDetails {


    MeasurementPredicateFactory precondictionFactory;
    MeasurementDataRepository measurementRepository;

    @Autowired
    public UCMeasurementDataDetailsImpl(MeasurementDataRepository measurementRepository, MeasurementPredicateFactory precondictionFactory) {
        this.measurementRepository = measurementRepository;
        this.precondictionFactory = precondictionFactory;
    }

    @Override
    public List<MeasurementDataDto> details(UUID uuid, DataLimit limit, int value) {
        Predicate<MeasurementDataDto> preconditions = precondictionFactory.createForData(limit, value);
        return measurementRepository.find(uuid, preconditions);
    }
}
