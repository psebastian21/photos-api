package com.api.photos.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestOperations;

import com.api.photos.exception.NotFoundException;
import com.api.photos.model.Album;

@Repository
public class AlbumRepository implements IAlbumRepository {
	
	private static final String ALBUM_LIST_URL = "https://jsonplaceholder.typicode.com/albums";
	private static final String ALBUMS_PER_USER_URL = "https://jsonplaceholder.typicode.com/users/%d/albums";
	
	@Autowired
	private RestOperations rest;

	@Override
	public List<Album> getAll() {
		ResponseEntity<Album[]> response = rest.getForEntity(ALBUM_LIST_URL, Album[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public Album get(int id) {
		ResponseEntity<Album> response = rest.getForEntity(ALBUM_LIST_URL + "/" + id, Album.class);
		return response.getBody();
	}

	@Override
	public List<Album> getByUser(int userId) {
		ResponseEntity<Album[]> response = rest
				.getForEntity(String.format(ALBUMS_PER_USER_URL, userId), Album[].class);
		if(response.getBody().length == 0) {
			throw new NotFoundException();
		}
		return Arrays.asList(response.getBody());
	}

}
