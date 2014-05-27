package pl.edu.agh.dsm.front.web.view.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 2014-05-23.
 */
public class ComplexMeasurementInput {

	private String baseMeasurementUri;
	private String typeCode;
	private Map<String, String> parameters = new HashMap<>();

	public ComplexMeasurementInput() {
	}

	public ComplexMeasurementInput(String typeCode, Map<String, String> parameters) {
		this.typeCode = typeCode;
		this.parameters = parameters;
	}

	public ComplexMeasurementInput(String baseMeasurementUri, String typeCode, Map<String, String> parameters) {
		this(typeCode, parameters);
		this.baseMeasurementUri = baseMeasurementUri;
	}

	public String getBaseMeasurementUri() {
		return baseMeasurementUri;
	}

	public void setBaseMeasurementUri(String baseMeasurementUri) {
		this.baseMeasurementUri = baseMeasurementUri;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
}
