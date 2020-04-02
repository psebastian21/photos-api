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

import com.api.photos.model.Album;
import com.api.photos.repository.IAlbumRepository;

@RunWith(SpringRunner.class)
public class AlbumServiceTests {
	@Mock
	private IAlbumRepository albumRepository;
	
	@InjectMocks
	private AlbumService albumService;
	
	@Test
	public void testGetAll() {
		//Arrange
		Album a1 = new Album();
		a1.setId(1);
		Album a2 = new Album();
		a2.setId(2);
		List<Album> expectedResponse = Arrays.asList(a1, a2);
		Mockito.when(albumRepository.getAll()).thenReturn(expectedResponse);
		//Act
		List<Album> actualResponse = albumService.getAll();
		//Assert
		Assert.assertEquals(expectedResponse, actualResponse);
	}
	@Test
	public void testGet() {
		//Arrange
		Album a1 = new Album();
		a1.setId(1);
		Mockito.when(albumRepository.get(1)).thenReturn(a1);
		//Act
		Album actualResponse = albumService.get(1);
		//Assert
		Assert.assertEquals(a1, actualResponse);
	}
}
