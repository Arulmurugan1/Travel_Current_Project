package com.web.objects;

public class Customer {
	private String customer_name;	
	private String start;
	private String end ;
	private String age;
	private String gender;
	private String email;
	private String phone;
	private int customer_id;

	

	public Customer(String customer_name, String start, String end, String age, String gender, String email,
			String phone) {
		super();
		this.customer_name = customer_name;
		this.start = start;
		this.end = end;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
	}
	public Customer(String start, String end, String email, String phone, int customer_id) {
		super();
		this.start = start;
		this.end = end;
		this.email = email;
		this.phone = phone;
		this.customer_id = customer_id;
	}
	public Customer(String customer_name, String start, String end, String age, String gender, String email,
			String phone, int customer_id) {
		super();
		this.customer_name = customer_name;
		this.start = start;
		this.end = end;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.customer_id = customer_id;
	}
	public Customer() {
		super();
		
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
