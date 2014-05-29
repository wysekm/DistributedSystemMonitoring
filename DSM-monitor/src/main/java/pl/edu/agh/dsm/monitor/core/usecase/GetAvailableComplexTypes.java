package pl.edu.agh.dsm.monitor.core.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.UseCase;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurementsService;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexType;

import java.util.List;

/**
 * Created by Tom on 2014-05-28.
 */
@UseCase
public class GetAvailableComplexTypes {

	@Autowired
	ComplexMeasurementsService complexMeasurementsService;

	public List<ComplexType> getAvailableComplexTypes() {
		return complexMeasurementsService.getAvailableComplexTypes();
	}
}
