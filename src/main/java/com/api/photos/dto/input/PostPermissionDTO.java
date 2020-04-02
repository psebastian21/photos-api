package com.api.photos.dto.input;

import lombok.Data;

@Data
public class PostPermissionDTO {
	private int userId;
	private int albumId;
	private boolean writePerm;
	private boolean readPerm;
}
