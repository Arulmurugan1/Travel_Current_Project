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
	private static final String DELETE_DRIVER = "delete from DRIVER where vehicle_no = ?;";
	private static final String UPDATE_DRIVER = "update DRIVER set driver_id = ?,driver_name= ?, gender =?,age=?,city=?,phone=?,vehicle_no=? where driver_id = ?";

	
	static Connection con = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	
	public Driverdao() { con = Dbmanager.getConnection();}
	
	Driver v = null;
	
	
	
	

	public boolean insertDriver(Driver d){
		System.out.println(INSERT_DRIVER);
		

		try {
				
			ps = con.prepareStatement(INSERT_DRIVER);
			int cntl = 0;
			ps.setString(++cntl,d.getName() );
			ps.setInt(++cntl,d.getAge() );
			ps.setString(++cntl,d.getGender() );
			ps.setString(++cntl,d.getCity() );
			ps.setString(++cntl,d.getPhone() );
			ps.setString(++cntl,d.getNo() );
			
			System.out.println(ps);
			
			rowsAffected = ps.executeUpdate() > 0;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public Driver selectDriver(int id)  {
		System.out.println(SELECT_BY_ID);
		

		try{
			
			ps = con.prepareStatement(SELECT_BY_ID);
			int cntl = 0;
			ps.setInt(++cntl, id);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			
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

		List < Driver > users = new ArrayList < Driver > ();

		try 
		{

			ps = con.prepareStatement(SELECT_ALL_DRIVER);
			System.out.println(ps);

			// Step 3: Execute the query or update query
			 rs = ps.executeQuery();

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

	public boolean deleteDriver(String id){
		try  
		{
			
			ps = con.prepareStatement(DELETE_DRIVER);
			int cntl = 0;
			ps.setString(++cntl, id);
			System.out.println(ps);
			rowsAffected = ps.executeUpdate() > 0;
			
			System.out.println("Deleted driver"+rowsAffected);
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public boolean updateDriver(Driver user)  {
		boolean rowUpdated =false;
		
		try
		{
			
			ps = con.prepareStatement(UPDATE_DRIVER);
			int cntl = 0;
			ps.setInt(++cntl,v.getId());
			ps.setString(++cntl,v.getName() );
			ps.setString(++cntl,v.getGender() );
			ps.setInt(++cntl,v.getAge() );
			ps.setString(++cntl,v.getCity() );
			ps.setString(++cntl,v.getPhone() );
			ps.setString(++cntl,v.getNo() );
			ps.setInt(++cntl,v.getId());
			rowUpdated = ps.executeUpdate() > 0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}

	public void closeAll() throws Throwable
	{
		if ( con !=null && !con.isClosed())
		{
			con.close();
			con = null;
			System.out.println("Connection Closed ::"+con);
		}
		if ( ps !=null )
		{
			ps.close();
			ps = null;
			System.out.println("Statement Closed ::"+ps);
		}
		if ( rs !=null  )
		{
			rs.close();
			rs = null;
			System.out.println("Resultset Closed ::"+rs);
		}
	}
	
}
