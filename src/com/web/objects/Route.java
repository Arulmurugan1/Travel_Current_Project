package com.web.objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="route")
public class Route 
{
	@Id
	private String vehicle_no;
	private String start,end;
	private double fare;

	public Route(String vehicle_no, String start, String end) {
		super();
		this.vehicle_no = vehicle_no;
		this.start = start;
		this.end = end;
	}
	
	
	public Route(String vehicle_no, String start, String end, double fare) {
		super();
		this.vehicle_no = vehicle_no;
		this.start = start;
		this.end = end;
		this.fare = fare;
	}


	public Route() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
	
	public double getFare() {
		return fare;
	}


	public void setFare(double fare) {
		this.fare = fare;
	}
	
	
	
}

