package com.api.photos.repository;

import java.util.List;

import com.api.photos.model.Album;

public interface IAlbumRepository {
	
	List<Album> getAll();
	Album get(int id);
	List<Album> getByUser(int userId);

}
