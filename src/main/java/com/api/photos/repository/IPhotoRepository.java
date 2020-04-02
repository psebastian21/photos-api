package com.api.photos.repository;

import java.util.List;

import com.api.photos.model.Photo;

public interface IPhotoRepository {
	
	List<Photo> getAll();
	Photo get(int id);
	List<Photo> getByAlbum(int albumId);
	List<Photo> getByUser(int userId);

}
