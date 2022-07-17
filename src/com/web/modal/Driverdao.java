package com.web.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.objects.Driver;
import com.web.util.Dbmanager;

public class Driverdao {

	boolean rowsAffected =false ;

	private static final String INSERT_DRIVER ="INSERT INTO DRIVER VALUES(null,?,?,?,?,?,?)" ;
	private static final String SELECT_BY_ID = "select * from DRIVER where driver_id =?";
	private static final String SELECT_ALL_DRIVER = "select * from DRIVER";
	private static final String DELETE_DRIVER = "delete from DRIVER where driver_id = ?;";
	private static final String UPDATE_DRIVER = "update DRIVER set driver_id = ?,driver_name= ?, gender =?,age=?,city=?,phone=?,vehicle_no=? where driver_id = ?";

	
	Connection con = null;
	public Driverdao() { con = Dbmanager.getConnection();}
	
	Driver v = new Driver();
	
	
	
	

	public boolean insertDriver(Driver d){
		System.out.println(INSERT_DRIVER);
		

		try {
				
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_DRIVER);
			int cntl = 0;
			preparedStatement.setString(++cntl,d.getName() );
			preparedStatement.setString(++cntl,d.getGender() );
			preparedStatement.setInt(++cntl,d.getAge() );
			preparedStatement.setString(++cntl,d.getCity() );
			preparedStatement.setString(++cntl,d.getPhone() );
			preparedStatement.setString(++cntl,d.getNo() );
			
			System.out.println(preparedStatement);
			
			rowsAffected = preparedStatement.executeUpdate() > 0;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public Driver selectDriver(int id)  {
		System.out.println(SELECT_BY_ID);
		

		try{
			
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_BY_ID);
			int cntl = 0;
			preparedStatement.setInt(++cntl, id);
			System.out.println(preparedStatement);


			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				v = new Driver
						(	rs.getString("driver_name"), rs.getString("gender"), rs.getString("city"), 
							rs.getString("phone"),rs.getString("vehicle_no"),rs.getInt("age"),rs.getInt("driver_id")	);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	public List < Driver > getAllDriver() {

		System.out.println(SELECT_ALL_DRIVER);

		
		List < Driver > users = new ArrayList < Driver > ();

		try 
		{

			PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_DRIVER);
			System.out.println(preparedStatement);

			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {

				users.add(new Driver
				(	rs.getString("driver_name"), rs.getString("gender"), rs.getString("city"), 
				rs.getString("phone"),rs.getString("vehicle_no"),rs.getInt("age"),rs.getInt("driver_id")	)	);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public boolean deleteDriver(int id){
		
		System.out.println(DELETE_DRIVER);
		
		try  
		{
			
			PreparedStatement statement = con.prepareStatement(DELETE_DRIVER);
			int cntl = 0;
			statement.setInt(++cntl, id);
			
			rowsAffected = statement.executeUpdate() > 0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public boolean updateDriver(Driver user)  {
		boolean rowUpdated =false;
		
		try
		{
			
			PreparedStatement preparedStatement = con.prepareStatement(UPDATE_DRIVER);
			int cntl = 0;
			preparedStatement.setInt(++cntl,v.getId());
			preparedStatement.setString(++cntl,v.getName() );
			preparedStatement.setString(++cntl,v.getGender() );
			preparedStatement.setInt(++cntl,v.getAge() );
			preparedStatement.setString(++cntl,v.getCity() );
			preparedStatement.setString(++cntl,v.getPhone() );
			preparedStatement.setString(++cntl,v.getNo() );
			preparedStatement.setInt(++cntl,v.getId());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}


}
