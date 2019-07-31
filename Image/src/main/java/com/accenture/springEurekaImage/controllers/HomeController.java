package com.accenture.springEurekaImage.controllers;


import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.springEurekaImage.entities.Image;
import com.accenture.springEurekaImage.repository.ImageRepository;
import com.accenture.springEurekaImage.service.ImageService;

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
			Image imagen = new Image();
			imagen.setName(img.getName());
			imagen.setUrl(img.getUrl());
			imagen.setGalleryId(id);
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
	
	@GetMapping("/images/{id}") 
	public List<Image> getImagesByGallery(@PathVariable Long id) {
		
		List<Image> images = imageService.findByGalleryId(id);
		return images;
	}	
	
	@PutMapping("/changeName/{id_img}") //
	public ResponseEntity<Object> putChangeName(@PathVariable ("id_img") Long id_img, @RequestBody Image img ) throws JSONException{
		
		JSONObject putJson = new JSONObject();
		Image image = imageService.findById(id_img);
		
		if (image!=null) {
			
			if(image.getGalleryId()!=null) {
				image.setName(img.getName());
				imageService.save(image);
				putJson.put("error", 0);
				putJson.put("result", "Nombre cambiado");
				logger.info("Se pudo cambiar el nombre a la imagen");
				return ResponseEntity.ok().body(putJson.toString());
			} else {
				putJson.put("error", 2);
				putJson.put("result", "La imagen no existe en la galeria");
				logger.error("No se pudo cambiar el nombre de la imagen");
				return ResponseEntity.ok().body(putJson.toString());
			}
			
		} else {
			putJson.put("error", 1);
			putJson.put("result", "La imagen no existe");
			logger.error("No se pudo cambiar el nombre de la imagen");
			return ResponseEntity.ok().body(putJson.toString());
		}		
	}
	

	@DeleteMapping("delete/{id}") //Api de delete
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
//	@CrossOrigin(origins = "http://localhost:4200")
//	@GetMapping("/images")
//	public ResponseEntity<Object> getImages() throws JSONException {
//		List<Image> images = imageService.findAll();
//		JSONObject obj = new JSONObject();
//		JSONArray array = new JSONArray();
//		JSONObject json = new JSONObject();
//		for(Image img: images) {
//			obj.put("title", img.getTitle());
//			obj.put("url", img.getUrl());
//			obj.put("id", img.getId());
//			obj.put("gallery_id", img.getGallery_id());
//			
//			array.put(obj);
//		}
//		json.put("error",0);
//		json.put("results", array);
//		
//				return ResponseEntity.ok().body(json.toString());
//	}
	
//	@RequestMapping("/imagenes")
//	public List<Images> getImagenes(){
//		List<Images> images = Arrays.asList(
//				new Images(1L,"This is Seiya","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSivYtxeq7nc1YXwqglLHxSIz8wC8qWm4yUk23PhWUFlIwl4PaBCw"),
//				new Images(2L,"Shiryu: Caballero del Dragón","http://3.bp.blogspot.com/-kqyGNo6HBYY/UeCHb8QREvI/AAAAAAAALIQ/Gutp3BhWAvM/s400/XF32.jpg"),
//				new Images(3L,"Andromeda y sus cadenas","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSg7owBJ-yMubI6GXnC-UVZLGIUdKtaBptsHo0RTmQ6rKJV67iC"));
//		return images;
//	}
	
//	@PostMapping("/guardar")
//	public ResponseEntity<Object> saveImage(@RequestBody Image imagen) throws JSONException{
//
//		Image unaimagen = new Image();
//		JSONObject res= new JSONObject();
//		
//			if(imagen!=null) {
//				
//				unaimagen.setTitle(imagen.getTitle());
//				unaimagen.setUrl(imagen.getUrl());
//				unaimagen.setGallery_id(imagen.getGallery_id());
//				imageService.save(unaimagen);
//				System.out.println(unaimagen.getTitle());
//				
//				res.put("error", 0);
//				res.put("result", "pudimos salvar tu imagen");
//					
//				
//			}else {
//				res.put("error", 1);
//				res.put("result", "no pudimos salvar tu imagen");	
//			}
//		return ResponseEntity.ok().body(res.toString());
//	}
//}
