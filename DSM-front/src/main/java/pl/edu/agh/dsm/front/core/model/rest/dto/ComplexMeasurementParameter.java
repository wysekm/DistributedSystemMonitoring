package pl.edu.agh.dsm.front.core.model.rest.dto;

/**
 * Created by Tom on 2014-06-01.
 */
public class ComplexMeasurementParameter {

	String paramCode;
	String value;

	public ComplexMeasurementParameter() {
	}

	public ComplexMeasurementParameter(String paramName, String value) {
		this.paramCode = paramName;
		this.value = value;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
