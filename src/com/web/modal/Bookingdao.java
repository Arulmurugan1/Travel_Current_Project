package com.web.modal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.web.objects.Booking;
import com.web.objects.Customer;
import com.web.util.Dbmanager;

public class Bookingdao {

	private static final String INSERT_BOOKING = "INSERT INTO BOOKING VALUES(null,?,?,?,?,?,?,'Arulmurugan',' ',sysdate())";
	private static final String SELECT_USER_BY_ID = "select * from booking where booking_no =?";
	private static final String SELECT_ALL_USERS = "SELECT A.*,B.* FROM BOOKING A,CUSTOMER B WHERE A.CUSTOMER_ID=B.CUSTOMER_ID";
	private static final String DELETE_USERS_SQL = "delete from booking where booking_no = ?;";

	static Connection con = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	Booking u = null ;

	public Bookingdao() {
		con = Dbmanager.getConnection();
	}

	public int insertBooking(Booking user) 
	{
		int result = 0;
		try {			
			ps = con.prepareStatement(INSERT_BOOKING, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,user.getPickup_from() );
			ps.setString(2,user.getDrop_at() );
			ps.setInt(3,user.getCustomer_id() );
			ps.setString(4,user.getVehicle_no() );
			ps.setString(5,user.getDriver_id() );
			ps.setDouble(6,user.getFare() );	
			System.out.println(ps);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();

			while ( rs.next())
			{
				result = rs.getInt(1);
				System.out.println("Booking No ::"+result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Booking selectBooking(int id) {
		System.out.println(SELECT_USER_BY_ID);	

		try {			
			ps = con.prepareStatement(SELECT_USER_BY_ID);
			ps.setInt(1, id);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) 
			{	
				u = new Booking() ;
				u.setBooking_no(rs.getInt(1));
				u.setPickup_from(rs.getString(2));
				u.setDrop_at(rs.getString(3));
				u.setCustomer_id(rs.getInt(4));
				u.setVehicle_no(rs.getString(5));
				u.setDriver_id(rs.getString(6));
				u.setFare(rs.getDouble(7));
				u.setCustomer_name(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	public List<Booking> getAllBooking() {

		List<Booking> ls = new ArrayList<>();
		try {
			ps = Dbmanager.getConnection().prepareStatement(SELECT_ALL_USERS);
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				u = new Booking();
				u.setBooking_no(rs.getInt("booking_no"));
				u.setPickup_from(rs.getString("pickup_from"));
				u.setDrop_at(rs.getString("drop_at"));
				u.setCustomer_id(rs.getInt("customer_id"));
				u.setVehicle_no(rs.getString("vehicle_no"));
				u.setDriver_id(rs.getString("driver_id"));
				u.setFare(rs.getDouble("fare"));
				u.setCustomer_name(rs.getString("customer_name"));
				u.setAge(rs.getString("age"));
				u.setGender(rs.getString("gender"));
				u.setStart(rs.getString("start"));
				u.setEmail(rs.getString("email"));
				u.setEnd(rs.getString("end"));
				u.setPhone(rs.getString("phone"));
				u.setStatus(rs.getString("status").trim());
				ls.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

	public boolean deleteBooking(int id) 
	{
		boolean rowsAffected = false;
		System.out.println(DELETE_USERS_SQL);
		try {

			ps = con.prepareStatement(DELETE_USERS_SQL);

			ps.setInt(1, id);
			rowsAffected = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public boolean updateBooking(Booking user) 
	{
		String sql = "  update booking set";


		boolean result = false;
		try {
			if ( user.getPickup_from().trim().length() > 0)
				sql += " pickup_from = ?, ";
			if ( user.getDrop_at().trim().length() > 0)
				sql += " drop_at = ?, ";
			if ( user.getCustomer_id()  > 0)
				sql += " customer_id = ?, ";
			if ( user.getVehicle_no().trim().length() > 0)
				sql += " vehicle_no = ?, ";
			if ( user.getDriver_id().trim().length() > 0)
				sql += " driver_id = ?, ";
			if ( user.getFare() > 0)
				sql += " fare = ? ";
			if ( user.getStatus().trim().length() > 0 )
                sql += " status = ? ";
			sql += " where booking_no = ?";	
			
			ps = con.prepareStatement(sql);
			
			int c =1;
			
			if ( user.getPickup_from().trim().length() > 0)
				ps.setString(c++,user.getPickup_from());
			if ( user.getDrop_at().trim().length() > 0)
				ps.setString(c++,user.getDrop_at());
			if ( user.getCustomer_id()  > 0)
				ps.setInt(c++,user.getCustomer_id());
			if ( user.getVehicle_no().trim().length() > 0)
				ps.setString(c++,user.getVehicle_no());
			if ( user.getDriver_id().trim().length() > 0)
				ps.setString(c++,user.getDriver_id());
			if ( user.getFare() > 0)
				ps.setDouble(c++,user.getFare());	
			if ( user.getStatus().trim().length() > 0 )
			    ps.setString(c++,user.getStatus());
			ps.setInt(c++,user.getBooking_no());	

			System.out.println(ps);
			
			result = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public boolean bookingCheck(Booking user, Customer c) {

		boolean rows = true;
		int cntl = 1;

		try {

			ps = con.prepareStatement(CheckQuery(user, c));

			System.out.println(CheckQuery(user, c));

			if (user.getPickup_from().length() > 0)
				ps.setString(cntl++, user.getPickup_from());

			if (user.getDrop_at().length() > 0)
				ps.setString(cntl++, user.getDrop_at());

			if (user.getVehicle_no().length() > 0)
				ps.setString(cntl++, user.getVehicle_no());

			if (user.getDriver_id().length() > 0)
				ps.setString(cntl++, user.getDriver_id());

			if (user.getFare() > 0)
				ps.setDouble(cntl++, user.getFare());

			if (c.getCustomer_name().length() > 0)
				ps.setString(cntl++, c.getCustomer_name());

			if (user.getBooking_no() > 0)
				ps.setInt(cntl++, user.getBooking_no());

			if (user.getCustomer_id() > 0)
				ps.setInt(cntl++, user.getCustomer_id());

			System.out.println(ps);

			ResultSet rs = ps.executeQuery();

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

	public void closeAll() throws Exception
	{
		if ( con !=null && !con.isClosed())
		{
			con.close();
			con = null;
		}
		if ( ps !=null )
		{
			ps.close();
			ps = null;
		}
		if ( rs !=null  )
		{
			rs.close();
			rs = null;
		}
	}
}