package pl.edu.agh.dsm.front.dto;

import java.util.ArrayList;
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

		public Parameter(String paramName, String paramCode, boolean required) {
			this.required = required;
			this.paramCode = paramCode;
			this.paramName = paramName;
		}
	}

	public String typeName;
	public String typeCode;
	public List<Parameter> typeList;

	public ComplexTypeDto(String typeName, String typeCode, Parameter... parameters) {
		this.typeName = typeName;
		this.typeCode = typeCode;
		this.typeList = Arrays.asList(parameters);
	}
}
