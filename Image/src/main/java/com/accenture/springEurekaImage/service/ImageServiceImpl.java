package com.accenture.springEurekaImage.service;

import java.util.List;

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
		
		logger.info("Accediendo a la base de datos de imagen");
		List<Image> images = repoImg.findAll();
	
		return images;
	}
	
	@Override
	@Transactional
	public List<Image> findByGalleryId(Long galleryId){
		
		logger.info("Accediendo a imagenes de una galeria");
		return repoImg.findByGalleryId(galleryId);
	}

	@Override
	@Transactional
	public Image save(Image image) {
		
		logger.info("Guardando imagen");
		return repoImg.save(image);
	}

	@Override
	@Transactional
	public Image findById(Long id) {
		
		logger.info("Encontrando imagen");
		Image image = repoImg.findById(id).orElse(null);
		return image;
	}

	@Override
	@Transactional
	public void delete(Image image) {
		
		logger.info("Eliminando imagene");
		repoImg.delete(image);
	}
}
