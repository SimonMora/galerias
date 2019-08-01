package com.accenture.gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accenture.gallery.entities.Image;

@Service("restTemplateImpl")
public class GalleryServiceRestTemplate implements GalleryService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Image> findByGallery(Long galleryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> saveImage(Image img, Long galleryId) {
		Object body = img;
		ResponseEntity<Image> image = restTemplate.postForEntity("http://image-service/postImages/"+galleryId, body, Image.class);		
		if(image!=null) {
			return ResponseEntity.ok().body(image);
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@Override
	public ResponseEntity<?> deleteImage(Long imgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> changeImageName(Long imgId, Image img) {
		// TODO Auto-generated method stub
		return null;
	}

}
