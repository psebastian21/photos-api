package com.api.photos.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.api.photos.model.User;

@Repository
public class UserRepository implements IUserRepository {
	
	private static final String USER_LIST_URL = "https://jsonplaceholder.typicode.com/users";
	private static final String SQL_GET_USER_IDS_FOR_ALBUM_AND_PERMISSIONS = "select\r\n" + 
			"	user_id\r\n" + 
			"from\r\n" + 
			"	photos.permission\r\n" + 
			"where\r\n" + 
			"	album_id = ? and read_permission = ? and write_permission = ?";
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private JdbcOperations jdbc;
	
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

	@Override
	public List<User> getUsersForAlbumAndPermissions(int albumId, boolean writePerm, boolean readPerm) {
		return this.getUserIdsForAlbumAndPermissions(albumId, writePerm, readPerm)
		.stream()
		.map(this::get)
		.collect(Collectors.toList());
	}
	
	private List<Integer> getUserIdsForAlbumAndPermissions(int albumId, boolean writePerm, boolean readPerm){
		Object[] params = {albumId,  readPerm, writePerm};
		RowMapper<Integer> rm = (rs, rowNum) -> Integer.valueOf(rs.getInt("user_id"));
		return jdbc.query(SQL_GET_USER_IDS_FOR_ALBUM_AND_PERMISSIONS, params, rm);
	}

}
