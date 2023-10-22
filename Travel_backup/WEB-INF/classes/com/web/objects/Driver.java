package com.web.objects;

public class Driver {
	private String name,gender,city,phone,no;
	private int age,id ;
	
	public Driver(String name, String gender,String city, String phone,  String no, int age, int id) {
		super();
		this.city = city;
		this.phone = phone;
		this.name = name;
		this.gender = gender;
		this.no = no;
		this.age = age;
		this.id = id;
	}
	
	
	public Driver() {
		// TODO Auto-generated constructor stub
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


    @Override
    public String toString() {
        return "Driver [name=" + name + ", gender=" + gender + ", city=" + city + ", phone=" + phone + ", no=" + no
                + ", age=" + age + ", id=" + id + "]";
    }

	
	
	
	
}
