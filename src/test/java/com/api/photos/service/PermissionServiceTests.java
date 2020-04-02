package com.api.photos.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.photos.repository.IPermissionRepository;

@RunWith(SpringRunner.class)
public class PermissionServiceTests {
	
	@Mock
	private IPermissionRepository permissionRepository;
	
	@InjectMocks
	private PermissionService permissionService;
	
	@Test
	public void testPut() {
		//Arrange
		Mockito.doNothing().when(permissionRepository).put(Mockito.anyInt(), Mockito.anyInt(), 
				Mockito.anyBoolean(), Mockito.anyBoolean());
		//Act
		permissionService.put(1, 1, true, true);
		//Assert
		Mockito.verify(permissionRepository).put(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyBoolean(), 
				Mockito.anyBoolean());
	}

}
