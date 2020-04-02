package com.api.photos.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.photos.dto.input.PostPermissionDTO;
import com.api.photos.service.PermissionService;

@RunWith(SpringRunner.class)
public class PermissionControllerTests {
	
	@Mock
	private PermissionService permissionService;
	
	@InjectMocks
	private PermissionController permissionController;
	
	@Test
	public void testPut() {
		//Arrange
		PostPermissionDTO dto = new PostPermissionDTO();
		Mockito.doNothing().when(permissionService).put(Mockito.anyInt(), Mockito.anyInt(), 
				Mockito.anyBoolean(), Mockito.anyBoolean());
		ResponseEntity<ResponseMessage> expectedResponse = ResponseEntity.ok(new ResponseMessage("Confirmed"));
		//Act
		ResponseEntity<ResponseMessage> actualResponse = permissionController.put(dto);
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}

}
