package com.web.objects;



public class Booking {
		private int booking_no;
		private String pickup_from;
		private String drop_at;
		private int customer_id;
		private String vehicle_no;
		private String driver_id;
		private double fare;
		private String customer_name;//Customer table column
		private String start;
		private String end ;
		private String age;
		private String gender;
		private String email;
		private String phone;
		
		public int getBooking_no() {
			return booking_no;
		}
		public void setBooking_no(int booking_no) {
			this.booking_no = booking_no;
		}
		public String getPickup_from() {
			return pickup_from;
		}
		public void setPickup_from(String pickup_from) {
			this.pickup_from = pickup_from;
		}
		public String getDrop_at() {
			return drop_at;
		}
		public void setDrop_at(String drop_at) {
			this.drop_at = drop_at;
		}
		public int getCustomer_id() {
			return customer_id;
		}
		public void setCustomer_id(int customer_id) {
			this.customer_id = customer_id;
		}
		public String getVehicle_no() {
			return vehicle_no;
		}
		public void setVehicle_no(String vehicle_no) {
			this.vehicle_no = vehicle_no;
		}
		public String getDriver_id() {
			return driver_id;
		}
		public void setDriver_id(String driver_id) {
			this.driver_id = driver_id;
		}
		public double getFare() {
			return fare;
		}
		public void setFare(double fare) {
			this.fare = fare;
		}


		public String getCustomer_name() {
			return customer_name;
		}


		public void setCustomer_name(String customer_name) {
			this.customer_name = customer_name;
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
		@Override
		public String toString() {
			return "Booking [booking_no=" + booking_no + ", pickup_from=" + pickup_from + ", drop_at=" + drop_at
					+ ", customer_id=" + customer_id + ", vehicle_no=" + vehicle_no + ", driver_id=" + driver_id
					+ ", fare=" + fare + ", customer_name=" + customer_name + ", start=" + start + ", end=" + end
					+ ", age=" + age + ", gender=" + gender + ", email=" + email + ", phone=" + phone + "]";
		}
		

		
		
	}


