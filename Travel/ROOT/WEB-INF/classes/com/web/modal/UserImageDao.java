package com.web.modal;

import java.sql.SQLException;

import com.web.common.Generic;
import com.web.objects.UserImage;

public class UserImageDao extends Generic 
{
	boolean rowsAffected =false ;
	
	private static final String INSERT_USER ="INSERT INTO USER_IMAGE(user_id, image_name, image_path, uploaded_time, uploaded_by,type,size,object_url) VALUES(?,?,?,sysdate(),?,?,?,?)" ;
	private static final String CHECK_IMAGE =" select 'x' from USER_IMAGE where user_id = ? and image_name =? and image_path = ? and type =? and size = ?" ;
	
	public UserImageDao()
	{
		LogObject = this;
	}
	
	public boolean insertUser(UserImage d) throws SQLException
	{
		if( ! isImageExists(d) )
		{
			ps = con.prepareStatement(INSERT_USER);
			int cntl = 0;
			ps.setString(++cntl,d.getUser_id() );
			ps.setString(++cntl,d.getImage_name());
			ps.setString(++cntl,d.getImage_path() );
			ps.setString(++cntl,d.getUploaded_by() );
			ps.setString(++cntl,d.getType() );
			ps.setInt(++cntl,d.getSize() );
			ps.setString(++cntl,d.getObjectUrl() );
			
			logContent(ps,DEBUG , null, this);

			rowsAffected = ps.executeUpdate() > 0;

			logContent(" records affected "+rowsAffected,DEBUG , null, this);

		}

		return rowsAffected;
	}
	
	public boolean isImageExists(UserImage d) throws SQLException
    {
        ps = con.prepareStatement(CHECK_IMAGE);
        int cntl = 0;
        ps.setString(++cntl,d.getUser_id() );
        ps.setString(++cntl,d.getImage_name());
        ps.setString(++cntl,d.getImage_path() );
        ps.setString(++cntl,d.getType() );
        ps.setInt(++cntl,d.getSize() );
        
        logContent(ps,DEBUG , null, this);

        rs = ps.executeQuery();
        
        if(rs == null)
            return false;
        
        while ( rs.next() )
        {
        	logContent(" Image With Name "+d.getImage_name()+" already Exists ",INFO);
        	return true ;
        }
        
        return false;
    }
	
}
