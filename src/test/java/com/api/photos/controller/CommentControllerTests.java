package com.api.photos.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.photos.model.Comment;
import com.api.photos.service.CommentService;

@RunWith(SpringRunner.class)
public class CommentControllerTests {
	
	@Mock
	private CommentService commentService;
	
	@InjectMocks
	private CommentController commentController;
	
	@Test
	public void testGetAll() {
		//Arrange
		Comment c1 = new Comment();
		Comment c2 = new Comment();
		List<Comment> comments = Arrays.asList(c1, c2);
		ResponseEntity<List<Comment>> expectedResponse = ResponseEntity.ok(comments);
		Mockito.when(commentService.getAll()).thenReturn(comments);
		//Act
		ResponseEntity<List<Comment>> actualResponse = commentController.getAll();
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}
	@Test
	public void testGet() {
		//Arrange
		Comment c1 = new Comment();
		Mockito.when(commentService.get(1)).thenReturn(c1);
		ResponseEntity<Comment> expectedResponse = ResponseEntity.ok(c1);
		//Act
		ResponseEntity<Comment> actualResponse = commentController.get(1);
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}
	@Test
	public void testGetByName() {
		//Arrange
		Comment c1 = new Comment();
		Comment c2 = new Comment();
		List<Comment> comments = Arrays.asList(c1, c2);
		Mockito.when(commentService.getByName(Mockito.anyString())).thenReturn(comments);
		ResponseEntity<List<Comment>> expectedResponse = ResponseEntity.ok(comments);
		//Act
		ResponseEntity<List<Comment>> actualResponse = commentController.getByName("foo");
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}
	@Test
	public void testGetByUser() {
		//Arrange
		Comment c1 = new Comment();
		Comment c2 = new Comment();
		List<Comment> comments = Arrays.asList(c1, c2);
		Mockito.when(commentService.getByUser(1)).thenReturn(comments);
		ResponseEntity<List<Comment>> expectedResponse = ResponseEntity.ok(comments);
		//Act
		ResponseEntity<List<Comment>> actualResponse = commentController.getByUser(1);
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}

}
