package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.web.objects.Vehicle;
import com.web.util.Dbmanager;


@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxServlet() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String vehicleNo = request.getParameter("vehicleNo");

		System.out.println("Inside doPost for Vehicle No call from ajax");

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");

		System.getProperty("java.classpath");
		
		Gson gson = new Gson(); 
		JsonObject myObj = new JsonObject();

		Vehicle info = getInfo(vehicleNo);
		JsonElement vehicleObj = gson.toJsonTree(info);
		if(info.getNo() == null){
			System.out.println("data came false..");
			myObj.addProperty("success", false);
		}
		else {
			myObj.addProperty("success", true);
			System.out.println("data came success..");
		}
		myObj.add("vehicleObj", vehicleObj);
		out.println(myObj.toString());

		out.close();

	}

	//Get Country Information
	private Vehicle getInfo(String vehicleNo) {

		Vehicle v = new Vehicle();
		Connection conn = null;            
		PreparedStatement stmt = null;     
		String sql = null;

		try {    
			System.out.println("Inside getInfo for Vehicle No call from ajax");
			conn = Dbmanager.getConnection();

			sql = "Select vehicle_no from car where vehicle_no =?"; 
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vehicleNo.trim());
			ResultSet rs = stmt.executeQuery(); 

			while(rs.next()){ 
				v.setNo(rs.getString(1));
			}                                                                         

			rs.close();                                                               
			stmt.close();                                                             
			stmt = null;                                                              


			conn.close();                                                             
			conn = null;                                                   

		}                                                               
		catch(Exception e)
		{
			e.getLocalizedMessage();
		}                      

		finally {                                                       

			if (stmt != null) {                                            
				try {                                                         
					stmt.close();                                                
				} catch (SQLException e) {                                
					// ignore -- as we can't do anything about it here           
				}                                                             

				stmt = null;                                            
			}                                                        

			if (conn != null) {                                      
				try {                                                   
					conn.close();                                          
				} catch (SQLException e) {                          

				}                                                       

				conn = null;                                            
			}                                                        
		}              

		return v;

	}   


}
