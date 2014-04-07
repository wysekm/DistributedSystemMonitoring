package pl.edu.agh.dsm.monitor.externalApi.impl;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.dsm.monitor.dto.MeasurementDto;
import pl.edu.agh.dsm.monitor.externalApi.UCMeasurementsList;
import pl.edu.agh.dsm.monitor.annotations.UseCase;
import pl.edu.agh.dsm.monitor.measurement.MeasurementPredicateFactory;
import pl.edu.agh.dsm.monitor.measurement.MeasurementRepository;

import java.util.List;

@UseCase("UC_PF_MT4A")
public class UCMeasurementsListImpl implements UCMeasurementsList {


    MeasurementPredicateFactory precondictionFactory;
    MeasurementRepository measurementRepository;

    @Autowired
    public UCMeasurementsListImpl(MeasurementRepository measurementRepository, MeasurementPredicateFactory precondictionFactory) {
        this.measurementRepository = measurementRepository;
        this.precondictionFactory = precondictionFactory;
    }

    @Override
    public List<MeasurementDto> filter(String metric, String resource) {

        Predicate<MeasurementDto> preconditions = precondictionFactory.createForMeasurement(metric, resource);
        return measurementRepository.findAll(preconditions);
    }
}
