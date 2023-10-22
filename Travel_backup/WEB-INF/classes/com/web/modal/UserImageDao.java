package com.web.modal;

import com.web.common.Generic;
import com.web.common.LoggerFactory;
import com.web.objects.UserImage;

public class UserImageDao extends Generic 
{
	boolean rowsAffected =false ;
	
	private static final String INSERT_USER ="INSERT INTO USER_IMAGE VALUES(?,?,?,sysdate(),?)" ;
	
	public boolean insertUser(UserImage d)throws Exception
    {
        ps = con.prepareStatement(INSERT_USER);
        int cntl = 0;
        ps.setString(++cntl,d.getUser_id() );
        ps.setString(++cntl,d.getImage_name());
        ps.setString(++cntl,d.getImage_path() );
        ps.setString(++cntl,d.getUploaded_by() );

        logContent(ps,LoggerFactory.DEBUG , null, this);

        rowsAffected = ps.executeUpdate() > 0;
       
        return rowsAffected;
    }
	
}
