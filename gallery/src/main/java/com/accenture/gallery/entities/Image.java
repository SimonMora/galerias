package com.accenture.gallery.entities;

public class Image {
	private Long id;
	private String name;
	private Long galleryId;
	private String url;
	
	
	public Image() {
		
	}
	public Image(Long id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
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
