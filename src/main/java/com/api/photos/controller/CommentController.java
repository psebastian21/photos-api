package com.api.photos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.api.photos.model.Comment;
import com.api.photos.service.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/comments")
	public ResponseEntity<List<Comment>> getAll(){
		return ResponseEntity.ok(this.commentService.getAll());
	}
	@GetMapping("/comments/name/{name}")
	public ResponseEntity<List<Comment>> getByName(@PathVariable String name){
		return ResponseEntity.ok(this.commentService.getByName(name));
	}
	@GetMapping("/comments/userid/{userId}")
	public ResponseEntity<List<Comment>> getByUser(@PathVariable int userId){
		return ResponseEntity.ok(this.commentService.getByUser(userId));
	}
	@GetMapping("/comments/{id}")
	public ResponseEntity<Comment> get(@PathVariable int id){
		return ResponseEntity.ok(this.commentService.get(id));
	}

}
