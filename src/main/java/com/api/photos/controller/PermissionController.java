package com.api.photos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.photos.dto.input.PostPermissionDTO;
import com.api.photos.service.PermissionService;

@RestController
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;
	
	@PostMapping("/permission")
	public ResponseEntity<ResponseMessage> put(@RequestBody PostPermissionDTO input){
		this.permissionService.put(input.getUserId(), input.getAlbumId(), input.getWritePerm(), 
				input.getReadPerm());
		ResponseMessage message = new ResponseMessage("Confirmed");
		return ResponseEntity.ok(message);
	}

}
