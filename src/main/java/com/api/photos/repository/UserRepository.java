package com.api.photos.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestOperations;

import com.api.photos.exception.NotFoundException;
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
	private RestOperations rest;
	@Autowired
	private JdbcOperations jdbc;
	
	@Override
	public List<User> getAll(){
		ResponseEntity<User[]> response = rest.getForEntity(USER_LIST_URL, User[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public User get(int id) {
		ResponseEntity<User> response = rest.getForEntity(USER_LIST_URL + "/" + id, User.class);
		return response.getBody();
	}

	@Override
	public List<User> getByAlbumAndPermissions(int albumId, boolean writePerm, boolean readPerm) throws NotFoundException{
		return this.getUserIdsByAlbumAndPermissions(albumId, writePerm, readPerm)
		.stream()
		.map(this::get)
		.collect(Collectors.toList());
	}

	private List<Integer> getUserIdsByAlbumAndPermissions(int albumId, boolean writePerm, boolean readPerm) throws NotFoundException{
		Object[] params = {albumId,  readPerm, writePerm};
		List<Integer> result = jdbc.query(SQL_GET_USER_IDS_FOR_ALBUM_AND_PERMISSIONS, params, 
				(rs, rowNum) -> Integer.valueOf(rs.getInt("user_id")));
		if(result.isEmpty()) {
			throw new NotFoundException();
		}
		return result;
	}

}
