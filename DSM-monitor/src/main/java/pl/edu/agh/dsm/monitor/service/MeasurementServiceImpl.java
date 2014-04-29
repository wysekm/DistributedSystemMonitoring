package pl.edu.agh.dsm.monitor.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Predicate;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.repository.MeasurementRepository;
import pl.edu.agh.dsm.common.repository.predicate.MeasurementPredicateFactory;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.dto.SimpleMeasurementDataDto;
import pl.edu.agh.dsm.monitor.internalApi.MeasurementDtoFactory;
import pl.edu.agh.dsm.monitor.repository.MeasurementDataRepository;
import pl.edu.agh.dsm.monitor.repository.predicate.DataLimit;
import pl.edu.agh.dsm.monitor.repository.predicate.MeasurementDataPredicateFactory;

@Service
public class MeasurementServiceImpl implements MeasurementsService {

	@Autowired
	MeasurementPredicateFactory precondictionFactory;
	
	@Autowired
	MeasurementDataPredicateFactory dataPrecondictionFactory;
	
	@Autowired
	MeasurementRepository measurementRepository;
	
	@Autowired
	MeasurementDataRepository measurementDataRepository;
	
	@Autowired
	MeasurementDtoFactory measurementDtoFactory;
	
	@Override
	public List<MeasurementDto> getList(String metric, String resource) {
		Predicate<MeasurementDto> preconditions = precondictionFactory
				.createForMeasurement(metric, resource);
		return measurementRepository.findAll(preconditions);
	}

	@Override
	public MeasurementDto getDetails(UUID uuid) {
		return measurementRepository.find(uuid);
	}

	@Override
	public List<MeasurementDataDto> getData(UUID uuid, DataLimit limit,
			int value) {
		Predicate<MeasurementDataDto> preconditions = dataPrecondictionFactory
				.createForData(limit, value);
		return measurementDataRepository.find(uuid, preconditions);
	}

	@Override
	synchronized public void addNewData(SimpleMeasurementDataDto dto) {
		MeasurementDataDto measurementData = measurementDtoFactory.createNewMeasurementData(dto);
		if(measurementRepository.find(dto.getId()) == null) {
			MeasurementDto measurement = measurementDtoFactory.createNewMeasurement(dto);
			measurementRepository.save(measurement);
		}
		measurementDataRepository.add(dto.getId(), measurementData);
	}
	


}
