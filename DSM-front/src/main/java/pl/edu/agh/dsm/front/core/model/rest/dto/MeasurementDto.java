package pl.edu.agh.dsm.front.core.model.rest.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MeasurementDto {

	@JsonIgnore
	private UUID id;
	private String resource;
	private String metric;
	private String unit;

	@JsonIgnore
	private String monitor;

	@JsonIgnore
	private ComplexMeasurementDto complexDetails;

	public MeasurementDto(UUID id, String resource, String metric, String unit,
			String monitor) {
		this.id = id;
		this.resource = resource;
		this.metric = metric;
		this.unit = unit;
		this.monitor = monitor;
	}

	public MeasurementDto(UUID id) {
		this.id = id;
	}

	public MeasurementDto() {
	}

	public UUID getId() {
		return this.id;
	}

	public String getResource() {
		return this.resource;
	}

	public String getMetric() {
		return this.metric;
	}

	public String getUnit() {
		return this.unit;
	}

	public String getMonitor() {
		return monitor;
	}

	public ComplexMeasurementDto getComplexDetails() {
		return complexDetails;
	}

	public void setComplexDetails(ComplexMeasurementDto complexDetails) {
		this.complexDetails = complexDetails;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MeasurementDto that = (MeasurementDto) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
