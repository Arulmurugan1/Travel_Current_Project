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
		@Override
		public String toString() {
			return "Booking [booking_no=" + booking_no + ", pickup_from=" + pickup_from + ", drop_at=" + drop_at
					+ ", customer_id=" + customer_id + ", vehicle_no=" + vehicle_no + ", driver_id=" + driver_id
					+ ", fare=" + fare + ", customer_name=" + customer_name + "]";
		}

		
		
	}


