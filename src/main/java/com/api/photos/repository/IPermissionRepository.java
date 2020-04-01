package com.api.photos.repository;

public interface IPermissionRepository {
	
	void put(int userId, int albumId, boolean wPerm, boolean rPerm);

}
