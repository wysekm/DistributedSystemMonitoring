package pl.edu.agh.dsm.monitor.core.model.measurement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.agh.dsm.monitor.core.model.measurement.data.DataLimit;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementDataPredicateFactory;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementDataRepository;

import com.google.common.base.Predicate;

@Service
public class MeasurementService {
	
	@Autowired
	MeasurementPredicateFactory precondictionFactory;
	
	@Autowired
	MeasurementDataPredicateFactory dataPrecondictionFactory;
	
	@Autowired
	MeasurementRepository measurementRepository;
	
	@Autowired
	MeasurementDataRepository measurementDataRepository;
	
	@Autowired
	CatalogueProxy catalogueProxy;
	
	public List<Measurement> getList(String metric, String resource) {
		Predicate<Measurement> preconditions = precondictionFactory
				.createForMeasurement(metric, resource);
		return measurementRepository.findAll(preconditions);
	}

	public Measurement getDetails(UUID uuid) {
		return measurementRepository.find(uuid);
	}

	public List<MeasurementData> getData(UUID uuid, DataLimit limit, int value) {
		Predicate<MeasurementData> preconditions = dataPrecondictionFactory
				.createForData(limit, value);
		return measurementDataRepository.find(uuid, preconditions);
	}
	
	public void addMeasurementData(Measurement measurement, MeasurementData data) {
		if(measurementRepository.find(measurement.getId()) == null) {
			measurementRepository.save(measurement);
			catalogueProxy.addMeasurement(measurement);
		}
		measurementDataRepository.add(measurement.getId(),data);
	}
	
	public void deleteMeasurement(UUID uuid) {
		// TODO this function should delete measurement and its data from repositories,
		// and inform catalogue of this change
	}
}
