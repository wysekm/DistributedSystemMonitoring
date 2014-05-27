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

	private String measurement;
	private String type;
	Map<String, String> params = new HashMap<>();

	@JsonIgnore
	private String createdBy;

	public ComplexMeasurementDto() {
	}

	public ComplexMeasurementDto(String typeCode, Map<String, String> parameters, String createdBy) {
		this.type = typeCode;
		this.createdBy = createdBy;
		this.params = parameters;
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

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
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
