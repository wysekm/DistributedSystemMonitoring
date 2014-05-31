package pl.edu.agh.dsm.monitor.web.view.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurementParam;
import pl.edu.agh.dsm.monitor.core.model.user.ApplicationUser;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ComplexMeasurementInDto {

	String baseMeasurementUri;
	String type;
	List<ComplexMeasurementParam> params;

	@ConstructorProperties({ "baseMeasurementUri", "type", "params" })
	public ComplexMeasurementInDto(String baseMeasurementUri, String type, List<ComplexMeasurementParam> params) {
		this.baseMeasurementUri = baseMeasurementUri;
		this.type = type;
		this.params = params;
	}

	public ComplexMeasurementInDto() {}

	public String getBaseMeasurementUri() {
		return baseMeasurementUri;
	}

	public String getType() {
		return type;
	}

	public List<ComplexMeasurementParam> getParams() {
		return params;
	}

	@Override
	public String toString() {
		return "ComplexMeasurementInDto{" +
				", baseMeasurementUri='" + baseMeasurementUri + '\'' +
				", type='" + type + '\'' +
				", params=" + params +
				'}';
	}
}
