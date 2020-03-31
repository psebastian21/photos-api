package com.api.photos.dto.input;

public class PostPermissionDTO {
	
	private int userId;
	private int albumId;
	private boolean writePerm;
	private boolean readPerm;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	public boolean getWritePerm() {
		return writePerm;
	}
	public void setWritePerm(boolean writePerm) {
		this.writePerm = writePerm;
	}
	public boolean getReadPerm() {
		return readPerm;
	}
	public void setReadPerm(boolean readPerm) {
		this.readPerm = readPerm;
	}

}
