package pl.edu.agh.dsm.monitor.core.model.measurement.complex.task;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import pl.edu.agh.dsm.monitor.core.model.measurement.MeasurementService;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurementParam;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexType;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.annotation.Task;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.annotation.TaskAnnotationParser;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.DataLimit;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;

import java.util.List;
import java.util.UUID;

/**
 * Created by Tom on 2014-05-29.
 */
@Task
public abstract class ComplexMeasurementTask {

	@Autowired
	private MeasurementService measurementService;

	private ComplexMeasurement complexMeasurement;

	public ComplexMeasurementTask(ComplexMeasurement complexMeasurement) {
		validate(complexMeasurement.getParams());
		this.complexMeasurement = complexMeasurement;
	}

	public abstract void update();

	public abstract UpdateType getUpdateType();

	public ComplexMeasurement getComplexMeasurement() {
		return complexMeasurement;
	}

	public ComplexType getComplexType() {
		Task annotation = this.getClass().getAnnotation(Task.class);
		return TaskAnnotationParser.getComplexType(annotation);
	}

	protected void addMeasurementData(MeasurementData data) {
		UUID thisId = complexMeasurement.getId();
		measurementService.addMeasurementData(thisId, data);
	}

	protected List<MeasurementData> getBaseMeasurementData(DataLimit dataLimit, int limitValue) {
		UUID baseMeasurementId = complexMeasurement.getBaseMeasurementId();
		return measurementService.getData(baseMeasurementId, dataLimit, limitValue);
	}

	protected double getParamValue(String paramCode) {
		for(ComplexMeasurementParam param : complexMeasurement.getParams()) {
			if(param.getParamCode().equals(paramCode)) {
				return param.getValue();
			}
		}
		throw new IllegalArgumentException("No such param : " + paramCode);
	}

	private void validate(List<ComplexMeasurementParam> params) {
		ComplexType complexType = getComplexType();
		Assert.isTrue(params.size() == complexType.getParams().size(),
				"Parameters do not comply with the complex type");
		for(ComplexMeasurementParam param : params) {
			Assert.isTrue(complexType.containsParam(param.getParamCode()),
					"Parameters do not comply with the complex type");
		}
	}
}
