package com.api.photos.repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestOperations;

import com.api.photos.exception.NotFoundException;
import com.api.photos.model.Comment;
import com.api.photos.model.Post;

@Repository
public class CommentRepository implements ICommentRepository {
	
	private static final String URL_LIST_COMMENTS = "https://jsonplaceholder.typicode.com/comments";
	private static final String URL_POSTS_BY_USER = "https://jsonplaceholder.typicode.com/users/{userId}/posts";
	private static final String URL_COMMENTS_BY_POST = "https://jsonplaceholder.typicode.com/posts/{postId}/comments";
	
	@Autowired
	private RestOperations rest;

	@Override
	public List<Comment> getAll() {
		ResponseEntity<Comment[]> response = rest.getForEntity(URL_LIST_COMMENTS, Comment[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public Comment get(int id) {
		ResponseEntity<Comment> response = rest.getForEntity(URL_LIST_COMMENTS + "/" + id, Comment.class);
		return response.getBody();
	}

	@Override
	public List<Comment> getByName(String name) {
		return this.getAll()
				.stream()
				.filter(comment -> comment.getName().equals(name))
				.collect(Collectors.toList());
	}

	@Override
	public List<Comment> getByUser(int userId) {
		List<Comment> comments = new LinkedList<>();
		this.getPostsByUser(userId)
				.stream()
				.map(post -> this.getByPost(post.getId()))
				.forEach(comments::addAll);
		return comments;
	}
	
	@Override
	public List<Comment> getByPost(int postId){
		ResponseEntity<Comment[]> comments = rest.getForEntity(URL_COMMENTS_BY_POST, Comment[].class, postId);
		return Arrays.asList(comments.getBody());
	}
	
	private List<Post> getPostsByUser(int userId) throws NotFoundException{
		ResponseEntity<Post[]> posts = rest.getForEntity(URL_POSTS_BY_USER, Post[].class, userId);
		if(posts.getBody().length == 0) {
			throw new NotFoundException();
		}
		return Arrays.asList(posts.getBody());
	}

}
