package com.api.photos.repository;

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
import org.springframework.web.client.RestOperations;

import com.api.photos.exception.NotFoundException;
import com.api.photos.model.Album;

@RunWith(SpringRunner.class)
public class AlbumRepositoryTests {
	
	@Mock
	private RestOperations rest;
	@InjectMocks
	private AlbumRepository albumRepository;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAll() {
		//Arrange
		Album a1 = new Album();
		Album a2 = new Album();
		Album[] albums = {a1, a2};
		ResponseEntity<Album[]> restResponse = ResponseEntity.ok(albums);
		Mockito.when(rest.getForEntity(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(restResponse);
		List<Album> expectedResponse = Arrays.asList(albums);
		//Act
		List<Album> actualResponse = albumRepository.getAll();
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testGet() {
		//Arrange
		Album a1 = new Album();
		a1.setId(1);
		ResponseEntity<Album> restResponse = ResponseEntity.ok(a1);
		Mockito.when(rest.getForEntity(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(restResponse);
		//Act
		Album actualResponse = albumRepository.get(1);
		//Assert
		Assert.assertEquals(a1, actualResponse);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testGetByUserIfFoundThenReturn() {
		//Arrange
		Album a1 = new Album();
		Album a2 = new Album();
		Album[] albums = {a1, a2};
		ResponseEntity<Album[]> restResponse = ResponseEntity.ok(albums);
		Mockito.when(rest.getForEntity(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(restResponse);
		List<Album> expectedResponse = Arrays.asList(albums);
		//Act
		List<Album> actualResponse = albumRepository.getByUser(1);
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}
	@SuppressWarnings("unchecked")
	@Test(expected = NotFoundException.class)
	public void testGetByUserIfNotFoundThenThrowNotFoundException() {
		//Arrange
		Mockito.when(rest.getForEntity(Mockito.anyString(), Mockito.any(Class.class)))
		.thenReturn(ResponseEntity.ok(new Album[0]));
		//Act
		albumRepository.getByUser(1);
	}

}
