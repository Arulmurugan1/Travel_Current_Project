package com.web.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.objects.Route;
import com.web.util.Dbmanager;

public class Routedao 
{
	
	

	boolean rowsAffected =false ;

	private static final String INSERT ="INSERT INTO ROUTE VALUES(?,?,?)" ;
	private static final String SELECTBYNO = "select * from ROUTE where VEHICLE_NO =?";
	private static final String SELECTALL = "select * from ROUTE ORDER BY START";
	private static final String DELETE = "delete from ROUTE where VEHICLE_NO = ?;";
	private static final String UPDATE = "update ROUTE set START=?,END=? where VEHICLE_NO = ?";	
	private static final String CHECK  = "SELECT vehicle_no FROM ROUTE WHERE START = ? AND END =?;";
	
	Connection con = null ;
	
	public Routedao() {con = Dbmanager.getConnection();}

	public boolean insert(Route r){
		System.out.println(INSERT);
		

		try {
				
			PreparedStatement preparedStatement = con.prepareStatement(INSERT);
			int cntl = 0;
			preparedStatement.setString(++cntl,r.getVehicle_no() );
			preparedStatement.setString(++cntl,r.getStart() );
			preparedStatement.setString(++cntl,r.getEnd() );
			
			System.out.println(preparedStatement);
			
			rowsAffected = preparedStatement.executeUpdate() > 0;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public String check(Route r){
		System.out.println(CHECK);
		String str ="";

		try {
				
			PreparedStatement preparedStatement = con.prepareStatement(CHECK);
			int cntl = 0;
			preparedStatement.setString(++cntl,r.getStart() );
			preparedStatement.setString(++cntl,r.getEnd() );
			
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next())
			{
				str = rs.getString(1)+"";
				System.out.println("Route for vehicle"+rs.getString(1));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public Route selectRoute(String s)  {
		System.out.println(SELECTBYNO);
		
			Route r = new Route();
		try{

			PreparedStatement preparedStatement = con.prepareStatement(SELECTBYNO);
			int cntl = 0;
			preparedStatement.setString(++cntl,s);
			System.out.println(preparedStatement);


			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				int c =1;
				r =new Route(rs.getString(c++), rs.getString(c++), rs.getString(c++));
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	public List < Route > getAllRoutes() {

		System.out.println(SELECTALL);

		
		List < Route > users = new ArrayList < Route > ();

		try 
		{
			

			PreparedStatement preparedStatement = con.prepareStatement(SELECTALL);
			System.out.println(preparedStatement);

			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int c =1;
				users.add( new Route(rs.getString(c++), rs.getString(c++), rs.getString(c++)));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public boolean delete(String s){
		
		System.out.println(DELETE);
		
		try  
		{
			
			PreparedStatement statement = con.prepareStatement(DELETE);
			int cntl = 0;
			statement.setString(++cntl,s);
			
			rowsAffected = statement.executeUpdate() > 0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public boolean update(Route r)  {
		boolean rowUpdated =false;
		
		try
		{ 
			PreparedStatement preparedStatement = con.prepareStatement(UPDATE);
			int cntl = 0;
			preparedStatement.setString(++cntl,r.getStart() );
			preparedStatement.setString(++cntl,r.getEnd() );
			preparedStatement.setString(++cntl,r.getVehicle_no() );
			rowUpdated = preparedStatement.executeUpdate() > 0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}

}
