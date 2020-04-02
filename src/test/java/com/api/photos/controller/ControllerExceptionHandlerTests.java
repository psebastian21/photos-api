package com.api.photos.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.api.photos.exception.PermissionPuttingException;

@RunWith(SpringRunner.class)
public class ControllerExceptionHandlerTests {
	
	@Mock
	private Logger controllerAdvicelogger;
	@Mock
	private WebRequest webRequest;
	
	@InjectMocks
	private ControllerExceptionHandler ceHandler;
	
	@Before
	public void before() {
		Mockito.doNothing().when(controllerAdvicelogger).info(Mockito.anyString(), Mockito.any(Exception.class));
		Mockito.doNothing().when(controllerAdvicelogger).error(Mockito.anyString(), Mockito.any(Exception.class));
	}
	
	@Test
	public void testHandleEntityNotFound() {
		//Arrange
		Exception e = new Exception();
		//Act
		ResponseEntity<ResponseMessage> response = ceHandler.handleEntityNotFound(e, webRequest);
		//Assert
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	@Test
	public void testHandleTypeMismatch() {
		//Arrange
		MethodArgumentTypeMismatchException e = Mockito.mock(MethodArgumentTypeMismatchException.class);
		//Act
		ResponseEntity<ResponseMessage> response = ceHandler.handleTypeMismatch(e, webRequest);
		//Assert
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	@Test
	public void testHandleDataIntegrityViolation() {
		//Arrange
		DataIntegrityViolationException e = Mockito.mock(DataIntegrityViolationException.class);
		//Act
		ResponseEntity<ResponseMessage> response = ceHandler.handleDataIntegrityViolation(e, webRequest);
		//Assert
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	@Test
	public void testHandlePermissionPuttingException() {
		//Arrange
		PermissionPuttingException e = Mockito.mock(PermissionPuttingException.class);
		//Act
		ResponseEntity<ResponseMessage> response = ceHandler.handlePermissionPuttingException(e, webRequest);
		//Assert
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	@Test
	public void testHandleJsonParsingException() {
		//Arrange
		HttpMessageNotReadableException e = Mockito.mock(HttpMessageNotReadableException.class);
		//Act
		ResponseEntity<ResponseMessage> response = ceHandler.handleJsonParsingException(e, webRequest);
		//Assert
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	@Test
	public void testDefaultExceptionHandler() {
		//Arrange
		HttpMessageNotReadableException e = Mockito.mock(HttpMessageNotReadableException.class);
		//Act
		ResponseEntity<ResponseMessage> response = ceHandler.defaultExceptionHandler(e, webRequest);
		//Assert
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

}
