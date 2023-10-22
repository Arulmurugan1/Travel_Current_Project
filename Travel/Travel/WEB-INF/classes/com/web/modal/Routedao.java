package com.web.modal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.common.Generic;
import com.web.common.LoggerFactory;
import com.web.objects.Route;

public class Routedao extends Generic
{





	private static final String INSERT ="INSERT INTO ROUTE(vehicle_no, start, end, fare) VALUES(?,?,?,?)" ;
	private static final String SELECTBYNO = "select * from ROUTE where VEHICLE_NO =?";
	private static final String SELECTALL = "select * from ROUTE ORDER BY START";
	private static final String DELETE = "delete from ROUTE where VEHICLE_NO = ?;";
	private static final String UPDATE = "update ROUTE set START=?,END=?,Vehicle_no=? , fare=? where VEHICLE_NO = ?";	
	private static final String CHECK  = "SELECT vehicle_no FROM ROUTE WHERE START = ? AND END =?;";

	public boolean insert(Route r) throws SQLException{
		boolean rowsAffected =false ;
		ps = con.prepareStatement(INSERT);
		int cntl = 0;
		ps.setString(++cntl,r.getVehicle_no() );
		ps.setString(++cntl,r.getStart() );
		ps.setString(++cntl,r.getEnd() );
		ps.setDouble(++cntl, r.getFare());
		logContent(ps,LoggerFactory.DEBUG , null, this);
		rowsAffected = ps.executeUpdate() > 0;
		return rowsAffected;
	}

	public String check(Route r) throws SQLException{
		String str ="";
		ps = con.prepareStatement(CHECK);
		int cntl = 0;
		ps.setString(++cntl,r.getStart() );
		ps.setString(++cntl,r.getEnd() );

		logContent(ps,LoggerFactory.DEBUG , null, this);

		rs = ps.executeQuery();

		if (rs.next())
		{
			str = rs.getString(1);
			logContent("Route for vehicle"+rs.getString(1),LoggerFactory.DEBUG , null, this);
		}
		return str;
	}

	public Route selectRoute(String s) throws SQLException  {
		Route r = new Route();
		ps = con.prepareStatement(SELECTBYNO);
		int cntl = 0;
		ps.setString(++cntl,s);
		logContent(ps,LoggerFactory.DEBUG , null, this);
		rs = ps.executeQuery();			
		while (rs.next()) {
			int c =1;
			r =new Route(rs.getString(c++), rs.getString(c++), rs.getString(c++));				
		}
		return r;
	}

	public List < Route > getAllRoutes() throws SQLException {
		List < Route > users = new ArrayList < Route > ();
		ps = con.prepareStatement(SELECTALL);
		logContent(ps,LoggerFactory.DEBUG , null, this);

		// Step 3: Execute the query or update query
		rs = ps.executeQuery();

		// Step 4: Process the ResultSet object.
		while (rs.next()) 
		{
			users.add( new Route(rs.getString("vehicle_no"), rs.getString("start"), rs.getString("end"),rs.getDouble("fare")));
		}
		return users;
	}

	public boolean delete(String s) throws SQLException{
		boolean rowsAffected = false;
		ps = con.prepareStatement(DELETE);
		int cntl = 0;
		ps.setString(++cntl,s);
		logContent(ps,LoggerFactory.DEBUG , null, this);
		rowsAffected = ps.executeUpdate() > 0;
		logContent("Route deleted "+rowsAffected,LoggerFactory.DEBUG , null, this);
		return rowsAffected;
	}

	public boolean update(Route r) throws SQLException  {
		boolean rowUpdated =false;
		ps = con.prepareStatement(UPDATE);
		int cntl = 0;
		ps.setString(++cntl,r.getStart() );
		ps.setString(++cntl,r.getEnd() );
		ps.setString(++cntl,r.getVehicle_no() );
		ps.setDouble(++cntl, r.getFare());
		
		rowUpdated = ps.executeUpdate() > 0;
		return rowUpdated;
	}
}
