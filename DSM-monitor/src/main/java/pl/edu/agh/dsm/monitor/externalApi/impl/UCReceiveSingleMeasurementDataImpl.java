package pl.edu.agh.dsm.monitor.externalApi.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.dsm.common.annotations.UseCase;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.dto.SimpleMeasurementDataDto;
import pl.edu.agh.dsm.monitor.externalApi.UCReceiveSingleMeasurementData;
import pl.edu.agh.dsm.monitor.measurement.MeasurementDataRepository;


@UseCase()
public class UCReceiveSingleMeasurementDataImpl implements UCReceiveSingleMeasurementData {

    MeasurementDataRepository dataRepository;

    @Autowired
    public UCReceiveSingleMeasurementDataImpl(MeasurementDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void addNewData(SimpleMeasurementDataDto dto)
    {
        dataRepository.add(dto.getId(),new MeasurementDataDto(dto.getTimestamp(),dto.getValue()));
    }

}
