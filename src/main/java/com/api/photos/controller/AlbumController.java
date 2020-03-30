package com.api.photos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.api.photos.model.Album;
import com.api.photos.service.AlbumService;

@RestController
public class AlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@GetMapping("/albums")
	public ResponseEntity<List<Album>> getAllUsers(){
		return ResponseEntity.ok(albumService.getAllAlbums());
	}
	@GetMapping("/albums/{id}")
	public ResponseEntity<Album> get(@PathVariable int id){
		return ResponseEntity.ok(albumService.get(id));
	}

}
