package pl.edu.agh.dsm.monitor.internalApi;

import static pl.edu.agh.dsm.common.utils.HttpClientUtil.makeDeleteRequest;
import static pl.edu.agh.dsm.common.utils.HttpClientUtil.makeGetRequest;
import static pl.edu.agh.dsm.common.utils.HttpClientUtil.makePostRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.common.InternalErrorException;
import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.utils.HttpClientUtil.HttpResponseResult;
import pl.edu.agh.dsm.monitor.MonitorConfig;

import java.io.IOException;
import java.util.UUID;

@Component
public class CatalogueProxyImpl implements CatalogueProxy{

	static final Logger logger = LoggerFactory
			.getLogger(CatalogueProxyImpl.class);

    @Value("${catalogue.address}")
    String catalogueAdress;

    @Autowired
    ObjectMapper objectMapper;


	@Override
	public void addMeasurement(MeasurementDto dto) {


//        ObjectWriter objectWriter = objectMapper.writerWithView("JakisProfil".getClass());
//        objectWriter.writeValueAsString()

		logger.debug("send info about new measurement with id {}", dto);

		String data = "{\n" + "\"id\":\"" + dto.getId() + "\",\n"
				+ "\"monitor\": \"" + dto.getMonitor() + "\",\n"
				+ "\"resource\": \"" + dto.getResource() + "\",\n"
				+ "\"unit\": \"" + dto.getUnit() + "\",\n"
				+ "\"metric\": \"" + dto.getMetric() + "\"\n" + "}";
		HttpEntity dataEntity = new StringEntity(data,
				ContentType.APPLICATION_JSON);
		Credentials userCredentials = new UsernamePasswordCredentials(
				"monitor", "monitor");
		HttpResponseResult result = null;
		try {
			result = makePostRequest(catalogueAdress
					+ "/measurements", dataEntity, userCredentials);
			if (result.statusLine.getStatusCode() != HttpStatus.SC_CREATED)
				throw new InternalErrorException(
						result.statusLine.getReasonPhrase());
		} catch (IOException e) {
			throw new InternalErrorException(e.getMessage());
		}
	}

	@Override
	public void removeMeasurement(UUID uuid) {
		logger.debug("send info about removed measurement with id {}", uuid);

		Credentials userCredentials = new UsernamePasswordCredentials(
				"monitor", "monitor");
		HttpResponseResult result = null;
		try {
			result = makeDeleteRequest(catalogueAdress + "/measurements/"
					+ uuid, userCredentials);
			if (result.statusLine.getStatusCode() != HttpStatus.SC_OK)
				throw new InternalErrorException(
						result.statusLine.getReasonPhrase());
		} catch (IOException e) {
			throw new InternalErrorException(e.getMessage());
		}
	}
}
