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

		logger.info("Lista de imagenes");
		return repoImg.findAll();
	}
	
	@Override
	public List<Image> findByGalleryId(Long galleryId){
		
		logger.info("Imagen encontrada por galeria");
		return repoImg.findByGalleryId(galleryId);
	}

	@Override
	@Transactional
	public Image save(Image image) {

		logger.info("Imagen guardada");
		return repoImg.save(image);
	}

	@Override
	public Image findById(Long id) {
		
		logger.info("Imagen encontrada");
		return repoImg.findById(id).orElse(null);
	}

	@Override
	public void delete(Image image) {
		
		repoImg.delete(image);
		logger.info("Imagen eliminada");
	}
	
	

}
