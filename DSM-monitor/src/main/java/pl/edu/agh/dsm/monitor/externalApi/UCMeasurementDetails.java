package pl.edu.agh.dsm.monitor.externalApi;

import pl.edu.agh.dsm.common.dto.MeasurementDto;

import java.util.UUID;

public interface UCMeasurementDetails {
    public MeasurementDto details(UUID uuid);


}
