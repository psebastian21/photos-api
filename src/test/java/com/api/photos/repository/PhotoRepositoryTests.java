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

import com.api.photos.model.Album;
import com.api.photos.model.Photo;


@RunWith(SpringRunner.class)
public class PhotoRepositoryTests {
	
	@Mock
	private RestOperations rest;
	@Mock
	private IAlbumRepository albumRepository;
	@InjectMocks
	private PhotoRepository photoRepository;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAll() {
		//Arrange
		Photo p1 = new Photo();
		Photo p2 = new Photo();
		Mockito.when(rest.getForEntity(Mockito.anyString(), Mockito.any(Class.class)))
		.thenReturn(ResponseEntity.ok(new Photo[] {p1, p2}));
		List<Photo> expected = Arrays.asList(p1, p2);
		//Act
		List<Photo> actual = photoRepository.getAll();
		//Assert
		Assert.assertEquals(expected, actual);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testGet() {
		//Arrange
		Photo p1 = new Photo();
		Mockito.when(rest.getForEntity(Mockito.anyString(), Mockito.any(Class.class)))
		.thenReturn(ResponseEntity.ok(p1));
		//Act
		Photo actual = photoRepository.get(1);
		//Assert
		Assert.assertEquals(p1, actual);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void getByUser() {
		//Arrange
		Album a1 = new Album();
		a1.setId(1);
		Album a2 = new Album();
		a2.setId(2);
		List<Album> albums = Arrays.asList(a1, a2);
		Mockito.when(albumRepository.getByUser(Mockito.anyInt())).thenReturn(albums);
		Photo p1 = new Photo();
		p1.setId(1);
		Photo p2 = new Photo();
		p2.setId(2);
		Photo p3 = new Photo();
		p3.setId(3);
		Mockito.when(rest.getForEntity(Mockito.anyString(), Mockito.any(Class.class)))
		.thenReturn(ResponseEntity.ok(new Photo[] {p1}), ResponseEntity.ok(new Photo[] {p2, p3}));
		List<Photo> expected = Arrays.asList(p1, p2, p3);
		//Act
		List<Photo> actual = photoRepository.getByUser(1);
		//Assert
		Assert.assertEquals(expected, actual);
	}
}
