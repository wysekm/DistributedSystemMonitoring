package pl.edu.agh.dsm.front.core.model.rest.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 2014-05-23.
 */
public class ComplexMeasurementOutDto {

	private String baseMeasurementUri;
	private String type;
	private List<ComplexMeasurementParameter> params = new ArrayList<>();

	public ComplexMeasurementOutDto() {
	}

	public ComplexMeasurementOutDto(String baseMeasurementUri, String typeCode, Map<String, String> parameters) {
		this.type = typeCode;
		this.baseMeasurementUri = baseMeasurementUri;
		for(Map.Entry<String, String> entry : parameters.entrySet()) {
			params.add(new ComplexMeasurementParameter(entry.getKey(), entry.getValue()));
		}
	}

	public String getType() {
		return type;
	}

	public void setBaseMeasurementUri(String baseMeasurementUri) {
		this.baseMeasurementUri = baseMeasurementUri;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ComplexMeasurementParameter> getParams() {
		return params;
	}

	public void setParams(List<ComplexMeasurementParameter> params) {
		this.params = params;
	}

	public String getBaseMeasurementUri() {
		return baseMeasurementUri;
	}

	@Override
	public String toString() {
		return "ComplexMeasurementDto{" +
				"baseMeasurementUri='" + baseMeasurementUri + '\'' +
				", type='" + type + '\'' +
				", params=" + params +
				'}';
	}
}
