package pl.edu.agh.dsm.front.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MeasurementDto {

	@JsonIgnore
	private UUID id;
	private String resource;
	private String metric;
	private String unit;
	@JsonIgnore
	private String monitor;

	public MeasurementDto(UUID id, String resource, String metric, String unit,
			String monitor) {
		this.id = id;
		this.resource = resource;
		this.metric = metric;
		this.unit = unit;
		this.monitor = monitor;
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

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof MeasurementDto))
			return false;
		final MeasurementDto other = (MeasurementDto) o;
		if (!other.canEqual((Object) this))
			return false;
		final Object this$id = this.getId();
		final Object other$id = other.getId();
		if (this$id == null ? other$id != null : !this$id.equals(other$id))
			return false;
		final Object this$resource = this.getResource();
		final Object other$resource = other.getResource();
		if (this$resource == null ? other$resource != null : !this$resource
				.equals(other$resource))
			return false;
		final Object this$metric = this.getMetric();
		final Object other$metric = other.getMetric();
		if (this$metric == null ? other$metric != null : !this$metric
				.equals(other$metric))
			return false;
		final Object this$unit = this.getUnit();
		final Object other$unit = other.getUnit();
		if (this$unit == null ? other$unit != null : !this$unit
				.equals(other$unit))
			return false;
		return true;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $id = this.getId();
		result = result * PRIME + ($id == null ? 0 : $id.hashCode());
		final Object $resource = this.getResource();
		result = result * PRIME
				+ ($resource == null ? 0 : $resource.hashCode());
		final Object $metric = this.getMetric();
		result = result * PRIME + ($metric == null ? 0 : $metric.hashCode());
		final Object $unit = this.getUnit();
		result = result * PRIME + ($unit == null ? 0 : $unit.hashCode());
		return result;
	}

	public boolean canEqual(Object other) {
		return other instanceof MeasurementDto;
	}

	@Override
	public String toString() {
		return "MeasurementDto [id=" + id + ", resource=" + resource
				+ ", metric=" + metric + ", unit=" + unit + ", monitor="
				+ monitor + "]";
	}
}
