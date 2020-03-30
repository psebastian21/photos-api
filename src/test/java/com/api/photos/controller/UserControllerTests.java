package com.api.photos.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.photos.model.User;
import com.api.photos.service.UserService;

import org.junit.Assert;

@RunWith(SpringRunner.class)
public class UserControllerTests {
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserController userController;
	
	@Test
	public void testGetAllUsers() {
		//Arrange
		User u1 = new User();
		u1.setId(1);
		User u2 = new User();
		u2.setId(2);
		List<User> users = Arrays.asList(u1, u2);
		ResponseEntity<List<User>> expectedResponse = ResponseEntity.ok(users);
		Mockito.when(userService.getAllUsers()).thenReturn(users);
		//Act
		ResponseEntity<List<User>> actualResponse = userController.getAllUsers();
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}
	@Test
	public void testGet() {
		//Arrange
		User u1 = new User();
		u1.setId(1);
		Mockito.when(userService.get(1)).thenReturn(u1);
		ResponseEntity<User> expectedResponse = ResponseEntity.ok(u1);
		//Act
		ResponseEntity<User> actualResponse = userController.get(1);
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}

}
