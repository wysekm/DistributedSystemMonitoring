package pl.edu.agh.dsm.front.core.model.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 2014-05-23.
 */
public class ComplexMeasurementDto {

	public static class Parameter {
		String paramName;
		String value;

		public Parameter() {
		}

		public Parameter(String paramName, String value) {
			this.paramName = paramName;
			this.value = value;
		}

		public String getParamName() {
			return paramName;
		}

		public void setParamName(String paramName) {
			this.paramName = paramName;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	private String measurement;
	private String type;
	private List<Parameter> params = new ArrayList<>();

	@JsonIgnore
	private String createdBy;

	public ComplexMeasurementDto() {
	}

	public ComplexMeasurementDto(String typeCode, Map<String, String> parameters, String createdBy) {
		this.type = typeCode;
		this.createdBy = createdBy;
		for(Map.Entry<String, String> entry : parameters.entrySet()) {
			params.add(new Parameter(entry.getKey(), entry.getValue()));
		}
	}

	public ComplexMeasurementDto(String baseMeasurementUri, String typeCode, Map<String, String> parameters, String createdBy) {
		this(typeCode, parameters, createdBy);
		this.measurement = baseMeasurementUri;
	}

	public String getType() {
		return type;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Parameter> getParams() {
		return params;
	}

	public void setParams(List<Parameter> params) {
		this.params = params;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "ComplexMeasurementDto{" +
				"measurement='" + measurement + '\'' +
				", type='" + type + '\'' +
				", params=" + params +
				", createdBy='" + createdBy + '\'' +
				'}';
	}
}
