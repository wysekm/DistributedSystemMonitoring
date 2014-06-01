package pl.edu.agh.dsm.front.web.Infrastructure;

import pl.edu.agh.dsm.front.core.model.rest.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.front.core.model.rest.dto.ComplexMeasurementOutDto;
import pl.edu.agh.dsm.front.web.view.dto.ComplexMeasurementInput;

/**
 * Created by Tom on 2014-05-27.
 */
public class ComplexConverter {

	public static ComplexMeasurementOutDto convert(ComplexMeasurementInput complex) {
		return new ComplexMeasurementOutDto(complex.getBaseMeasurementUri(),
				complex.getTypeCode(), complex.getParameters());
	}
}
