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
		public Booking(int booking_no, String pickup_from, String drop_at, int customer_id, String vehicle_no,
				String driver_id, double fare,String customer_name) {
			super();
			this.booking_no = booking_no;
			this.pickup_from = pickup_from;
			this.drop_at = drop_at;
			this.customer_id = customer_id;
			this.vehicle_no = vehicle_no;
			this.driver_id = driver_id;
			this.fare = fare;
			this.customer_name =customer_name;
		}
		
		
		public Booking(String pickup_from, String drop_at, String vehicle_no, String driver_id,
				double fare, String customer_name) {
			super();
			this.pickup_from = pickup_from;
			this.drop_at = drop_at;
			this.vehicle_no = vehicle_no;
			this.driver_id = driver_id;
			this.fare = fare;
			this.customer_name = customer_name;
		}


		public Booking(int booking_no, String pickup_from, String drop_at, int customer_id, String vehicle_no,
				String driver_id, double fare) {
			super();
			this.booking_no = booking_no;
			this.pickup_from = pickup_from;
			this.drop_at = drop_at;
			this.customer_id = customer_id;
			this.vehicle_no = vehicle_no;
			this.driver_id = driver_id;
			this.fare = fare;
			
		}
		
		public Booking(int booking_no, int customer_id, String vehicle_no, String driver_id) {
			super();
			this.booking_no = booking_no;
			this.customer_id = customer_id;
			this.vehicle_no = vehicle_no;
			this.driver_id = driver_id;
		}


		public Booking(String pickup_from, String drop_at, int customer_id, String vehicle_no, String driver_id,
				double fare) {
			super();
			this.pickup_from = pickup_from;
			this.drop_at = drop_at;
			this.customer_id = customer_id;
			this.vehicle_no = vehicle_no;
			this.driver_id = driver_id;
			this.fare = fare;
		}


		public Booking() {
			super();
			
		}
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

		
		
	}


