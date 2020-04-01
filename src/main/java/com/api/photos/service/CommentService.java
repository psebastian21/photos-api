package com.api.photos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.photos.model.Comment;
import com.api.photos.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public List<Comment> getAll(){
		return this.commentRepository.getAll();
	}
	public Comment get(int id) {
		return this.commentRepository.get(id);
	}
	public List<Comment> getByName(String name){
		return this.commentRepository.getByName(name);
	}
	public List<Comment> getByUser(int userId){
		return this.commentRepository.getByUser(userId);
	}
	public List<Comment> getByPost(int postId){
		return this.commentRepository.getByPost(postId);
	}

}
