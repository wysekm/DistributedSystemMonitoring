package pl.edu.agh.dsm.monitor.core.model.measurement.complex;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.Assert;
import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.MeasurementService;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.TaskExecutor;

@Service
public class ComplexMeasurementsService {

	@Autowired
	MeasurementService measurementService;

	@Autowired
	ComplexMeasurementsRepository complexRepository;

	@Autowired
	TaskExecutor taskExecutor;

	/**
	 * Create a new complex measurement
	 *
	 * @param measurement
	 * @throws IllegalArgumentException
	 * 		when complex measurement type is not recognized,
	 * 		or when parameters specification is not met
	 */
	public void create(ComplexMeasurement measurement) throws IllegalArgumentException {
		Measurement baseMeasurement = measurementService.getDetails(measurement.baseMeasurementId);
		Assert.notNull(baseMeasurement, "Base measurement not found");
		Measurement newMeasurement = createNewMeasurement(baseMeasurement);
		measurementService.addMeasurement(newMeasurement);
		measurement.setId(newMeasurement.getId());
		complexRepository.save(measurement);
		taskExecutor.createTask(measurement);
	}

	public void delete(UUID uuid) {
		complexRepository.remove(uuid);
		taskExecutor.deleteTask(uuid);
		measurementService.deleteMeasurement(uuid);
	}

	public ComplexMeasurement getDetails(UUID uuid) {
		return complexRepository.find(uuid);
	}

	public boolean isComplex(UUID uuid) {
		return complexRepository.find(uuid) != null;
	}

	public List<ComplexType> getAvailableComplexTypes() {
		return taskExecutor.getAvailableComplexTypes();
	}

	private Measurement createNewMeasurement(Measurement baseMeasurement) {
		return new Measurement(UUID.randomUUID(),baseMeasurement.getResource(),
				baseMeasurement.getMetric(), baseMeasurement.getUnit(), baseMeasurement.getMonitor());
	}

}
