package pl.edu.agh.dsm.front.core.model.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;
import pl.edu.agh.dsm.front.core.model.rest.dto.MeasurementDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tom on 2014-05-26.
 */
@Service
public class DetailedMeasurementService {

	@Autowired
	RestClientService restClientService;

	@Value("${measurements.uri}")
	String measurementsUri;

	public Collection<Resource<MeasurementDto>> getDetailedMeasurements(
			String metric, String resource, UserCredentials user)
	{
		Collection<Resource<MeasurementDto>> measurements =
				restClientService.getCatalogueMeasurements(measurementsUri, metric, resource);
		List<Resource<MeasurementDto>> result = new ArrayList<>();
		for (Resource<MeasurementDto> measurement : measurements) {
			String detailsUri = measurement.getLink("details").getHref();
			Resource<MeasurementDto> details = restClientService.getMonitorMeasurement(detailsUri, user);
			if (details.hasLink("complexDetails")) {
				String complexDetailsUri = details.getLink("complexDetails").getHref();
				details.getContent().setComplexDetails(
						restClientService.getComplexDetails(complexDetailsUri).getContent());
			}
			result.add(details);
		}
		return result;
	}
}
