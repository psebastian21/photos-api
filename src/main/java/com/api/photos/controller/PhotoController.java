package com.api.photos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.api.photos.model.Photo;
import com.api.photos.service.PhotoService;

@RestController
public class PhotoController {
	
	@Autowired
	private PhotoService photoService;
	
	@GetMapping("/photos")
	public ResponseEntity<List<Photo>> getAll(){
		return ResponseEntity.ok(photoService.getAll());
	}
	@GetMapping("/photos/{id}")
	public ResponseEntity<Photo> get(@PathVariable int id){
		return ResponseEntity.ok(photoService.get(id));
	}
	@GetMapping("/users/{userId}/photos")
	public ResponseEntity<List<Photo>> getByUser(@PathVariable int userId){
		return ResponseEntity.ok(photoService.getByUser(userId));
	}

}
