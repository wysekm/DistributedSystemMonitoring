package pl.edu.agh.dsm.front.web.view.dto;

import pl.edu.agh.dsm.front.core.model.rest.dto.ComplexMeasurementDto;

/**
 * Created by Tom on 2014-05-27.
 */
public class MeasurementInput {

	public String addUri;
	public ComplexMeasurementInput complexDetails = new ComplexMeasurementInput();

	public String getAddUri() {
		return addUri;
	}

	public void setAddUri(String addUri) {
		this.addUri = addUri;
	}

	public ComplexMeasurementInput getComplexDetails() {
		return complexDetails;
	}

	public void setComplexDetails(ComplexMeasurementInput complexDetails) {
		this.complexDetails = complexDetails;
	}
}
