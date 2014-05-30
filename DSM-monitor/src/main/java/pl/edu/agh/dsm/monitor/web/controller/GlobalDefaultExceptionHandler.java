package pl.edu.agh.dsm.monitor.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.edu.agh.dsm.monitor.core.infrastructure.InternalErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	static final Logger log = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

	@Autowired
	ObjectMapper objectMapper;

	@ExceptionHandler(value = InternalErrorException.class)
	public ResponseEntity<String> defaultErrorHandler(HttpServletRequest req, InternalErrorException e)
			throws Exception {
		log.error("received error ({}) with message: {}", e.getFailureStatus(), e.getMessage());
		String responseBody = objectMapper.writer().writeValueAsString(e.getErrorMessageDto());
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.put("Content-Type", Arrays.asList(MediaType.APPLICATION_JSON_VALUE));
		ResponseEntity<String> response = new ResponseEntity<String>(responseBody, headers, e.getFailureStatus());
		return response;
	}
}