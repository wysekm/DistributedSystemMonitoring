package pl.edu.agh.dsm.monitor.externalApi.impl;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.common.annotations.UseCase;
import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.measurement.MeasurementRepository;
import pl.edu.agh.dsm.monitor.externalApi.UCMeasurementDetails;

import java.util.UUID;

@UseCase("UC_PF_MT4B")
public class UCMeasurementDetailsImpl implements UCMeasurementDetails {

    MeasurementRepository repository;

    @Autowired
    public UCMeasurementDetailsImpl(MeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public MeasurementDto details(UUID uuid) {
        return repository.find(uuid);
    }
}
