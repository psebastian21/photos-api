package com.api.photos.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.api.photos.exception.PermissionPuttingException;

@Repository
public class PermissionRepository implements IPermissionRepository {
	
	private static final String SQL_VERIFY_PERMISSION_BY_ALBUM_AND_USER_IDS = "SELECT\r\n" + 
			"	COUNT(*) AS PRM_COUNT\r\n" + 
			"FROM\r\n" + 
			"	PHOTOS.PERMISSION\r\n" + 
			"WHERE\r\n" + 
			"	USER_ID = ? AND ALBUM_ID = ?";
	private static final String SQL_UPDATE_PERMISSION = "UPDATE\r\n" + 
			"	PHOTOS.PERMISSION\r\n" + 
			"SET\r\n" + 
			"	READ_PERMISSION = ?,\r\n" + 
			"	WRITE_PERMISSION = ?\r\n" + 
			"WHERE\r\n" + 
			"	USER_ID = ? AND ALBUM_ID = ?";
	private static final String SQL_INSERT_PERMISSION = "INSERT INTO PHOTOS.PERMISSION\r\n" + 
			"VALUES(?, ?, ?, ?)";
	
	@Autowired
	private JdbcOperations jdbc;
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IAlbumRepository albumRepository;
	@Autowired 
	private PlatformTransactionManager platformTransactionManager;

	@Override
	public void putPermission(int userId, int albumId, boolean wPerm, boolean rPerm) throws PermissionPuttingException{
		this.verifyUserAndAlbum(userId, albumId);
		DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus status = platformTransactionManager.getTransaction(defaultTransactionDefinition);
		Optional<Integer> permFound = Optional.ofNullable
				(jdbc.queryForObject(SQL_VERIFY_PERMISSION_BY_ALBUM_AND_USER_IDS, new Object[]{userId, albumId}, Integer.class));
		try {
			if(permFound.orElse(0) == 0) {
				jdbc.update(SQL_INSERT_PERMISSION, userId, albumId, rPerm, wPerm);
			}
			else {
				jdbc.update(SQL_UPDATE_PERMISSION, rPerm, wPerm, userId, albumId);
			}
			platformTransactionManager.commit(status);
		}
		catch(Exception e) {
			platformTransactionManager.rollback(status);
			throw e;
		}

	}

	private void verifyUserAndAlbum(int userId, int albumId) throws PermissionPuttingException{
		try {
			userRepository.get(userId);
			albumRepository.get(albumId);
		}
		catch(Exception e) {
			throw new PermissionPuttingException("User or album nonexistent, or service down", e);
		}
		
	}

}
