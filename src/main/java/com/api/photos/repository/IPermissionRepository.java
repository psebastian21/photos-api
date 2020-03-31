package com.api.photos.repository;

public interface IPermissionRepository {
	
	void putPermission(int userId, int albumId, boolean wPerm, boolean rPerm);

}
