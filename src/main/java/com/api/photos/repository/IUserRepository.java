package com.api.photos.repository;

import java.util.List;

import com.api.photos.model.User;

public interface IUserRepository {

	List<User> getAllUsers();
	User get(int id);
	List<User> getUsersForAlbumAndPermissions(int albumId, boolean writePerm, boolean readPerm);

}