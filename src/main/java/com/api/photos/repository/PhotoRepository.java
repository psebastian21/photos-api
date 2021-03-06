package com.api.photos.repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestOperations;

import com.api.photos.model.Album;
import com.api.photos.model.Photo;

@Repository
public class PhotoRepository implements IPhotoRepository {
	
	private static final String PHOTO_LIST_URL = "https://jsonplaceholder.typicode.com/photos";
	private static final String PHOTOS_FOR_ALBUM_URL = "https://jsonplaceholder.typicode.com/albums/%d/photos";
	
	@Autowired
	private RestOperations rest;
	@Autowired
	private IAlbumRepository albumRepository;

	@Override
	public List<Photo> getAll() {
		ResponseEntity<Photo[]> response = rest.getForEntity(PHOTO_LIST_URL, Photo[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public Photo get(int id) {
		ResponseEntity<Photo> response = rest.getForEntity(PHOTO_LIST_URL + "/" + id, Photo.class);
		return response.getBody();
	}

	@Override
	public List<Photo> getByAlbum(int albumId) {
		ResponseEntity<Photo[]> response = rest
				.getForEntity(String.format(PHOTOS_FOR_ALBUM_URL, albumId), Photo[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public List<Photo> getByUser(int userId) {
		List<Photo> photos = new LinkedList<>();
		albumRepository.getByUser(userId)
		.stream()
		.mapToInt(Album::getId)
		.forEach(albumId -> photos.addAll(this.getByAlbum(albumId)));
		return photos;
	}

}
