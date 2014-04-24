package pl.edu.agh.dsm.monitor.externalApi.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.dsm.monitor.dto.SimpleMeasurementDataDto;
import pl.edu.agh.dsm.monitor.externalApi.UCReceiveSingleMeasurementData;
import pl.edu.agh.dsm.monitor.measurement.MeasurementDataRepository;

public class UCReceiveSingleMeasurementDataImpl implements UCReceiveSingleMeasurementData {

    @Autowired
    MeasurementDataRepository dataRepository;

    @Override
    public void addNewData(SimpleMeasurementDataDto dto)
    {
//        dataRepository.

    }

}
