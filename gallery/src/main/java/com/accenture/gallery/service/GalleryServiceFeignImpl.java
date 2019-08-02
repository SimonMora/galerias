package com.accenture.gallery.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accenture.gallery.client.ImageClientRest;
import com.accenture.gallery.entities.Image;
import com.accenture.gallery.entities.RequestImage;

@Service("FeignImpl")
public class GalleryServiceFeignImpl implements GalleryService {

	@Autowired
	private ImageClientRest clientImage; 
	private Logger logger = LoggerFactory.getLogger(GalleryServiceFeignImpl.class);
	
	
	@Override
	public ResponseEntity<?> findByGallery(Long galleryId) {
		
		return clientImage.getImagesByGallery(galleryId);
	}

	@Override
	public ResponseEntity<?> saveImage(RequestImage img, Long galleryId) {
		Object obj = new Object();
		obj = clientImage.postImageFromGallery(img, galleryId);
		if(obj!=null) {
		logger.info("Se ha comunicado con la base de datos, retornando una repuesta.");
			return ResponseEntity.ok().body(obj);
		}else {
			logger.info("No se ha obtenido repuesta de la base de datos.");
			return ResponseEntity.badRequest().body(null);
		}	
	}

	@Override
	public ResponseEntity<?> deleteImage(Long imgId) {
		Object obj = new Object();
		obj = clientImage.delete(imgId);
			if(obj!=null) {
				logger.info("Se ha comunicado con la base de datos, retornando una repuesta.");
				return ResponseEntity.ok().body(obj);
			}else {
				logger.info("No se ha obtenido repuesta de la base de datos.");
				return ResponseEntity.badRequest().body(null);
			}
		
	}

	@Override
	public ResponseEntity<?> changeImageName(Long imgId, Image img) {
		Object obj = new Object();
		obj = clientImage.putChangeName(imgId, img);
			if(obj!=null) {
				logger.info("Se ha comunicado con la base de datos, retornando una repuesta.");
				return ResponseEntity.ok().body(obj);
			}else {
				logger.info("No se ha obtenido repuesta de la base de datos.");
				return ResponseEntity.badRequest().body(null);
			}
	}	
}
