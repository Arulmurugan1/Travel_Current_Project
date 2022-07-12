package com.web.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.objects.Route;
import com.web.util.Dbmanager;

public class Routedao {

	boolean rowsAffected =false ;

	private static final String INSERT ="INSERT INTO ROUTE VALUES(?,?,?)" ;
	private static final String SELECTBYNO = "select * from ROUTE where VEHICLE_NO =?";
	private static final String SELECTALL = "select * from ROUTE ORDER BY START";
	private static final String DELETE = "delete from ROUTE where VEHICLE_NO = ?;";
	private static final String UPDATE = "update ROUTE set START=?,END=? where VEHICLE_NO = ?";	
	private static final String CHECK  = "SELECT 'X' FROM ROUTE WHERE START = ? AND END =?;";
	
	Connection connection = null;

	public boolean insert(Route r){
		System.out.println(INSERT);
		

		try {
			connection = Dbmanager.getConnection();
				
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
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

	public boolean check(Route r){
		System.out.println(CHECK);
		

		try {
			connection = Dbmanager.getConnection();
				
			PreparedStatement preparedStatement = connection.prepareStatement(CHECK);
			int cntl = 0;
			preparedStatement.setString(++cntl,r.getStart() );
			preparedStatement.setString(++cntl,r.getEnd() );
			
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next())
			{
				rowsAffected = true ;
				System.out.println("Route Exist");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	public Route selectRoute(String s)  {
		System.out.println(SELECTBYNO);
		
			Route r = new Route();
		try{

			connection = Dbmanager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECTBYNO);
			int cntl = 0;
			preparedStatement.setString(++cntl,s);
			System.out.println(preparedStatement);


			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				r =new Route(rs.getString("vehicle_no"), rs.getString("start"), rs.getString("end"));
				
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
			connection = Dbmanager.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECTALL);
			System.out.println(preparedStatement);

			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {

				users.add( new Route(rs.getString("vehicle_no"), rs.getString("start"), rs.getString("end")));
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
			connection = Dbmanager.getConnection(); 
			PreparedStatement statement = connection.prepareStatement(DELETE);
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
			connection = Dbmanager.getConnection(); 
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
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
