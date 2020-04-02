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

import com.api.photos.model.Photo;
import com.api.photos.repository.IPhotoRepository;

@RunWith(SpringRunner.class)
public class PhotoServiceTests {
	
	@Mock
	private IPhotoRepository photoRepository;
	@InjectMocks
	private PhotoService photoService;

	@Test
	public void testGetAll() {
		//Arrange
		Photo p1 = new Photo();
		Photo p2 = new Photo();
		List<Photo> expectedResponse = Arrays.asList(p1, p2);
		Mockito.when(photoRepository.getAll()).thenReturn(expectedResponse);
		//Act
		List<Photo> actualResponse = photoService.getAll();
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}
	@Test
	public void testGet() {
		//Arrange
		Photo p1 = new Photo();
		Mockito.when(photoRepository.get(1)).thenReturn(p1);
		//Act
		Photo actualResponse = photoService.get(1);
		//Assert
		Assert.assertEquals(p1, actualResponse);
	}
	@Test
	public void testGetByUser() {
		//Arrange
		Photo p1 = new Photo();
		Photo p2 = new Photo();
		List<Photo> expectedResponse = Arrays.asList(p1, p2);
		Mockito.when(photoRepository.getByUser(1)).thenReturn(expectedResponse);
		//Act
		List<Photo> actualResponse = photoService.getByUser(1);
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}

}
