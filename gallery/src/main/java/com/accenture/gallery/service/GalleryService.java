package com.accenture.gallery.service;

import java.util.List;

import org.springframework.http.ResponseEntity;


import com.accenture.gallery.entities.Image;
import com.accenture.gallery.entities.RequestImage;

public interface GalleryService {

	public ResponseEntity<?> findByGallery(Long galleryId); 
	public ResponseEntity<?> saveImage(RequestImage img, Long galleryId);
	public ResponseEntity<?> deleteImage(Long imgId); 
	public ResponseEntity<?> changeImageName(Long imgId, Image img);
}
