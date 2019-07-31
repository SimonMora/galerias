package com.accenture.springEurekaImage.controllers;


import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.accenture.springEurekaImage.service.ImageService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/")
public class HomeController {

	Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private Environment env;
	@Autowired
	private ImageService imageService;
	
	@PostMapping("/postImages/{id}")
	public ResponseEntity<Object> postImageFromGallery(@RequestBody Image img, @PathVariable Long id) throws JSONException{
		JSONObject imgJSON = new JSONObject();
		
		if (img!=null) {
			Image imagen = new Image(img.getName(), img.getUrl(), id);
			imageService.save(imagen);
			imgJSON.put("error", 0);
			imgJSON.put("result", "OK");
			logger.info("La imagen fue guardada exitosamente");
			return ResponseEntity.ok().body(imgJSON.toString());
		} else {
			imgJSON.put("error", 1);
			imgJSON.put("result", "La imagen no puede ser null");
			logger.info("La imagen no pudo ser guardada");
			return ResponseEntity.ok().body(imgJSON.toString());
		}
	}
	
	@GetMapping("/images/{id}") //Recibe la id de una galeria
	@ApiOperation(value = "Retrieve images from a specific gallery")
    @ApiResponses(value = @ApiResponse(code = 200, message = "Successful", response = Image.class))
	public ResponseEntity<Object> getImagesByGallery(@PathVariable Long id) throws JSONException {
		
		JSONObject response = new JSONObject();
		JSONArray arrayImage = new JSONArray();

		List<Image> images = imageService.findByGalleryId(id);
		if (images!=null) {
			for (Image image : images) {
				JSONObject toArray = new JSONObject();
				
				toArray.put("Url", image.getUrl());
				toArray.put("Gallery Id", image.getGalleryId());
				toArray.put("Id", image.getId());
				toArray.put("Name", image.getName());
				
				arrayImage.put(toArray);
			}

			response.put("error", 0);
			response.put("result", arrayImage);
		} else {
			response.put("error", 1);
			response.put("result", "La galeria no tiene imagenes");
		}
		
		return ResponseEntity.ok().body(response.toString());
	}
	
	@PutMapping("/changeName/{id_img}")
	@ApiOperation(value = "Change image name")
	public ResponseEntity<Object> putChangeName(@PathVariable ("id_img") Long id_img, @RequestParam String name) throws JSONException{
		
		JSONObject putJson = new JSONObject();
		Image image = imageService.findById(id_img);
		
		if (image!=null) {
			
			image.setName(name);
			imageService.save(image);
			putJson.put("error", 0);
			putJson.put("result", "Nombre cambiado");
			logger.info("Se pudo cambiar el nombre a la imagen");
			return ResponseEntity.ok().body(putJson.toString());
			
		} else {
			putJson.put("error", 1);
			putJson.put("result", "La imagen no existe");
			logger.error("No se pudo cambiar el nombre de la imagen");
			return ResponseEntity.ok().body(putJson.toString());
		}		
	}
	

	@DeleteMapping("delete/{id}")
	@ApiOperation(value = "Remove image from db")
	public ResponseEntity<Object> delete(@PathVariable ("id") Long id) throws JSONException{
		JSONObject imgJSON = new JSONObject();
		Image img = imageService.findById(id);  
		
		if (img!=null) {
			imageService.delete(img);
			imgJSON.put("error", 0);
			imgJSON.put("result", "Imagen eliminada");
			return ResponseEntity.ok().body(imgJSON.toString());
		} else {
			imgJSON.put("error", 1);
			imgJSON.put("result", "La imagen no existe");
			return ResponseEntity.ok().body(imgJSON.toString());
		}
	}
	
	@GetMapping("/echo") //Para ver si responde la API
	public String echo (String mensaje) {
		logger.info("Mensaje enviado exitosamente: " + mensaje);
		return mensaje;
	}

}