package com.accenture.gallery.service;

import org.springframework.http.ResponseEntity;

public interface GalleryService {

	public ResponseEntity<Object> findByGallery(Long galleryId); 
	
}
