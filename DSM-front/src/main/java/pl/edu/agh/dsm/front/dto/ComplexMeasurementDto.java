package pl.edu.agh.dsm.front.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;
import java.util.UUID;

/**
 * Created by Tom on 2014-05-23.
 */
public class ComplexMeasurementDto {

	@JsonIgnore
	private UUID baseMeasurementId;
	private String typeCode;
	private Map<String, String> parameters;
	private String createdBy;

	public ComplexMeasurementDto(UUID baseMeasurementId, String typeCode, Map<String, String> parameters, String createdBy) {
		this.baseMeasurementId = baseMeasurementId;
		this.typeCode = typeCode;
		this.parameters = parameters;
		this.createdBy = createdBy;
	}

	public UUID getBaseMeasurementId() {
		return baseMeasurementId;
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
}
