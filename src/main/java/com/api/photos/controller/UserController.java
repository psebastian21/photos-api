package com.api.photos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.api.photos.model.User;
import com.api.photos.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAll(){
		return ResponseEntity.ok(userService.getAll());
	}
	@GetMapping("/users/{id}")
	public ResponseEntity<User> get(@PathVariable int id){
		return ResponseEntity.ok(userService.get(id));
	}
	@GetMapping("/users/{albumId}/{wPerm}/{rPerm}")
	public ResponseEntity<List<User>> getByAlbumAndPermissions(@PathVariable int albumId, 
			@PathVariable boolean wPerm, @PathVariable boolean rPerm) {
		return ResponseEntity.ok(userService.getByAlbumAndPermissions(albumId, wPerm, rPerm));
	}

}
