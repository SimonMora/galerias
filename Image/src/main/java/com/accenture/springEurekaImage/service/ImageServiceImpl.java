package com.accenture.springEurekaImage.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.springEurekaImage.entities.Image;
import com.accenture.springEurekaImage.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {
	
	Logger logger = LoggerFactory.getLogger(ImageService.class);
	
	@Autowired
	private ImageRepository repoImg;
	
	@Override
	@Transactional
	public List<Image> findAll() {
		
		List<Image> images = repoImg.findAll();
		if(images.size()!=0) {
			logger.info("Lista de imagenes");
		} else {
			logger.info("No hay imagenes en la base de datos");
		}
		
		return images;
	}
	
	@Override
	@Transactional
	public List<Image> findByGalleryId(Long galleryId){
		
		List<Image> images = repoImg.findByGalleryId(galleryId);;
		if(images.size()!=0) {
			logger.info("Imagenes encontradas por galeria");
		} else {
			logger.info("La galeria no tiene imagenes");
		}
		
		return images;
	}

	@Override
	@Transactional
	public Image save(Image image) {
		
		logger.info("Imagen guardada");
		return repoImg.save(image);
	}

	@Override
	@Transactional
	public Image findById(Long id) {
		
		Image image = repoImg.findById(id).orElse(null);
		if(image!=null) {
			logger.info("Imagen encontrada");

		} else {
			logger.info("Imagen no encontrada");

		}
		return image;
	}

	@Override
	@Transactional
	public void delete(Image image) {
		
		repoImg.delete(image);
		logger.info("Imagen eliminada");
	}
	
	

}
