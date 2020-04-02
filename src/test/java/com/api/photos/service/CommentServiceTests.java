package com.api.photos.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.photos.model.Comment;
import com.api.photos.repository.ICommentRepository;

@RunWith(SpringRunner.class)
public class CommentServiceTests {
	@Mock
	private ICommentRepository commentRepository;
	
	@InjectMocks
	private CommentService commentService;
	
	@Test
	public void testGetAll() {
		//Arrange
		Comment c1 = new Comment();
		Comment c2 = new Comment();
		List<Comment> expectedResponse = Arrays.asList(c1, c2);
		Mockito.when(commentRepository.getAll()).thenReturn(expectedResponse);
		//Act
		List<Comment> actualResponse = commentService.getAll();
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}
	@Test
	public void testGet() {
		//Arrange
		Comment c1 = new Comment();
		Mockito.when(commentRepository.get(1)).thenReturn(c1);
		//Act
		Comment actualResponse = commentService.get(1);
		//Assert
		Assert.assertEquals(c1, actualResponse);
	}
	@Test
	public void testGetByName() {
		//Arrange
		Comment c1 = new Comment();
		Comment c2 = new Comment();
		List<Comment> expectedResponse = Arrays.asList(c1, c2);
		Mockito.when(commentRepository.getByName(Mockito.anyString())).thenReturn(expectedResponse);
		//Act
		List<Comment> actualResponse = commentService.getByName("foo");
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}
	@Test
	public void testGetByUser() {
		//Arrange
		Comment c1 = new Comment();
		Comment c2 = new Comment();
		List<Comment> expectedResponse = Arrays.asList(c1, c2);
		Mockito.when(commentRepository.getByUser(1)).thenReturn(expectedResponse);
		//Act
		List<Comment> actualResponse = commentService.getByUser(1);
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}

}
