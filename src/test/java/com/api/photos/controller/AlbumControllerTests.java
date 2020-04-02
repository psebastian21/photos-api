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

import com.api.photos.model.Album;
import com.api.photos.service.AlbumService;

@RunWith(SpringRunner.class)
public class AlbumControllerTests {
	
	@Mock
	private AlbumService albumService;
	
	@InjectMocks
	private AlbumController albumController;
	
	@Test
	public void testGetAll() {
		//Arrange
		Album a1 = new Album();
		a1.setId(1);
		Album a2 = new Album();
		a2.setId(2);
		List<Album> albums = Arrays.asList(a1, a2);
		ResponseEntity<List<Album>> expectedResponse = ResponseEntity.ok(albums);
		Mockito.when(albumService.getAll()).thenReturn(albums);
		//Act
		ResponseEntity<List<Album>> actualResponse = albumController.getAll();
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}
	@Test
	public void testGet() {
		//Arrange
		Album a1 = new Album();
		a1.setId(1);
		Mockito.when(albumService.get(1)).thenReturn(a1);
		ResponseEntity<Album> expectedResponse = ResponseEntity.ok(a1);
		//Act
		ResponseEntity<Album> actualResponse = albumController.get(1);
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}

}
