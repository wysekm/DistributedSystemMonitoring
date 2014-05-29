package pl.edu.agh.dsm.monitor.core.model.measurement.complex;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Tom on 2014-05-29.
 */
public class ComplexType {

	public static class Parameter {
		public String paramName;
		public String paramCode;
		public boolean required;

		public Parameter(String paramName, String paramCode, boolean required) {
			this.required = required;
			this.paramCode = paramCode;
			this.paramName = paramName;
		}

		public String getParamName() {
			return paramName;
		}

		public String getParamCode() {
			return paramCode;
		}

		public boolean isRequired() {
			return required;
		}
	}

	public String typeName;
	public String typeCode;
	public List<Parameter> params;

	public ComplexType(String typeName, String typeCode, Parameter... parameters) {
		this.typeName = typeName;
		this.typeCode = typeCode;
		this.params = Arrays.asList(parameters);
	}

	public String getTypeName() {
		return typeName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public List<Parameter> getParams() {
		return params;
	}

	public boolean containsParam(String paramCode) {
		for(Parameter param : params) {
			if(param.paramCode.equals(paramCode)) {
				return true;
			}
		}
		return false;
	}
}
