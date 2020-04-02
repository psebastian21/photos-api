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

import com.api.photos.model.Photo;
import com.api.photos.service.PhotoService;

@RunWith(SpringRunner.class)
public class PhotoControllerTests {
	
	@Mock
	private PhotoService photoService;
	@InjectMocks
	private PhotoController photoController;

	@Test
	public void testGetAll() {
		//Arrange
		Photo p1 = new Photo();
		Photo p2 = new Photo();
		List<Photo> photos = Arrays.asList(p1, p2);
		ResponseEntity<List<Photo>> expectedResponse = ResponseEntity.ok(photos);
		Mockito.when(photoService.getAll()).thenReturn(photos);
		//Act
		ResponseEntity<List<Photo>> actualResponse = photoController.getAll();
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}
	@Test
	public void testGet() {
		//Arrange
		Photo p1 = new Photo();
		Mockito.when(photoService.get(1)).thenReturn(p1);
		ResponseEntity<Photo> expectedResponse = ResponseEntity.ok(p1);
		//Act
		ResponseEntity<Photo> actualResponse = photoController.get(1);
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}
	@Test
	public void testGetByUser() {
		//Arrange
		Photo p1 = new Photo();
		Photo p2 = new Photo();
		List<Photo> photos = Arrays.asList(p1, p2);
		Mockito.when(photoService.getByUser(1)).thenReturn(photos);
		ResponseEntity<List<Photo>> expectedResponse = ResponseEntity.ok(photos);
		//Act
		ResponseEntity<List<Photo>> actualResponse = photoController.getByUser(1);
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}
}
