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
	public List<Image> verGallery(@PathVariable("id") Long id){
		return galleryFeign.findByGallery(id);
	}
	
	@PostMapping("/{id}/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> postImage(@PathVariable ("id") Long galleryId, @RequestBody Image img){
		
		return galleryFeign.saveImage(img, galleryId);
	}
	
	@DeleteMapping("/image/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteImage(@PathVariable("id")Long imgId){
		return galleryFeign.deleteImage(imgId);
	}
	
	@PutMapping("/image/change/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> reNameImage(@PathVariable("id") Long imgId,@RequestBody Image img){
		return galleryFeign.changeImageName(imgId, img);
	}
	
//	@CrossOrigin(origins = "http://localhost:4200")
//	@RequestMapping("/{id}")
//	public ResponseEntity<Object> getGallery(@PathVariable final Long id) {
//		
//		
//		Gallery gallery = gallRepo.findById(id).orElse(null);
//		
//		String images;	
//		images = restTemplate.getForObject("http://image-service/images/", String.class);
//				
//			
//		return images;
//	}
	
	@RequestMapping("/admin")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String homeAdmin() {
		return "This is the admin area of Gallery service running at port: " + env.getProperty("local.server.port");
	}

}
