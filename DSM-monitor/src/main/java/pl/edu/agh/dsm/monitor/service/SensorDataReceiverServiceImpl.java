package pl.edu.agh.dsm.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.common.dto.MeasurementDataDto;
import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.repository.MeasurementRepository;
import pl.edu.agh.dsm.monitor.dto.SimpleMeasurementDataDto;
import pl.edu.agh.dsm.monitor.internalApi.CatalogueProxy;
import pl.edu.agh.dsm.monitor.internalApi.MeasurementDtoFactory;
import pl.edu.agh.dsm.monitor.repository.MeasurementDataRepository;

public class SensorDataReceiverServiceImpl implements SensorDataReceiverService {

	@Autowired
	CatalogueProxy catalogueProxy;
	
	@Autowired
	MeasurementRepository measurementRepository;
	
	@Autowired
	MeasurementDataRepository measurementDataRepository;
	
	@Autowired
	MeasurementDtoFactory measurementDtoFactory;
	
	@Override
	public void processSensorData(SimpleMeasurementDataDto data) {
		MeasurementDataDto measurementData = measurementDtoFactory.createNewMeasurementData(data);
		if(measurementRepository.find(data.getId()) == null) {
			MeasurementDto measurement = measurementDtoFactory.createNewMeasurement(data);
			measurementRepository.save(measurement);
			catalogueProxy.addMeasurement(measurement);
		}
		measurementDataRepository.add(data.getId(), measurementData);
	}

}
