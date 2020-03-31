package com.api.photos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.photos.repository.IPermissionRepository;

@Service
public class PermissionService {
	
	@Autowired
	private IPermissionRepository permissionRepository;
	
	public void putPermission(int userId, int albumId, boolean wPerm, boolean rPerm) {
		this.permissionRepository.putPermission(userId, albumId, wPerm, rPerm);
	}

}
