package pl.edu.agh.dsm.monitor.core.model.measurement.complex;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import pl.edu.agh.dsm.monitor.core.model.user.ApplicationUser;

import java.util.List;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ComplexMeasurement {

	// TODO create ComplexMeasurementDto in web.view.dto that will have URL instead of UUID
	// http://127.0.0.1:8081/measurements/359a2051-ab94-40f6-b89a-f4ffec4da2a7",


	UUID id;
	UUID baseMeasurementId;
	String type;
	List<ComplexMeasurementParam> params;
	ApplicationUser createdBy;

	public ComplexMeasurement(UUID id, UUID baseMeasurementId, String type, List<ComplexMeasurementParam> params, ApplicationUser createdBy) {
		this.id = id;
		this.baseMeasurementId = baseMeasurementId;
		this.type = type;
		this.params = params;
		this.createdBy = createdBy;
	}

	public UUID getId() {
		return id;
	}

	public UUID getBaseMeasurementId() {
		return baseMeasurementId;
	}

	public String getType() {
		return type;
	}

	public List<ComplexMeasurementParam> getParams() {
		return params;
	}

	public ApplicationUser getCreatedBy() {
		return createdBy;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ComplexMeasurement that = (ComplexMeasurement) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
