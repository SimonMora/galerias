package com.accenture.gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accenture.gallery.client.ImageClientRest;
import com.accenture.gallery.entities.Image;

@Service
public class GalleryServiceFeignImpl implements GalleryService {

	@Autowired
	private ImageClientRest clientImage; 
	
	@Override
	public List<Image> findByGallery(Long galleryId) {
		
		return clientImage.getImagesByGallery(galleryId);
	}

	
	
}
