package com.api.photos.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.api.photos.exception.PermissionPuttingException;
import com.api.photos.model.Album;

@RunWith(SpringRunner.class)
public class PermissionRepositoryTests {
	
	@Mock
	private JdbcOperations jdbc;
	@Mock
	private IUserRepository userRepository;
	@Mock
	private IAlbumRepository albumRepository;
	@Mock 
	private PlatformTransactionManager platformTransactionManager;
	@InjectMocks
	private PermissionRepository permRepository;
	
	@Test
	public void testPutWhenTransactionSuccessfulThenCommit() {
		//Arrange
		Mockito.when(userRepository.get(Mockito.anyInt())).thenReturn(null);
		Album album = new Album();
		album.setUserId(1);
		Mockito.when(albumRepository.get(Mockito.anyInt())).thenReturn(album);
		TransactionStatus ts = Mockito.mock(TransactionStatus.class);
		Mockito.when(platformTransactionManager.getTransaction(Mockito.any())).thenReturn(ts);
		Mockito.when(jdbc.update(Mockito.anyString(), ArgumentMatchers.<Object>any())).thenReturn(1);
		Mockito.doNothing().when(platformTransactionManager).commit(ts);
		//Act
		permRepository.put(2, 1, true, true);
		//Assert
		Mockito.verify(platformTransactionManager).commit(ts);
	}
	@Test
	public void testPutWhenTransactionNotSuccessfulThenRollBack() {
		//Arrange
		Mockito.when(userRepository.get(Mockito.anyInt())).thenReturn(null);
		Album album = new Album();
		album.setUserId(1);
		Mockito.when(albumRepository.get(Mockito.anyInt())).thenReturn(album);
		TransactionStatus ts = Mockito.mock(TransactionStatus.class);
		Mockito.when(platformTransactionManager.getTransaction(Mockito.any())).thenReturn(ts);
		Mockito.when(jdbc.update(Mockito.anyString(), ArgumentMatchers.<Object>any()))
		.thenThrow(DataIntegrityViolationException.class);
		Mockito.doNothing().when(platformTransactionManager).rollback(ts);
		//Act
		try {
			permRepository.put(2, 1, true, true);
			Assert.fail("Shouldn't get here");
		}
		catch(DataIntegrityViolationException e) {
			//Assert
			Mockito.verify(platformTransactionManager).rollback(ts);
		}
	}
	@Test(expected = PermissionPuttingException.class)
	public void testPutWhenUserOwnsAlbumThenThrowPermissionPuttingException() {
		//Arrange
		Mockito.when(userRepository.get(Mockito.anyInt())).thenReturn(null);
		Album album = new Album();
		album.setUserId(1);
		Mockito.when(albumRepository.get(Mockito.anyInt())).thenReturn(album);
		//Act
		permRepository.put(1, 1, true, true);
	}
	@Test(expected = PermissionPuttingException.class)
	public void testPutWhenUserOrAlbumNotFoundThenThrowPermissionPuttingException() {
		//Arrange
		Mockito.when(userRepository.get(Mockito.anyInt())).thenThrow(HttpClientErrorException.NotFound.class);
		//Act
		permRepository.put(1, 1, true, true);
	}
}
