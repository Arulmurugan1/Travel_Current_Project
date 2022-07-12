package com.web.objects;

public class Route 
{
	private String vehicle_no,start,end;

	public Route(String vehicle_no, String start, String end) {
		super();
		this.vehicle_no = vehicle_no;
		this.start = start;
		this.end = end;
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
	
	
}

