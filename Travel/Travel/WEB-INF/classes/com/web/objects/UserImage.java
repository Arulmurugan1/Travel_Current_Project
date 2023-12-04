package com.web.objects;

import java.time.LocalDate;

public class UserImage 
{
	String user_id, image_name, image_path, uploaded_by ,type;
	int size ;
	LocalDate uploaded_time ;

	

	public UserImage(String user_id, String image_name, String image_path, String uploaded_by, String type, int size) {
		super();
		this.user_id = user_id;
		this.image_name = image_name;
		this.image_path = image_path;
		this.uploaded_by = uploaded_by;
		this.type = type;
		this.size = size;
		this.uploaded_time = LocalDate.now();
	}

	public UserImage() 
	{
		
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public LocalDate getUploaded_time() {
		return uploaded_time;
	}

	public String getUploaded_by() {
		return uploaded_by;
	}

	public void setUploaded_by(String uploaded_by) {
		this.uploaded_by = uploaded_by;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setUploaded_time(LocalDate uploaded_time) {
		this.uploaded_time = uploaded_time;
	}
	
	
	
	
}
