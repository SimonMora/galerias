package com.accenture.springEurekaImage.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Image {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Long galleryId;
	private String url;
	
	public Image() {
		
	}
	public Image(String name, String url, Long galleryId) {
		super();
		this.name = name;
		this.url = url;
		this.galleryId = galleryId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getGalleryId() {
		return galleryId;
	}
	public void setGalleryId(Long gallery_id) {
		this.galleryId = gallery_id;
	}
	

}
