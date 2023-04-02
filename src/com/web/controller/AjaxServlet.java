package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.web.common.LoggerFactory;
import com.web.modal.Bookingdao;
import com.web.objects.Booking;
import com.web.util.Dbmanager;


@WebServlet("/Ajax")
public class AjaxServlet extends CustomServlet 
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
            
            super.service(request,this, response);
            
            out = response.getWriter();

            String vehicleNo = request.getParameter("vehicle_no");
            String vehicleModel = request.getParameter("vehicle_model");

            String status = request.getParameter("status");
            String booking_no = request.getParameter("booking_no");
            logContent("VehicleNo ["+vehicleNo+"] Vehicle Model ["+vehicleModel+"]" , LoggerFactory.DEBUG, null);;

            logContent("Inside ajax" , LoggerFactory.INFO, null);

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

                if ( no !="" )
                    out.print(no);
                else
                    out.print("No Vehicle found");
            }
            if ( vehicleModel!=null && vehicleModel.trim().length()  > 0)
            {
                data = getVehicleType(vehicleModel);
                if( data !=null &&  !data.toString().equals(""))
                {
                    logContent("data found.." , LoggerFactory.INFO, null);
                }
                else 
                {
                    logContent("No data found.." , LoggerFactory.INFO, null);
                }
                logContent(data.toString() , LoggerFactory.INFO, null);

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
            logContent("Exception in Ajax Servlet", LoggerFactory.ERROR, s);
            
            out.print("Exception in Ajax Servlet  "+s.getMessage());
        }
        finally
        {
            try {
                closeAll();
            } catch (SQLException e) {
                out.print("Exception in closing Connection "+e.getErrorCode()+" " +e.getSQLState()+" "+e.getMessage());
            }
            out.close();
        }
    }

    private String getInfo(String vehicleNo) {
        String sql ="";
        try {  
            logContent("Inside getInfo for Vehicle No call from ajax" , LoggerFactory.INFO, null);
            conn = Dbmanager.getConnection();
            stmt = conn.prepareStatement("Select vehicle_no from vehicle where vehicle_no =?");
            stmt.setString(1, vehicleNo.trim());
            rs = stmt.executeQuery(); 
            

            while(rs.next())
            { 
                sql = rs.getString(1);
                logContent("vehicle_no => "+sql , LoggerFactory.INFO, null);
            } 
            closeAll();
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
            logContent("Inside VehicleType for Vehicle Model call from ajax" , LoggerFactory.DEBUG, null);
            conn = Dbmanager.getConnection(); 
            stmt = conn.prepareStatement("select m.title from car_brand c,model m where c.id = m.make_id and c.title=?");
            stmt.setString(1, model.trim());
            rs = stmt.executeQuery(); 
            while(rs.next())
            { 
                obj.put(rs.getString(1));
            } 
            closeAll();
            logContent("Connection closed" , LoggerFactory.DEBUG, null);
        }                                                               
        catch(Exception e)
        {
            logContent("", LoggerFactory.ERROR, e);
        }
        return  ( obj.toString().length() > 0 ) ? obj : null ;
    }
    
    private void closeAll() throws SQLException
    {
        if ( conn !=null )
        {
            conn.close();
        }
        if ( stmt !=null )
        {
            stmt.close();
        }
        if ( rs !=null  )
        {
            rs.close();
        }
        logContent("Connection ["+conn+"] Statement ["+stmt+"] Resultset ["+rs+"]" , LoggerFactory.DEBUG, null);
    }

}
