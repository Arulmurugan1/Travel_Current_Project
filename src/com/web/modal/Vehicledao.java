package com.web.modal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.objects.Vehicle;
import com.web.util.Dbmanager;

public class Vehicledao {


	private static final String INSERT_VEHICLE ="INSERT INTO vehicle VALUES(?,?,?,?);" ;
	private static final String SELECT_BY_NO = "select * from vehicle where vehicle_no =?";
	private static final String SELECT_ALL_VEHICLE = "select * from vehicle";
	private static final String DELETE_VEHICLE = "delete from vehicle where vehicle_no = ?;";
	private static final String UPDATE_VEHICLE = "update vehicle set vehicle_model = ?,vehicle_no= ?, vehicle_type =?,vehicle_color=? where vehicle_no = ?;";



	private static Vehicle v ;
	private static PreparedStatement ps ;
	private static ResultSet rs ;
	private static Connection con ;

	public Vehicledao() { con = Dbmanager.getConnection();}

	public boolean insertVehicle(Vehicle v){
		boolean v1 =false;
		try {


			ps = con.prepareStatement(INSERT_VEHICLE);

			ps.setString(1,v.getNo() );
			ps.setString(2,v.getModel() );
			ps.setString(3,v.getType() );
			ps.setString(4,v.getColor() );
			System.out.println(ps);
			v1 = ps.executeUpdate()>0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return v1;
	}

	public Vehicle selectVehicle(String no) {
		System.out.println(SELECT_BY_NO);


		try{

			ps = con.prepareStatement(SELECT_BY_NO);

			ps.setString(1, no);
			System.out.println(ps);


			rs = ps.executeQuery();

			while (rs.next()) {
				String no1 =rs.getString("vehicle_no");
				String model =rs.getString("vehicle_model");
				String type =rs.getString("vehicle_type");
				String color =rs.getString("vehicle_color");
				v = new Vehicle(no1, model, type, color);

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	public List < Vehicle > getAllVehicle() 
	{
		List<Vehicle> ls = new ArrayList < > ();
		try 
		{
			ps = Dbmanager.getConnection().prepareStatement(SELECT_ALL_VEHICLE);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				ls.add(new Vehicle(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4))) ;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return ls ;
	}

	public boolean deleteVehicle(String no){
		boolean rowDeleted=false;
		System.out.println(DELETE_VEHICLE);

		try  
		{
			ps = con.prepareStatement(DELETE_VEHICLE);

			ps.setString(1, no);

			rowDeleted = ps.executeUpdate() > 0;

			System.out.println("Deleted Vehicle "+rowDeleted);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}

	public boolean updateVehicle(Vehicle user)  {
		boolean rowUpdated =false;

		try
		{

			ps = con.prepareStatement(UPDATE_VEHICLE);

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
		System.out.println("Connection ["+con+"] Statement ["+ps+"] Resultset ["+rs+"]");
	}
}
