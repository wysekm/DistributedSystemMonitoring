package pl.edu.agh.dsm.monitor.core.model.measurement.complex;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ComplexMeasurementParam {
	String paramName;
	int value;

	// @java.beans.ConstructorProperties({"paramName", "value"})
	public ComplexMeasurementParam(String paramName, int value) {
		this.paramName = paramName;
		this.value = value;
	}

	public ComplexMeasurementParam() {
	}

	public String getParamName() {
		return this.paramName;
	}

	public int getValue() {
		return this.value;
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof ComplexMeasurementParam))
			return false;
		final ComplexMeasurementParam other = (ComplexMeasurementParam) o;
		if (!other.canEqual((Object) this))
			return false;
		final Object this$paramName = this.getParamName();
		final Object other$paramName = other.getParamName();
		if (this$paramName == null ? other$paramName != null : !this$paramName
				.equals(other$paramName))
			return false;
		if (this.getValue() != other.getValue())
			return false;
		return true;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $paramName = this.getParamName();
		result = result * PRIME
				+ ($paramName == null ? 0 : $paramName.hashCode());
		result = result * PRIME + this.getValue();
		return result;
	}

	public boolean canEqual(Object other) {
		return other instanceof ComplexMeasurementParam;
	}
}
