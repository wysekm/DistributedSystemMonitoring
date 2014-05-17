package pl.edu.agh.dsm.monitor.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.agh.dsm.common.dto.DataLimit;
import pl.edu.agh.dsm.common.dto.MeasurementDataDto;
import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.repository.MeasurementRepository;
import pl.edu.agh.dsm.common.repository.predicate.MeasurementPredicateFactory;
import pl.edu.agh.dsm.monitor.repository.MeasurementDataRepository;
import pl.edu.agh.dsm.monitor.repository.predicate.MeasurementDataPredicateFactory;

import com.google.common.base.Predicate;

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
}
