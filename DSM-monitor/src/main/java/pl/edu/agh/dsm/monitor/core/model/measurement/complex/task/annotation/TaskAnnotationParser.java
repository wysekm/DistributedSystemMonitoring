package pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.annotation;

import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexType;

import java.util.ArrayList;

/**
 * Created by Tom on 2014-05-29.
 */
public class TaskAnnotationParser {

	public static ComplexType getComplexType(Task task) {
		ComplexType.Parameter[] parameters = new ComplexType.Parameter[task.parameters().length];
		for(int i=0;i<task.parameters().length;i++) {
			Parameter param = task.parameters()[i];
			parameters[i] = new ComplexType.Parameter(param.name(), param.code(), param.required());
		}
		return new ComplexType(task.name(), task.code(), task.unit(), parameters);
	}
}
