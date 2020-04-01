package com.api.photos.repository;

import java.util.List;

import com.api.photos.model.Comment;

public interface ICommentRepository {
	
	List<Comment> getAll();
	Comment get(int id);
	List<Comment> getByName(String name);
	List<Comment> getByUser(int userId);
	List<Comment> getByPost(int postId);

}
