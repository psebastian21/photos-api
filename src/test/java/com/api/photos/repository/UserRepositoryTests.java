package com.api.photos.repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestOperations;

import com.api.photos.exception.NotFoundException;
import com.api.photos.model.User;

@RunWith(SpringRunner.class)
public class UserRepositoryTests {
	
	private static final String USER_LIST_URL = "https://jsonplaceholder.typicode.com/users";
	
	@Mock
	private RestOperations rest;
	@Mock
	private JdbcOperations jdbc;
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
		Mockito.when(rest.getForEntity(USER_LIST_URL, User[].class)).thenReturn(response);
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
		Mockito.when(rest.getForEntity(USER_LIST_URL + "/" + 1, User.class)).thenReturn(expectedResponse);
		//Act
		User response = userRepository.get(1);
		//Assert
		Assert.assertEquals(expectedResponse.getBody().getId(), response.getId());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testGetByAlbumAndPermissions() {
		//Arrange
		List<Integer> userIds = Arrays.asList(1, 2, 3);
		Mockito.when(jdbc.query(Mockito.anyString(), Mockito.any(Object[].class), Mockito.any(RowMapper.class)))
		.thenReturn(userIds);
		User u1 = new User();
		u1.setId(1);
		User u2 = new User();
		u2.setId(2);
		User u3 = new User();
		u3.setId(3);
		Mockito.when(rest.getForEntity(Mockito.anyString(), Mockito.any(Class.class)))
		.thenReturn(ResponseEntity.ok(u1),  ResponseEntity.ok(u2), ResponseEntity.ok(u3));
		List<User> expected = Arrays.asList(u1, u2, u3);
		//Act
		List<User> actual = userRepository.getByAlbumAndPermissions(1, true, true);
		//Assert
		Assert.assertEquals(expected, actual);
	}
	@SuppressWarnings("unchecked")
	@Test(expected = NotFoundException.class)
	public void testGetByAlbumAndPermissionsWhenNotFoundThenThrowNotFoundException() {
		//Arrange
		Mockito.when(jdbc.query(Mockito.anyString(), Mockito.any(Object[].class), Mockito.any(RowMapper.class)))
		.thenReturn(Collections.<Integer>emptyList());
		//Act
		userRepository.getByAlbumAndPermissions(1, true, true);
	}

}
