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

@FeignClient(name="image-service")
public interface ImageClientRest {
	
	@PostMapping("/postImages/{id}")
	public ResponseEntity<Object> postImageFromGallery(@RequestBody Image img, @PathVariable Long id);
	
	@GetMapping("/images/{id}") //futuro cambio a Respo
	public List<Image> getImagesByGallery(@PathVariable Long id);
	
	@PutMapping("/changeName/{id_img}")
	public ResponseEntity<Object> putChangeName(@PathVariable ("id_img") Long id_img, @RequestBody Image img );
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable ("id") Long id);
}
