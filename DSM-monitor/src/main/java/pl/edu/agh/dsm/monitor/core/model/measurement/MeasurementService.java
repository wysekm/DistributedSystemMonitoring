package pl.edu.agh.dsm.monitor.core.model.measurement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.TaskExecutor;
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

	@Autowired
	TaskExecutor taskExecutor;

	/**
	 * Retrieves measurements from the repository, filtered by metric name and resource name.
	 *
	 * @param metric metric name to filter the results by. Can be null or empty - then
	 *               measurements with any metric name will be returned.
	 * @param resource resource name to filter the results by. Can be null or empty - then
	 *               measurements with any resource name will be returned.
	 * @return list of measurements that meet the given criteria
	 */
	public List<Measurement> getList(String metric, String resource) {
		Predicate<Measurement> preconditions = precondictionFactory
				.createForMeasurement(metric, resource);
		return measurementRepository.findAll(preconditions);
	}

	public Measurement getDetails(UUID uuid) {
		return measurementRepository.find(uuid);
	}

	/**
	 * Retrieves measurement data from the repository
	 *
	 * @param uuid id of the measurement to get the data for
	 * @param limit a measure how to filter the results by.
	 *             All - returns all available data for the given measurement
	 *             Last - return last n data values
	 *             Since - returns all available data since last n minutes
	 * @param value the value for the data limit filter. See 'n' in the above list.
	 * @return list of measurement data items that meet the given criteria
	 */
	public List<MeasurementData> getData(UUID uuid, DataLimit limit, int value) {
		Predicate<MeasurementData> preconditions = dataPrecondictionFactory
				.createForData(limit, value);
		return measurementDataRepository.find(uuid, preconditions);
	}

	public void addMeasurement(Measurement measurement) {
		if(measurementRepository.find(measurement.getId()) == null) {
			measurementRepository.save(measurement);
			catalogueProxy.addMeasurement(measurement);
		}
	}
	
	public void addMeasurementData(UUID id, MeasurementData data) {
		measurementDataRepository.add(id, data);
		taskExecutor.updateDataBasedTasks(id);
	}
	
	public void deleteMeasurement(UUID uuid) {
		// TODO this function should delete measurement and its data from repositories,
		// and inform catalogue of this change
	}
}
