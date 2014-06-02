package pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.impl;

import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.ComplexMeasurementTask;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.UpdateType;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.annotation.Parameter;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.annotation.Task;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.DataLimit;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;

import java.util.Date;
import java.util.List;
import static java.util.concurrent.TimeUnit.*;

/**
 * Created by Tom on 2014-05-29.
 */
@Task(name="Moving average", code="move_avg", parameters = {
	@Parameter(name="Interval (s)", code="interval"),
	@Parameter(name="Time window (s)", code="time_window")})
public class MovingAverageTask extends ComplexMeasurementTask {

	public MovingAverageTask(ComplexMeasurement complexMeasurement) {
		super(complexMeasurement);
	}

	private long lastAddTimestamp = new Date().getTime();

	@Override
	public void update() {
		if(hasIntervalTimePassed()) {
			List<MeasurementData> data = getBaseMeasurementData(DataLimit.since, (int) getTimeWindow());
			double value = calcAverage(data);
			long timestamp = new Date().getTime();
			addMeasurementData(new MeasurementData(timestamp, value));
			lastAddTimestamp = timestamp;
		}
	}

	public double calcAverage(List<MeasurementData> data) {
		double avg=0;
		for(int i=0; i< data.size();i++){
			avg += data.get(i).getData();
			}
		avg = avg/data.size();
		return avg;
	}

	public boolean hasIntervalTimePassed() {
		long now = new Date().getTime();
		long intervalSeconds = MILLISECONDS.convert(getInterval(), SECONDS);
		return now - lastAddTimestamp > intervalSeconds;
	}

	@Override
	public UpdateType getUpdateType() {
		return UpdateType.TIME_BASED;
	}

	public long getInterval() {
		return (long) getParamValue("interval");
	}

	public double getTimeWindow() {
		return getParamValue("time_window");
	}
}
