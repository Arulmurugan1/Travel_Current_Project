package com.web.modal;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.web.objects.Vehicle;
import com.web.util.Dbmanager;

public class Vehicledao {


	private static final String INSERT_VEHICLE ="INSERT INTO CAR VALUES(?,?,?,?);" ;
	private static final String SELECT_BY_NO = "select * from car where vehicle_no =?";
	private static final String SELECT_ALL_VEHICLE = "select * from car";
	private static final String DELETE_VEHICLE = "delete from car where vehicle_no = ?;";
	private static final String UPDATE_VEHICLE = "update car set vehicle_model = ?,vehicle_no= ?, vehicle_type =?,vehicle_color=? where vehicle_no = ?;";

	

	Vehicle v = new Vehicle();

	Connection con = null;
	public Vehicledao() { con = Dbmanager.getConnection();}

	public boolean insertVehicle(Vehicle v){
		System.out.println(INSERT_VEHICLE);

		boolean v1 =false;
		try {
			

			PreparedStatement preparedStatement = con.prepareStatement(INSERT_VEHICLE);

			preparedStatement.setString(1,v.getNo() );
			preparedStatement.setString(2,v.getModel() );
			preparedStatement.setString(3,v.getType() );
			preparedStatement.setString(4,v.getColor() );
			System.out.println(preparedStatement);
			v1 = preparedStatement.executeUpdate()>0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return v1;
	}

	public Vehicle selectVehicle(String no) throws Exception {
		System.out.println(SELECT_BY_NO);


		try{

			
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_BY_NO);

			preparedStatement.setString(1, no);
			System.out.println(preparedStatement);


			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String no1 =rs.getString("vehicle_no");
				String model =rs.getString("vehicle_model");
				String type =rs.getString("vehicle_type");
				String color =rs.getString("vehicle_color");
				v = new Vehicle(no1, model, type, color);

			}
		} catch(Exception e) {
			throw e;
		}
		return v;
	}

	public List < Vehicle > getAllVehicle() {

		System.out.println(SELECT_ALL_VEHICLE);


		List < Vehicle > users = new ArrayList < > ();

		try 
		{
			

			PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_VEHICLE);
			System.out.println(preparedStatement);

			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {

				String no =rs.getString("vehicle_no");
				String model =rs.getString("vehicle_model");
				String type =rs.getString("vehicle_type");
				String color =rs.getString("vehicle_color");
				users.add(new Vehicle(no, model, type, color));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public boolean deleteVehicle(String no){
		boolean rowDeleted=false;
		System.out.println(DELETE_VEHICLE);

		try  
		{
			con = Dbmanager.getConnection(); 
			PreparedStatement statement = con.prepareStatement(DELETE_VEHICLE);

			statement.setString(1, no);

			rowDeleted = statement.executeUpdate() > 0;
		}catch(Exception e) {
			e.printStackTrace();;
		}
		return rowDeleted;
	}

	public boolean updateVehicle(Vehicle user)  {
		boolean rowUpdated =false;

		try
		{
			
			PreparedStatement ps = con.prepareStatement(UPDATE_VEHICLE);

			ps.setString(1,v.getModel() );
			ps.setString(2,v.getNo() );
			ps.setString(3,v.getType() );
			ps.setString(4,v.getColor() );
			ps.setString(5, v.getNo());

			rowUpdated = ps.executeUpdate() > 0;

			System.out.println(ps);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}
}
