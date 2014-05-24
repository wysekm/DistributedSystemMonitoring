package pl.edu.agh.dsm.front.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 2014-05-23.
 */
public class ComplexMeasurementDto {

	@JsonIgnore
	private String baseMeasurementUri;
	private String typeCode;
	private Map<String, String> parameters = new HashMap<>();
	@JsonIgnore
	private String createdBy;

	public ComplexMeasurementDto() {
	}

	public ComplexMeasurementDto(String typeCode, Map<String, String> parameters, String createdBy) {
		this.typeCode = typeCode;
		this.parameters = parameters;
		this.createdBy = createdBy;
	}

	public ComplexMeasurementDto(String baseMeasurementUri, String typeCode, Map<String, String> parameters, String createdBy) {
		this.baseMeasurementUri = baseMeasurementUri;
		this.typeCode = typeCode;
		this.parameters = parameters;
		this.createdBy = createdBy;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getBaseMeasurementUri() {
		return baseMeasurementUri;
	}

	public void setBaseMeasurementUri(String baseMeasurementUri) {
		this.baseMeasurementUri = baseMeasurementUri;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "ComplexMeasurementDto{" +
				"baseMeasurementUri='" + baseMeasurementUri + '\'' +
				", typeCode='" + typeCode + '\'' +
				", parameters=" + parameters +
				", createdBy='" + createdBy + '\'' +
				'}';
	}
}
