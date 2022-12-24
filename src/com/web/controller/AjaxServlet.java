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

import org.json.JSONArray;

import com.web.modal.Bookingdao;
import com.web.objects.Booking;
import com.web.util.Dbmanager;


@WebServlet("/Ajax")
public class AjaxServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    static Connection conn = null;            
    static PreparedStatement stmt = null; 
    static ResultSet rs = null;
    static JSONArray data  = null;
    PrintWriter out ;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        try
        {
            out = response.getWriter();

            String vehicleNo = request.getParameter("vehicle_no");
            String vehicleModel = request.getParameter("vehicle_model");

            String status = request.getParameter("status");
            String booking_no = request.getParameter("booking_no");
            System.out.println("VehicleNo ["+vehicleNo+"] Vehicle Model ["+vehicleModel+"]");

            System.out.println("Inside ajax");

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
                String no = getInfo(vehicleNo).trim();

                if ( no !=null && no !="" )
                {
                    System.out.println(no);
                    out.print(no);
                }
                else
                {
                    System.out.println("Not found");
                    out.print("No Vehicle found");
                }
            }
            if ( vehicleModel!=null && vehicleModel.trim().length()  > 0)
            {
                data = getVehicleType(vehicleModel);
                if( data !=null &&  !data.toString().equals(""))
                {
                    System.out.println("data found..");
                }
                else 
                {
                    System.out.println("No data found..");
                }
                System.out.println(data.toString());

                out.println(data.toString());
            }
            if ( status !=null && booking_no !=null )
            {
                Booking b = new Booking();
                b.setStatus(status);
                b.setBooking_no(Integer.parseInt(booking_no));
                
                boolean result = new Bookingdao().updateBooking(b);
                
                if ( result )
                {
                    out.println("success");
                }
                else
                {
                    out.println("failed");
                }
            }
        }catch(Exception s) {
            out.print("exception");
        }
        finally
        {
            out.close();
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

    private JSONArray getVehicleType(String model)
    {
        JSONArray obj = new JSONArray();

        try {    
            System.out.println("Inside VehicleType for Vehicle Model call from ajax");
            conn = Dbmanager.getConnection(); 
            stmt = conn.prepareStatement("select m.title from car_brand c,model m where c.id = m.make_id and c.title=?");
            stmt.setString(1, model.trim());
            rs = stmt.executeQuery(); 
            while(rs.next())
            { 
                obj.put(rs.getString(1));
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
