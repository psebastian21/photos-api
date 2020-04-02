package com.api.photos.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.client.HttpClientErrorException;

import com.api.photos.exception.PermissionPuttingException;
import com.api.photos.model.Album;

@Repository
public class PermissionRepository implements IPermissionRepository {
	
	private static final String SQL_UPSERT_PERMISSION = 
			"INSERT INTO PHOTOS.PERMISSION(USER_ID, ALBUM_ID, READ_PERMISSION, WRITE_PERMISSION)\r\n" + 
			"VALUES(?, ?, ?, ?)\r\n" + 
			"ON CONFLICT ON CONSTRAINT PK_PERM DO UPDATE\r\n" + 
			"SET WRITE_PERMISSION = EXCLUDED.WRITE_PERMISSION, READ_PERMISSION = EXCLUDED.READ_PERMISSION";
	
	@Autowired
	private JdbcOperations jdbc;
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IAlbumRepository albumRepository;
	@Autowired 
	private PlatformTransactionManager platformTransactionManager;

	@Override
	public void put(int userId, int albumId, boolean wPerm, boolean rPerm) throws PermissionPuttingException{
		this.verifyUserAndAlbum(userId, albumId);
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			jdbc.update(SQL_UPSERT_PERMISSION, userId, albumId, rPerm, wPerm);
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
			Album album = albumRepository.get(albumId);
			if (album.getUserId() == userId) {
				throw new PermissionPuttingException("Specified user already owns specified album");
			}
		}
		catch(HttpClientErrorException.NotFound e) {
			throw new PermissionPuttingException("User or album nonexistent, or service down", e);
		}
		
	}

}
