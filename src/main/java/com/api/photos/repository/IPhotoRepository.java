package com.api.photos.repository;

import java.util.List;

import com.api.photos.model.Photo;

public interface IPhotoRepository {
	
	List<Photo> getAll();
	Photo get(int id);
	List<Photo> getPhotosForAlbum(int albumId);
	List<Photo> getPhotosForUser(int userId);

}
