package com.api.photos.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.photos.model.User;
import com.api.photos.repository.IUserRepository;

@RunWith(SpringRunner.class)
public class UserServiceTests {
	
	@Mock
	private IUserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	@Test
	public void testGetAllUsers() {
		//Arrange
		User u1 = new User();
		u1.setId(1);
		User u2 = new User();
		u2.setId(2);
		List<User> users = Arrays.asList(u1, u2);
		Mockito.when(userRepository.getAll()).thenReturn(users);
		//Act
		List<User> response = userService.getAll();
		//Assert
		Assert.assertEquals(users, response);
	}
	@Test
	public void testGet() {
		//Arrange
		User u1 = new User();
		u1.setId(1);
		Mockito.when(userRepository.get(1)).thenReturn(u1);
		//Act
		User response = userService.get(1);
		//Assert
		Assert.assertEquals(u1.getId(), response.getId());
	}

}
