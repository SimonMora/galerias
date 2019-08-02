package com.accenture.gallery.client;



import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.accenture.gallery.entities.Image;
import com.accenture.gallery.entities.RequestImage;

@FeignClient(name="image-service")
public interface ImageClientRest {
	
	@PostMapping("/postImages/{id}")
	public ResponseEntity<?> postImageFromGallery(@RequestBody RequestImage img, @PathVariable Long id);
	
	@GetMapping("/images/{id}")
	ResponseEntity<?> getImagesByGallery(@PathVariable Long id);
	
	@PutMapping("/changeName/{id_img}")
	public ResponseEntity<?> putChangeName(@PathVariable ("id_img") Long id_img, @RequestBody Image img );
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable ("id") Long id);
}
