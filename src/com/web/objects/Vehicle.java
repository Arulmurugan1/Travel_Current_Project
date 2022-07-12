package com.web.objects;

public class Vehicle {
	private String no ;
	private String model;
	private String type ;
	private String color ;
	public Vehicle(String no, String model, String type, String color) {
		super();
		this.no = no;
		this.model = model;
		this.type = type;
		this.color = color;
	}
	
	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}
