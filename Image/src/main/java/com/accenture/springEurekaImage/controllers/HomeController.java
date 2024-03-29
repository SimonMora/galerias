package com.accenture.springEurekaImage.controllers;


import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.accenture.springEurekaImage.entities.Image;
import com.accenture.springEurekaImage.request.RequestImage;
import com.accenture.springEurekaImage.service.ImageService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/")
public class HomeController {

	private Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private ImageService imageService;
	@Value("${configuracion.texto}")
	private String text;
	@Autowired
	private Environment env;
	
	@PostMapping("/postImages/{id}")
	@ApiOperation(value = "Save an image to the db")
	public ResponseEntity<?> postImageFromGallery(@RequestBody RequestImage img, @PathVariable Long id){
		System.out.println(img.getName()+" "+img.getUrl());
		if (img!=null) {
			Image imagen = new Image(img.getName(), img.getUrl(), id);
			return ResponseEntity.ok().body(imageService.save(imagen));
		} else { 
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/images/{id}") //Recibe la id de una galeria
	@ApiOperation(value = "Retrieve images from a specific gallery")
    @ApiResponses(value = @ApiResponse(code = 200, message = "Successful", response = Image.class))
	public ResponseEntity<?> getImagesByGallery(@PathVariable Long id) {
		
		List<Image> images = imageService.findByGalleryId(id);
		if (images.size()!=0) {
			
			return ResponseEntity.ok().body(images);
		} else {
			
			return ResponseEntity.badRequest().body(images);
		}
		
	}
	
	@PutMapping("/changeName/{id_img}")
	@ApiOperation(value = "Change image name")
	public ResponseEntity<?> putChangeName(@PathVariable ("id_img") Long id_img, @RequestBody Image img){
		
		Image image = imageService.findById(id_img);
		
		if (image!=null) {
			
			image.setName(img.getName());			
			return ResponseEntity.ok().body(imageService.save(image));
			
		} else {
			
			return ResponseEntity.badRequest().body(null);
		}		
	}
	

	@DeleteMapping("delete/{id}")
	@ApiOperation(value = "Remove image from db")
	public ResponseEntity<?> delete(@PathVariable ("id") Long id){
		Image img = imageService.findById(id);  
		
		if (img!=null) {
			imageService.delete(img);
		
			return ResponseEntity.ok().body(null);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig() throws JSONException{
		JSONObject obj = new JSONObject();
		obj.put("configuracion", this.text);
		obj.put("puerto", env.getProperty("server.port"));
		if (env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")) {
			obj.put("nombre", env.getProperty("configuracion.autor.name"));
			obj.put("mail", env.getProperty("configuracion.autor.email"));
		} 
		return ResponseEntity.ok().body(obj.toString());
	}
	
	@GetMapping("/echo") //Para ver si responde la API
	public String echo (String mensaje) {
		logger.info("Mensaje enviado exitosamente: " + mensaje);
		return mensaje;
	}

}