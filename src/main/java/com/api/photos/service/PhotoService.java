package com.api.photos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.photos.model.Photo;
import com.api.photos.repository.IPhotoRepository;

@Service
public class PhotoService {
	
	@Autowired
	private IPhotoRepository photoRepository;
	
	public List<Photo> getAll(){
		return photoRepository.getAll();
	}
	public Photo get(int id){
		return photoRepository.get(id);
	}
	public List<Photo> getByUser(int userId){
		return photoRepository.getByUser(userId);
	}

}
