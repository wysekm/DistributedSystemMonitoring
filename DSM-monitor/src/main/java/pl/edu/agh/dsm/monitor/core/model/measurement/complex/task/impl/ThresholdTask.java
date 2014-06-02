package pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.impl;

import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.ComplexMeasurementTask;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.UpdateType;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.annotation.Parameter;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.annotation.Task;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.DataLimit;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;

import java.util.List;

/**
 * Created by Tom on 2014-05-29.
 */
@Task(name="Threshold", code="thresh", unit="", parameters = {
	@Parameter(name="Threshold", code="thresh_value")})
public class ThresholdTask extends ComplexMeasurementTask {

	public ThresholdTask(ComplexMeasurement complexMeasurement) {
		super(complexMeasurement);
	}

	@Override
	public void update() {
		List<MeasurementData> data = getBaseMeasurementData(DataLimit.last, 1);
		if(data.isEmpty()) return;
		double value = calcThreshold(data.get(0), getThreshold());
		long timestamp = data.get(0).getTimestamp();
		addMeasurementData(new MeasurementData(timestamp, value));
	}

	double calcThreshold(MeasurementData data, double threshold) {
		if(data.getData() > threshold) {
			return 1;
		} else {
			return 0;
		}
	}

	public double getThreshold() {
		return getParamValue("thresh_value");
	}

	@Override
	public UpdateType getUpdateType() {
		return UpdateType.DATA_BASED;
	}
}
