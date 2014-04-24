package pl.edu.agh.dsm.monitor.externalApi;

import pl.edu.agh.dsm.monitor.dto.SimpleMeasurementDataDto;

//draft  UC_PF_MT3
public interface UCReceiveSingleMeasurementData {
    void addNewData(SimpleMeasurementDataDto dto);
}
