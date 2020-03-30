package com.api.photos.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.api.photos.model.User;

@Repository
public class UserRepositoryWebAndSQLImpl implements IUserRepository {
	
	private static final String USER_LIST_URL = "https://jsonplaceholder.typicode.com/users";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<User> getAllUsers(){
		ResponseEntity<User[]> response = restTemplate.getForEntity(USER_LIST_URL, User[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public User get(int id) {
		ResponseEntity<User> response = restTemplate.getForEntity(USER_LIST_URL + "/" + id, User.class);
		return response.getBody();
	}

}
