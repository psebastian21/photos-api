package com.api.photos.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.api.photos.model.User;

@RunWith(SpringRunner.class)
public class UserRepositoryTests {
	
	private static final String USER_LIST_URL = "https://jsonplaceholder.typicode.com/users";
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private UserRepository userRepository;

	@Test
	public void testGetAllUsers() {
		//Arrange
		User u1 = new User();
		u1.setId(1);
		User u2 = new User();
		u2.setId(2);
		User[] users = {u1, u2};
		ResponseEntity<User[]> response = ResponseEntity.ok(users);
		Mockito.when(restTemplate.getForEntity(USER_LIST_URL, User[].class)).thenReturn(response);
		//Act
		List<User> userList = userRepository.getAll();
		//Assert
		Assert.assertArrayEquals(users, userList.toArray(new User[0]));
	}
	@Test
	public void testGet() {
		//Arrange
		User u1 = new User();
		u1.setId(1);
		ResponseEntity<User> expectedResponse = ResponseEntity.ok(u1);
		Mockito.when(restTemplate.getForEntity(USER_LIST_URL + "/" + 1, User.class)).thenReturn(expectedResponse);
		//Act
		User response = userRepository.get(1);
		//Assert
		Assert.assertEquals(expectedResponse.getBody().getId(), response.getId());
	}

}
