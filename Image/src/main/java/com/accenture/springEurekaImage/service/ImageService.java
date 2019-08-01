package com.accenture.springEurekaImage.service;

import java.util.List;

import com.accenture.springEurekaImage.entities.Image;

public interface ImageService {
	
	public List<Image> findAll();
	public List<Image> findByGalleryId(Long id);
	public Image findById(Long id);
	public Image save(Image image);
	public void delete(Image image);
}
