package com.api.photos.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.api.photos.exception.NotFoundException;
import com.api.photos.exception.PermissionPuttingException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	@ExceptionHandler(value = { HttpClientErrorException.NotFound.class, NotFoundException.class })
	protected ResponseEntity<ResponseMessage> handleEntityNotFound(Exception e, WebRequest request) {
		logger.info("Not found", e);
		ResponseMessage message = new ResponseMessage("Entity not found");
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<ResponseMessage> handleTypeMismatch(MethodArgumentTypeMismatchException e, WebRequest request){
		logger.info("Wrong argument type on controller", e);
		ResponseMessage message = new ResponseMessage("Bad request");
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<ResponseMessage> handleDataIntegrityViolation(DataIntegrityViolationException e, WebRequest request){
		logger.info("Constraint violation attempted", e);
		ResponseMessage message = new ResponseMessage("Illegal operation");
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(PermissionPuttingException.class)
	protected ResponseEntity<ResponseMessage> handlePermissionPuttingException(PermissionPuttingException e, WebRequest request){
		logger.info(e.getMessage(), e);
		ResponseMessage message = new ResponseMessage(e.getMessage());
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ResponseMessage> handleJsonParsingException(HttpMessageNotReadableException e, WebRequest request){
		logger.info("Bad request, Json wrongly formatted", e);
		ResponseMessage message = new ResponseMessage("Bad request, Json wrongly formatted");
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	
	private class ResponseMessage{
		private String message;
		
		private ResponseMessage(String message) {
			this.message = message;
		}

		@SuppressWarnings("unused")
		public String getMessage() {
			return message;
		}

		@SuppressWarnings("unused")
		public void setMessage(String message) {
			this.message = message;
		}
	}

}
