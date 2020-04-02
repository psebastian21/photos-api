package com.api.photos.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestOperations;

import com.api.photos.exception.NotFoundException;
import com.api.photos.model.Comment;
import com.api.photos.model.Post;
@RunWith(SpringRunner.class)
public class CommentRepositoryTests {
	
	@Mock
	private RestOperations rest;
	@InjectMocks
	private CommentRepository commentRepository;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAll() {
		//Arrange
		Comment c1 = new Comment();
		c1.setId(1);
		Comment c2 = new Comment();
		c2.setId(2);
		Comment[] comments = {c1, c2};
		Mockito.when(rest.getForEntity(Mockito.anyString(), Mockito.any(Class.class)))
		.thenReturn(ResponseEntity.ok(comments));
		List<Comment> expected = Arrays.asList(comments);
		//Act
		List<Comment> actual = commentRepository.getAll();
		Assert.assertEquals(expected, actual);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testGet() {
		//Arrange
		Comment c1 = new Comment();
		c1.setId(1);
		Mockito.when(rest.getForEntity(Mockito.anyString(), Mockito.any(Class.class)))
		.thenReturn(ResponseEntity.ok(c1));
		//Act
		Comment actual = commentRepository.get(1);
		Assert.assertEquals(c1, actual);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testGetByName() {
		//Arrange
		Comment c1 = new Comment();
		c1.setName("one");
		Comment c2 = new Comment();
		c2.setName("two");
		Comment c3 = new Comment();
		c3.setName("two");
		Comment[] comments = {c1, c2, c3};
		Mockito.when(rest.getForEntity(Mockito.anyString(), Mockito.any(Class.class)))
		.thenReturn(ResponseEntity.ok(comments));
		List<Comment> expected = Arrays.asList(c2, c3);
		//Act
		List<Comment> actual = commentRepository.getByName("two");
		Assert.assertEquals(expected, actual);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testGetByUser() {
		//Arrange
		Post p1 = new Post();
		Post p2 = new Post();
		Post[] posts = {p1, p2};
		Comment c1 = new Comment();
		c1.setName("one");
		Comment c2 = new Comment();
		c2.setName("two");
		Comment c3 = new Comment();
		c3.setName("three");
		Mockito.when(rest.getForEntity(Mockito.anyString(), Mockito.any(Class.class), ArgumentMatchers.<Object>any()))
		.thenReturn(ResponseEntity.ok(posts)).thenReturn(ResponseEntity.ok(new Comment[] {c1}))
		.thenReturn(ResponseEntity.ok(new Comment[] {c2, c3}));
		List<Comment> expected = Arrays.asList(c1, c2, c3);
		//Act
		List<Comment> actual = commentRepository.getByUser(1);
		Assert.assertEquals(expected, actual);
	}
	@SuppressWarnings("unchecked")
	@Test(expected = NotFoundException.class)
	public void testGetByUserWhenNoPostsThenThrowNotFoundException() {
		//Arrange
		Mockito.when(rest.getForEntity(Mockito.anyString(), Mockito.any(Class.class), ArgumentMatchers.<Object>any()))
		.thenReturn(ResponseEntity.ok(new Post[0]));
		//Act
		commentRepository.getByUser(1);
	}

}
