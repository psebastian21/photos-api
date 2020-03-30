package com.api.photos.repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.api.photos.model.Album;
import com.api.photos.model.Photo;

@Repository
public class PhotoRepositoryWebAndSQLImpl implements IPhotoRepository {
	
	private static final String PHOTO_LIST_URL = "https://jsonplaceholder.typicode.com/photos";
	private static final String PHOTOS_FOR_ALBUM_URL = "https://jsonplaceholder.typicode.com/albums/%d/photos";
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private IAlbumRepository albumRepository;

	@Override
	public List<Photo> getAllPhotos() {
		ResponseEntity<Photo[]> response = restTemplate.getForEntity(PHOTO_LIST_URL, Photo[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public Photo get(int id) {
		ResponseEntity<Photo> response = restTemplate.getForEntity(PHOTO_LIST_URL + "/" + id, Photo.class);
		return response.getBody();
	}

	@Override
	public List<Photo> getPhotosForAlbum(int albumId) {
		ResponseEntity<Photo[]> response = restTemplate
				.getForEntity(String.format(PHOTOS_FOR_ALBUM_URL, albumId), Photo[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public List<Photo> getPhotosForUser(int userId) {
		List<Photo> photos = new LinkedList<>();
		albumRepository.getAlbumsForUser(userId)
		.stream()
		.mapToInt(Album::getId)
		.forEach(albumId -> photos.addAll(this.getPhotosForAlbum(albumId)));
		return photos;
	}

}
