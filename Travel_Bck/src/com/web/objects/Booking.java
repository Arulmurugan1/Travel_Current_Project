package com.web.objects;



public class Booking 
{
    private String  pickup_from, drop_at, vehicle_no, driver_id,  booked_by, status, booking_time ,customer_name,
                    age,gender,start,email,end,phone;
    
    private int booking_no=0 , customer_id=0 ;
    private double fare=0 ;
    
    public Booking()
    {
        pickup_from = drop_at= vehicle_no = driver_id=  booked_by= status= booking_time =customer_name= age =gender=start=email=end=phone="";
    }
    public String getPickup_from() {
        return this.pickup_from;
    }
    public void setPickup_from(String pickup_from) {
        this.pickup_from = pickup_from;
    }
    public String getDrop_at() {
        return this.drop_at;
    }
    public void setDrop_at(String drop_at) {
        this.drop_at = drop_at;
    }
    public String getVehicle_no() {
        return this.vehicle_no;
    }
    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }
    public String getDriver_id() {
        return this.driver_id;
    }
    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }
    public String getBooked_by() {
        return this.booked_by;
    }
    public void setBooked_by(String booked_by) {
        this.booked_by = booked_by;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getBooking_time() {
        return this.booking_time;
    }
    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }
    public int getBooking_no() {
        return this.booking_no;
    }
    public void setBooking_no(int booking_no) {
        this.booking_no = booking_no;
    }
    public int getCustomer_id() {
        return this.customer_id;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    public double getFare() {
        return this.fare;
    }
    public void setFare(double fare) {
        this.fare = fare;
    }
    public String getCustomer_name() {
        return this.customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getStart() {
        return this.start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEnd() {
        return this.end;
    }
    public void setEnd(String end) {
        this.end = end;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }




}


