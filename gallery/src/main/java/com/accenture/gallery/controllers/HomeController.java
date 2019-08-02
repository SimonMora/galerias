package com.accenture.gallery.controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.gallery.entities.Gallery;
import com.accenture.gallery.entities.Image;
import com.accenture.gallery.entities.RequestImage;
import com.accenture.gallery.service.GalleryService;

@RestController
@RequestMapping("/")
public class HomeController {
	
	
	@Autowired
	private Environment env;
	@Autowired
	@Qualifier("FeignImpl")
	private GalleryService galleryFeign;
	
	@RequestMapping(value = "/")
	public String home() {
		
		return "Hello from Gallery Service running at port: " + env.getProperty("local.server.port");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> verGallery(@PathVariable("id") String id){
		Long longId = Long.parseLong(id);
		return galleryFeign.findByGallery(longId);
	}
	
	@PostMapping("/{id}/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> postImage(@RequestBody RequestImage img, @PathVariable ("id") String galleryId){
		Long longId = Long.parseLong(galleryId);
		return galleryFeign.saveImage(img, longId);
	}
	
	@DeleteMapping("/image/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteImage(@PathVariable("id") String imgId){
		Long longId = Long.parseLong(imgId);
		return galleryFeign.deleteImage(longId);
	}
	
	@PutMapping("/image/change/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> reNameImage(@PathVariable("id") String imgId,@RequestBody Image img){
		Long longId = Long.parseLong(imgId);
		return galleryFeign.changeImageName(longId, img);
	}
		
	@RequestMapping("/admin")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String homeAdmin() {
		return "This is the admin area of Gallery service running at port: " + env.getProperty("local.server.port");
	}

}
