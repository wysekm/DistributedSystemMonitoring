package pl.edu.agh.dsm.monitor.core.model.measurement.complex;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ComplexMeasurementParam {
	String paramCode;
	double value;

	// @java.beans.ConstructorProperties({"paramCode", "value"})
	public ComplexMeasurementParam(String paramCode, double value) {
		this.paramCode = paramCode;
		this.value = value;
	}

	public ComplexMeasurementParam() {
	}

	public String getParamCode() {
		return this.paramCode;
	}

	public double getValue() {
		return this.value;
	}
}
