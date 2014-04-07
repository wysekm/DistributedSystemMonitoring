package pl.edu.agh.dsm.monitor.externalApi.impl;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.dsm.monitor.dto.Measurement;
import pl.edu.agh.dsm.monitor.externalApi.UCMeasurementsList;
import pl.edu.agh.dsm.monitor.annotations.UseCase;
import pl.edu.agh.dsm.monitor.measurement.MeasurementPrecondictionFactory;
import pl.edu.agh.dsm.monitor.measurement.MeasurementRepository;

import java.util.List;

@UseCase("UC_PF_MT4A")
public class UCMeasurementsListImpl implements UCMeasurementsList {


    MeasurementPrecondictionFactory precondictionFactory;
    MeasurementRepository measurementRepository;

    @Autowired
    public UCMeasurementsListImpl(MeasurementRepository measurementRepository, MeasurementPrecondictionFactory precondictionFactory) {
        this.measurementRepository = measurementRepository;
        this.precondictionFactory = precondictionFactory;
    }

    @Override
    public List<Measurement> filter(String metric, String resource) {

        Predicate<Measurement> preconditions = precondictionFactory.createForMeasurement(metric, resource);
        return measurementRepository.findAll(preconditions);
    }
}
