package com.web.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.json.JSONArray;

import com.web.util.Dbmanager;

public class AjaxProcess extends Generic 
{
	public  static AjaxProcess ref = new AjaxProcess();
	private static Connection conn = null;            
    private static PreparedStatement stmt = null; 
    private static ResultSet rs = null;
    
    public void setRequest(HttpServletRequest request) throws Exception 
	{
    		this.session = request.getSession();
    		this.request = request ;
	}
    
    public AjaxProcess() 
    {
    	super();
	}

	public String getInfo(String vehicleNo) 
	{
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
                logContent("vehicle_no => "+sql , LoggerFactory.INFO,null);
            } 
            Generic.closeAll(conn, stmt, rs, this);
        }                                                               
        catch(Exception e)
        {
            sql="Exception Occured Inside getInfo Catch";
            e.printStackTrace();
        } 
        return sql;

    }  

    private void logContent(String string, Level info,Throwable e) 
    {
    	Generic.logContent("Inside VehicleType for Vehicle Model call from ajax" , LoggerFactory.DEBUG,e, new AjaxProcess());
	}

	public JSONArray getVehicleType(String model)
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
            Generic.closeAll(conn, stmt, rs, this);
        }                                                               
        catch(Exception e)
        {
            logContent("", LoggerFactory.ERROR, e);
        }
        return  ( obj.toString().length() > 0 ) ? obj : null ;
    }
	
	public static AjaxProcess getInstance()
	{
		if( ref == null)
		{
			return new AjaxProcess();
		}
		else
		{
			return ref ;
		}
	}
	
}
