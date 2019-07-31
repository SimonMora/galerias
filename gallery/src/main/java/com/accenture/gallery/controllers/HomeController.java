package com.accenture.gallery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.accenture.gallery.entities.Gallery;
import com.accenture.gallery.repository.GalleryRepository;

@RestController
@RequestMapping("/")
public class HomeController {
	
	
	@Autowired
	private Environment env;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String home() {
		
		return "Hello from Gallery Service running at port: " + env.getProperty("local.server.port");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> verGallery(@PathVariable("id") Long id){
		
		
		return null;
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
	public String homeAdmin() {
		return "This is the admin area of Gallery service running at port: " + env.getProperty("local.server.port");
	}

}
