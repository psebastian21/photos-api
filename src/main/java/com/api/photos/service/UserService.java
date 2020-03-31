package com.api.photos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.photos.model.User;
import com.api.photos.repository.IUserRepository;

@Service
public class UserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	public List<User> getAllUsers(){
		return userRepository.getAllUsers();
	}
	public User get(int id){
		return userRepository.get(id);
	}
	public List<User> getUsersForAlbumAndPermissions(int albumId, boolean writePerm, boolean readPerm) {
		return userRepository.getUsersForAlbumAndPermissions(albumId, writePerm, readPerm);
	}

}
