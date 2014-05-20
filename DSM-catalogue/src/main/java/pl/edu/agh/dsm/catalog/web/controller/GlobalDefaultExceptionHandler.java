package pl.edu.agh.dsm.catalog.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pl.edu.agh.dsm.catalog.core.infrastructure.InternalErrorException;


@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(value = InternalErrorException.class)
	public ResponseEntity<String> defaultErrorHandler(HttpServletRequest req, InternalErrorException e)
			throws Exception {
		return new ResponseEntity<String>(e.getMessage(), e.getFailureStatus());
	}
}