package pl.edu.agh.dsm.monitor.externalApi.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.dsm.monitor.dto.Measurement;
import pl.edu.agh.dsm.monitor.externalApi.UCMeasurementDetails;
import pl.edu.agh.dsm.monitor.annotations.UseCase;
import pl.edu.agh.dsm.monitor.measurement.MeasurementRepository;

import java.util.UUID;

@UseCase("UC_PF_MT4B")
public class UCMeasurementDetailsImpl implements UCMeasurementDetails {

    MeasurementRepository repository;

    @Autowired
    public UCMeasurementDetailsImpl(MeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Measurement details(UUID uuid) {
        return repository.find(uuid);
    }
}
