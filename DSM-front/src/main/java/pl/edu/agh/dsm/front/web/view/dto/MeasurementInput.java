package pl.edu.agh.dsm.front.web.view.dto;

import pl.edu.agh.dsm.front.core.model.rest.dto.ComplexMeasurementDto;

/**
 * Created by Tom on 2014-05-27.
 */
public class MeasurementInput {

	public String addUri;
	public ComplexMeasurementDto complexDetails = new ComplexMeasurementDto();

	public String getAddUri() {
		return addUri;
	}

	public void setAddUri(String addUri) {
		this.addUri = addUri;
	}

	public ComplexMeasurementDto getComplexDetails() {
		return complexDetails;
	}

	public void setComplexDetails(ComplexMeasurementDto complexDetails) {
		this.complexDetails = complexDetails;
	}
}
