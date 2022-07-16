package com.web.modal;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.web.objects.Booking;
import com.web.objects.Customer;
import com.web.util.Dbmanager;

public class Bookingdao {

	private static final String INSERT_BOOKING = "INSERT INTO BOOKING VALUES(null,?,?,?,?,?,?);";
	private static final String SELECT_USER_BY_ID = "select * from booking where booking_no =?";
	private static final String SELECT_USER_BY_CUSTOMER_ID = "select * from booking where customer_id =?";
	private static final String SELECT_ALL_USERS = "SELECT A.*,B.CUSTOMER_NAME FROM BOOKING A,CUSTOMER B WHERE A.CUSTOMER_ID=B.CUSTOMER_ID";
	private static final String DELETE_USERS_SQL = "delete from booking where booking_no = ?;";
	
	Connection con =Dbmanager.getConnection();
	

	public Bookingdao() {
	}

	public boolean insertBooking(Booking user) 
	{
		System.out.println(INSERT_BOOKING);
		boolean rowsAffected = false;
		try {
			
			Vector field = new Vector();
			
			field.add(user.getPickup_from());
			field.add(user.getDrop_at());
			field.add(user.getCustomer_id());
			field.add(user.getVehicle_no());
			field.add(user.getDriver_id());
			field.add(user.getFare());		
			
			System.out.println(field +"  "+field.size());
			
			rowsAffected = 	Dbmanager.insertObjects(INSERT_BOOKING, field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public Booking selectBooking(int id) {
		Booking user = null;
		System.out.println(SELECT_USER_BY_ID);
		
		

		try {

			
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_BY_ID);

			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int c =1;
				int id1 = rs.getInt(c++);
				String pickup = rs.getString(c++);
				String drop = rs.getString(c++);
				int customer = rs.getInt(c++);
				String vehicle = rs.getString(c++);
				String driver = rs.getString(c++);
				double fare = rs.getDouble(c++);
				user = new Booking(id1, pickup, drop, customer, vehicle, driver, fare);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public Booking selectBookingByCustomerId(int id) {
		Booking user = null;
		System.out.println(SELECT_USER_BY_CUSTOMER_ID);

		try {

			
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_BY_CUSTOMER_ID);

			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int c =1;
				int id1 = rs.getInt(c++);
				String pickup = rs.getString(c++);
				String drop = rs.getString(c++);
				int customer = rs.getInt(c++);
				String vehicle = rs.getString(c++);
				String driver = rs.getString(c++);
				double fare = rs.getDouble(c++);
				user = new Booking(id1, pickup, drop, customer, vehicle, driver, fare);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<Booking> getAllBooking() {

		System.out.println(SELECT_ALL_USERS);

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Booking> users = new ArrayList<>();

		

		try {
			

			PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_USERS);
			System.out.println(preparedStatement);

			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {

				int c =1;
				int id = rs.getInt(c++);
				String pickup = rs.getString(c++);
				String drop = rs.getString(c++);
				int customer = rs.getInt(c++);
				String vehicle = rs.getString(c++);
				String driver = rs.getString(c++);
				double fare = rs.getDouble(c++);
				String customername = rs.getString(c++);
				users.add(new Booking(id, pickup, drop, customer, vehicle, driver, fare, customername));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public boolean deleteBooking(int id) 
	{
		boolean rowsAffected = false;
		System.out.println(DELETE_USERS_SQL);
		try {
			
			PreparedStatement statement = con.prepareStatement(DELETE_USERS_SQL);

			statement.setInt(1, id);
			rowsAffected = statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public boolean updateBooking(Booking user) 
	{
	    String UPDATE_BOOKING = "  update booking set";
	    
		
		boolean rowsAffected = false;
		try {			
			Vector field = new Vector();
			
			if ( user.getPickup_from().trim().length() > 0)
				UPDATE_BOOKING += " pickup_from = ?, ";
			if ( user.getDrop_at().trim().length() > 0)
				UPDATE_BOOKING += " drop_at = ?, ";
			if ( user.getCustomer_id()  > 0)
				UPDATE_BOOKING += " customer_id = ?, ";
			if ( user.getVehicle_no().trim().length() > 0)
				UPDATE_BOOKING += " vehicle_no = ?, ";
			if ( user.getDriver_id().trim().length() > 0)
				UPDATE_BOOKING += " driver_id = ?, ";
			if ( user.getFare() > 0)
				UPDATE_BOOKING += " fare = ? ";
				UPDATE_BOOKING += " where booking_no = ?";	
			
				System.out.println(UPDATE_BOOKING);
			
			if ( user.getPickup_from().trim().length() > 0)
				field.add(user.getPickup_from());
			if ( user.getDrop_at().trim().length() > 0)
				field.add(user.getDrop_at());
			if ( user.getCustomer_id()  > 0)
				field.add(user.getCustomer_id());
			if ( user.getVehicle_no().trim().length() > 0)
				field.add(user.getVehicle_no());
			if ( user.getDriver_id().trim().length() > 0)
				field.add(user.getDriver_id());
			if ( user.getFare() > 0)
				field.add(user.getFare());	
				field.add(user.getBooking_no());	
			
			System.out.println(field +"  "+field.size());
			
			rowsAffected = 	Dbmanager.updateObjects(UPDATE_BOOKING, field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	
	}

	public boolean bookingCheck(Booking user, Customer c) {

		boolean rows = true;
		int cntl = 1;

		try {
			
			PreparedStatement preparedStatement = con.prepareStatement(CheckQuery(user, c));

			System.out.println(CheckQuery(user, c));

			if (user.getPickup_from().length() > 0)
				preparedStatement.setString(cntl++, user.getPickup_from());

			if (user.getDrop_at().length() > 0)
				preparedStatement.setString(cntl++, user.getDrop_at());

			if (user.getVehicle_no().length() > 0)
				preparedStatement.setString(cntl++, user.getVehicle_no());

			if (user.getDriver_id().length() > 0)
				preparedStatement.setString(cntl++, user.getDriver_id());

			if (user.getFare() > 0)
				preparedStatement.setDouble(cntl++, user.getFare());

			if (c.getCustomer_name().length() > 0)
				preparedStatement.setString(cntl++, c.getCustomer_name());

			if (user.getBooking_no() > 0)
				preparedStatement.setInt(cntl++, user.getBooking_no());

			if (user.getCustomer_id() > 0)
				preparedStatement.setInt(cntl++, user.getCustomer_id());

			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				rows = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;

	}

	public static String CheckQuery(Booking b, Customer c) {
		String sql = " SELECT A.*,B.CUSTOMER_NAME FROM BOOKING A,CUSTOMER B WHERE A.CUSTOMER_ID=B.CUSTOMER_ID ";

		if (b.getPickup_from().length() > 0)
			sql += " AND A.PICKUP_FROM =? ";

		if (b.getDrop_at().length() > 0)
			sql += " AND A.DROP_AT =? ";

		if (b.getVehicle_no().length() > 0)
			sql += " AND A.VEHICLE_NO =? ";

		if (b.getDriver_id().length() > 0)
			sql += " AND A.DRIVER_ID =? ";

		if (b.getFare() > 0)
			sql += " AND A.FARE =? ";

		if (c.getCustomer_name().length() > 0)
			sql += " AND B.CUSTOMER_NAME =? ";

		if (b.getBooking_no() > 0)
			sql += " AND A.BOOKING_NO =? ";

		if (b.getCustomer_id() > 0)
			sql += " AND A.CUSTOMER_ID =? ";

		return sql;

	}

}