package com.accenture.gallery.service;

import java.util.List;

import com.accenture.gallery.entities.Image;

public interface GalleryService {

	public List<Image> findByGallery(Long galleryId); 
	
}
