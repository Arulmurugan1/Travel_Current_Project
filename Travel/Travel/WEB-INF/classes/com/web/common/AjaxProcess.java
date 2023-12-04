package com.web.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
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
            logContent("Inside getInfo for Vehicle No call from ajax" , INFO, null);
            conn = Dbmanager.getConnection();
            stmt = conn.prepareStatement("Select vehicle_no from vehicle where vehicle_no =?");
            stmt.setString(1, vehicleNo.trim());
            rs = stmt.executeQuery(); 
            

            while(rs.next())
            { 
                sql = rs.getString(1);
                logContent("vehicle_no => "+sql , INFO,null);
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
    	Generic.logContent(string , DEBUG,e, new AjaxProcess());
	}

	public JSONArray getVehicleType(String model)
    {
        JSONArray obj = new JSONArray();

        try {    
            logContent("Inside VehicleType for Vehicle Model call from ajax" , DEBUG, null);
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
            logContent("", ERROR, e);
        }
        return  ( obj.toString().length() > 0 ) ? obj : null ;
    }
	
	public boolean setUserInfo(UploadJava upld) throws SQLException
	{
		ret =false ;

		String userId = isNull(session.getAttribute("user_id"));
		
		Login_Info detail = new Login_Info();
		
		detail.setDob(request.getParameter("dob"));
		detail.setGender(request.getParameter("gender"));
		detail.setUser_id(userId);
		detail.setAltered_user(session.getAttribute("user").toString());
	
		logContent(detail , INFO);

		ret = new Logindao().updateUserInfo(detail)  ;
		
		if( upld.isUpdated() && ret )
		{
			
			if(ret = updateUserImage(userId).equals("") )
			{
				logContent("User Image Info Updated ["+ret+"]" , INFO);
			}
			
		}
		
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
		
		logContent("partHeader "+partHeader,DEBUG, null);
         
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
		boolean access = isNull(request.getParameter("id")).equals("Y");
		
		logContent("user ["+user+"] access ["+access+"]", INFO, null);
		
		JSONObject result = new JSONObject();
		
		if(user !="")
		{
			try
			{
				conn = Dbmanager.getConnection();
				
				ps = conn.prepareStatement("update login_info set status = ? , role = ? where user_id =?");
				ps.setString(1, access ? "N" : "Y");
				ps.setString(2, access ? "Guest" :"Admin");
				ps.setString(3, user);
				
				logContent(ps, DEBUG, null);
				
				int count = ps.executeUpdate() ;
				
				logContent("updated " + (count > 0), DEBUG, null);
				
				if(count > 0 )
					result.put("status", access ? "Pending" :"Approved");
				else
					result.put("status", access);
				
			}
			catch(Exception e)
			{
				logContent(e.getLocalizedMessage(), ERROR, e);
				result.put("status", access);
			}
		}
		return result ;
	}
	
	public boolean getUploadResponse(HttpServletRequest request)throws Exception
	{
		ret = false ;
		
		/*
		 * UploadJava upld = new UploadJava();
		 * 
		 * ret = upld.uploadImage(request) ;
		 */
		 
	 ret = updateUserImage( isNull(session.getAttribute("user_id")) ).equals("");
		 
	return ret ;
	}
	
	public String updateUserImage(String userId) throws SQLException
	{
		
		ret = false ;
		
		if( userId == null)
		{
			userId = isNull(session.getAttribute("user_id")) ;
		}
		
		logContent("ImagePath Of User "+userId, DEBUG);

		UserImage ui = new UserImage();
		ui.setImage_name(request.getParameter("fileName"));
		ui.setImage_path("Images/profileImages/"+userId);
		ui.setUploaded_by(userId);
		ui.setUser_id(userId);
		ui.setSize( Integer.parseInt(isNull(request.getParameter("fileSize")))) ;
		ui.setType(request.getParameter("fileType")) ;
		
		UserImageDao uim = new UserImageDao() ;

		if( ! uim.isImageExists(ui))
		{
			if( ret = uim.insertUser(ui) )
				session.setAttribute("ImagePath", ui.getImage_path()+seperator+ui.getImage_name());
		}
		else
		{
			return " Image with name "+ui.getImage_name()+" already exists " ;
		}
		
		return "" ;
	}
	
}
