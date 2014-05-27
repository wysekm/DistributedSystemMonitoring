package pl.edu.agh.dsm.front.web.Infrastructure;

import pl.edu.agh.dsm.front.core.model.rest.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.front.web.view.dto.ComplexMeasurementInput;

/**
 * Created by Tom on 2014-05-27.
 */
public class ComplexConverter {

	public static ComplexMeasurementDto convert(ComplexMeasurementInput complex) {
		return new ComplexMeasurementDto(complex.getBaseMeasurementUri(),
				complex.getTypeCode(), complex.getParameters(), null);
	}
}
