package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.web.util.Dbmanager;


@WebServlet("/Ajax")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;            
	PreparedStatement stmt = null; 
	ResultSet rs = null;
	JSONObject vehicleObj  = new JSONObject();

	public AjaxServlet() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		String vehicleNo = request.getParameter("vehicle_no");
		String vehicleModel = request.getParameter("vehicle_model");
		
		System.out.println("VehicleNo ["+vehicleNo+"] Vehicle Model ["+vehicleModel+"]");
		System.out.println("Inside doPost from ajax");
		
		response.setContentType("text/html");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");

		if ( vehicleNo!=null && vehicleNo.trim().length() > 0)
		{
			PrintWriter out = response.getWriter();
			String no = getInfo(vehicleNo).trim();
			System.out.println(no);
			if ( no !=null && no !="" )
			{
				System.out.println("Vehicle Found");
				out.print(no);
			}
			else
			{
				System.out.println("Not found");
				out.print("not_found");
			}
			out.close();
		}
		if ( vehicleModel!=null && vehicleModel.trim().length()  > 0)
		{
			PrintWriter out = response.getWriter();
			vehicleObj = getVehicleType(vehicleModel);
			try
			{
				if( vehicleObj !=null &&  !vehicleObj.toString().equals("")){
					System.out.println("data came true..");
				}
				else {
					System.out.println("data came false..");
				}
				System.out.println(vehicleObj.toString());
			}catch(Exception e)
			{
				e.printStackTrace();
			}			
			finally
			{
				out.println(vehicleObj.toString());
				out.close();
			}
		}
	}
	
	private String getInfo(String vehicleNo) {
		String sql ="";
		try {  
			System.out.println("Inside getInfo for Vehicle No call from ajax");
			conn = Dbmanager.getConnection();
			stmt = conn.prepareStatement("Select vehicle_no from vehicle where vehicle_no =?");
			stmt.setString(1, vehicleNo.trim());
			rs = stmt.executeQuery(); 
			System.out.println(stmt);

			while(rs.next())
			{ 
				sql = rs.getString(1);
				System.out.println(sql);
			} 
			stmt.close();
			rs.close();
			conn.close();
		}                                                               
		catch(Exception e)
		{
			sql="Exception Occured Inside getInfo Catch";
			e.printStackTrace();
		} 
		return sql;

	}  
	
	private JSONObject getVehicleType(String model)
	{
		JSONObject obj = new JSONObject();

		try {    
			System.out.println("Inside VehicleType for Vehicle Model call from ajax");
			conn = Dbmanager.getConnection(); 
			stmt = conn.prepareStatement("select m.title from car_brand c,model m where c.id = m.make_id and c.title=?");
			stmt.setString(1, model.trim());
			ResultSet rs = stmt.executeQuery(); 
			int c = 1;
			while(rs.next())
			{ 
				obj.put( "type"+ c++, rs.getString(1) ); 
			} 
			stmt.close();
			rs.close();
			conn.close();
			System.out.println("Connection closed");
		}                                                               
		catch(Exception e)
		{
			e.getLocalizedMessage();
		}  
	
		return  ( obj.toString().length() > 0 ) ? obj : null ;
	}

//	System.getProperty("java.classpath");
	
//	--------------Old Method to get Vehicle No  Starts-------------------------
	
//	Gson gson = new Gson(); 
//	JsonObject myObj = new JsonObject();
//	
//	if ( vehicleNo!=null && vehicleNo.trim().length() > 0)
//	{
//		Vehicle info = getInfo(vehicleNo);
//		JsonElement vehicleObj = gson.toJsonTree(info);
//		
//		System.out.println(vehicleObj.toString());
//		
//		if(info.getNo() == null){
//			System.out.println("data came false..");
//			myObj.addProperty("success", false);
//		}
//		else {
//			myObj.addProperty("success", true);
//			System.out.println("data came success..");
//		}
//		myObj.add("vehicleObj", vehicleObj);
//		out.println(myObj.toString());
//	}
	
//         --------------------------------  Ends Here -------------------------	
	
}
