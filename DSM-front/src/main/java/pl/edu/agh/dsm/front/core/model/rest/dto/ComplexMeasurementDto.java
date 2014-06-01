package pl.edu.agh.dsm.front.core.model.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 2014-05-23.
 */
public class ComplexMeasurementDto {

	public static class User {

		String name;

		public User() {
		}

		public User(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private String type;
	private User createdBy;
	private List<ComplexMeasurementParameter> params = new ArrayList<>();

	public ComplexMeasurementDto() {
	}

	public ComplexMeasurementDto(String type, Map<String, String> parameters, String createdBy) {
		this.type = type;
		this.createdBy = new User(createdBy);
		for(Map.Entry<String, String> entry : parameters.entrySet()) {
			params.add(new ComplexMeasurementParameter(entry.getKey(), entry.getValue()));
		}
	}

	public ComplexMeasurementDto(String type, List<ComplexMeasurementParameter> params, String createdBy) {
		this.type = type;
		this.params = params;
		this.createdBy = new User(createdBy);
	}

	public String getType() {
		return type;
	}

	public String getCreatedBy() {
		return createdBy.name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ComplexMeasurementParameter> getParams() {
		return params;
	}

	@Override
	public String toString() {
		return "ComplexMeasurementDto{" +
				", type='" + type + '\'' +
				", params=" + params +
				", createdBy='" + createdBy + '\'' +
				'}';
	}
}
