package pl.edu.agh.dsm.front.core.model.rest.dto;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Tom on 2014-05-22.
 */
public class ComplexTypeDto {

	public static class Parameter {
		public String paramName;
		public String paramCode;
		public boolean required;

		public Parameter() {}

		public Parameter(String paramName, String paramCode, boolean required) {
			this.required = required;
			this.paramCode = paramCode;
			this.paramName = paramName;
		}
	}

	public String typeName;
	public String typeCode;
	public List<Parameter> params;

	public ComplexTypeDto() {}

	public ComplexTypeDto(String typeName, String typeCode, Parameter... params) {
		this.typeName = typeName;
		this.typeCode = typeCode;
		this.params = Arrays.asList(params);
	}
}
