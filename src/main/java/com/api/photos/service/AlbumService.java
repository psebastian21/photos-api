package com.api.photos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.photos.model.Album;
import com.api.photos.repository.IAlbumRepository;

@Service
public class AlbumService {
	
	@Autowired
	private IAlbumRepository albumRepository;
	
	public List<Album> getAll(){
		return albumRepository.getAll();
	}
	public Album get(int id){
		return albumRepository.get(id);
	}

}
