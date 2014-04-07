package pl.edu.agh.dsm.monitor.externalApi.impl;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.dsm.monitor.dto.MeasurementData;
import pl.edu.agh.dsm.monitor.externalApi.UCMeasurementDataDetails;
import pl.edu.agh.dsm.monitor.annotations.UseCase;
import pl.edu.agh.dsm.monitor.measurement.MeasurementDataRepository;
import pl.edu.agh.dsm.monitor.measurement.MeasurementPrecondictionFactory;

import java.util.List;
import java.util.UUID;

@UseCase("UC_PF_MT4C")
public class UCMeasurementDataDetailsImpl implements UCMeasurementDataDetails {


    MeasurementPrecondictionFactory precondictionFactory;
    MeasurementDataRepository measurementRepository;

    @Autowired
    public UCMeasurementDataDetailsImpl(MeasurementDataRepository measurementRepository, MeasurementPrecondictionFactory precondictionFactory) {
        this.measurementRepository = measurementRepository;
        this.precondictionFactory = precondictionFactory;
    }

    @Override
    public List<MeasurementData> details(UUID uuid, String limit, String value) {
        Predicate<MeasurementData> preconditions = precondictionFactory.createForData(limit, value);
        return measurementRepository.find(uuid, preconditions);
    }
}
