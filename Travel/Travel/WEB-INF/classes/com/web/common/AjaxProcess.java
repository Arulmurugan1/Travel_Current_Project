package com.web.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.Part;

import org.apache.log4j.Level;
import org.json.JSONArray;
import org.json.JSONObject;

import com.web.modal.Logindao;
import com.web.modal.UserImageDao;
import com.web.objects.Login_Info;
import com.web.objects.UserImage;
import com.web.util.Dbmanager; 

public class AjaxProcess extends Generic 
{
	public  static AjaxProcess ref = new AjaxProcess();
	private static Connection conn = null;            
    private static PreparedStatement stmt = null; 
    private static ResultSet rs = null;
    
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
    	Generic.logContent(string , LoggerFactory.DEBUG,e, new AjaxProcess());
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
	
	public boolean setUserInfo(UploadJava upld) throws Exception
	{
		ret =false ;

		String userId = isNull(session.getAttribute("user_id").toString());
		
		Login_Info detail = new Login_Info();
		detail.setDob(request.getParameter("dob"));
		detail.setGender(request.getParameter("gender"));
		detail.setUser_id(userId);
		detail.setAltered_user(session.getAttribute("user").toString());
	
		logContent(detail.toString() , LoggerFactory.INFO, null);

		ret = new Logindao().updateUserInfo(detail)  ;
		
		if( upld.isUpdated() && ret )
		{

			logContent("ImagePath Of User "+detail.getUser_id(), LoggerFactory.DEBUG, null);

			UserImage ui = new UserImage();
			ui.setImage_name(upld.getFileName());
			ui.setImage_path("Images/"+userId);
			ui.setUploaded_by(userId);
			ui.setUser_id(userId);

			ret = new UserImageDao().insertUser(ui) ;
			
			if(ret)
			{
				session.setAttribute("ImagePath", ui.getImage_path()+seperator+ui.getImage_name());
			}
			
			logContent("User Image Info Updated ["+ret+"]" , LoggerFactory.INFO, null);
		}
		
		Generic.closeOpenConnections();
		
		return ret;
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
	
	public  String getFileName(final Part part) {  
        
		final String partHeader = part.getHeader("content-disposition");  
		
		logContent("partHeader "+partHeader,LoggerFactory.DEBUG, null);
         
        for (String content : part.getHeader("content-disposition").split(";")) 
        {  
            if (content.trim().startsWith("filename")) 
            {  
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");  
            }  
        }     
        return null;  
    }  
	
	public JSONObject setUserAcess() throws Exception
	{
		String user = isNull(request.getParameter("user"));
		String access = isNull(request.getParameter("id"));
		
		logContent("user ["+user+"] access ["+access+"]", LoggerFactory.INFO, null);
		
		JSONObject result = new JSONObject();
		
		if(user !="" && access !="")
		{
			try
			{
				conn = Dbmanager.getConnection();
				
				ps = conn.prepareStatement("update login_info set status = ? , role = ? where user_id =?");
				ps.setString(1, access.equals("Y") ? "N" : "Y");
				ps.setString(2, access.equals("Y") ? "Guest" :"Admin");
				ps.setString(3, user);
				
				logContent(ps, LoggerFactory.DEBUG, null);
				
				int count = ps.executeUpdate() ;
				
				logContent("updated " + (count > 0), LoggerFactory.DEBUG, null);
				
				if(count > 0 )
					result.put("status", access.equals("Y") ? "Pending" :"Approved");
				else
					result.put("status", access);
				
			}
			catch(Exception e)
			{
				logContent(e.getLocalizedMessage(), LoggerFactory.ERROR, e);
				result.put("status", access);
			}
		}
		return result ;
	}
	
	public boolean getUploadResponse() throws Exception
	{
		ret = false ;
		UploadJava upld = new UploadJava();
		upld.setHttpServlets(request, response);
		
		 ret = upld.uploadImage(null) ;
		 
		 if(ret)
		 {
			 ret = setUserInfo(upld);
		 }
		 
		 return ret ;
	}
	
}
