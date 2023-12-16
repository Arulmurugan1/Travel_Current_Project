package com.web.modal;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import com.web.common.CommonFactory;
import com.web.common.Generic;
import com.web.objects.Login_Info;
import com.web.util.Dbmanager;

public class Logindao extends Generic
{
    private static final String UPDATE_USERS_TSTAMP ="UPDATE login_info SET LAST_LOGIN=sysdate() WHERE USER_ID=?";
    private static final String UPDATE_USERS_INFO       ="UPDATE login_info SET gender=?,dob=STR_to_date(?,'%Y-%m-%d'),altered_user=? WHERE USER_ID=?";
    private static final String DELETE_USERS ="DELETE FROM login_info WHERE USER_ID=?";
    private static final String CHECK_USER   ="SELECT li.*,concat(ui.image_path,'/',ui.image_name) image_path FROM login_info li LEFT OUTER JOIN user_image ui ON li.user_id = ui.user_id WHERE li.USER_ID=?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM login_info  order by last_login desc";
    
    public Logindao()
	{
		LogObject = this;
	}

    public boolean insertUser(Login_Info user)throws SQLException, IllegalArgumentException, IllegalAccessException
    {
        boolean rowsaffected = false;
        ps =con.prepareStatement( Dbmanager.buildQuery(user, "insert", "image_path") ) ;
        rowsaffected=ps.executeUpdate() > 0 ;
        return rowsaffected;
    }
    
    
    public boolean updateUserLoginTtsamp(Login_Info user)throws SQLException
    {

        boolean rowsaffected = false;
        ps =con.prepareStatement(UPDATE_USERS_TSTAMP) ;
        ps.setString(1 , user.getUser_id()) ;
        logContent(ps,DEBUG , null, this);
        rowsaffected = ps.executeUpdate() > 0 ;

        return rowsaffected;
    }
    public boolean updateUserInfo(Login_Info user)throws SQLException,Exception
    {
        ps =con.prepareStatement(UPDATE_USERS_INFO) ;
        ps.setString(1 , user.getGender()) ;
        ps.setString(2 , user.getDob()) ;
        ps.setString(3 , user.getAltered_user()) ;
        ps.setString(4 , user.getUser_id()) ;
        
        logContent(ps,DEBUG , null, this);

        return ps.executeUpdate() > 0 ;
    }
    public boolean deleteUser(String id)throws SQLException
    {

        boolean rowsaffected = false;
        ps =con.prepareStatement(DELETE_USERS) ;
        ps.setString(1,id);
        logContent(ps,DEBUG , null, this);
        rowsaffected=ps.executeUpdate()>0;
        return rowsaffected;
    }

    public Login_Info selectUser(String id) throws SQLException
    {

        Login_Info u =new Login_Info();
        ps =con.prepareStatement(CHECK_USER);
        ps.setString(1,id);
        logContent(ps,DEBUG , null, this);
        rs =ps.executeQuery();

        while(rs.next()) {
            u.setUsername(rs.getString("username"));
            u.setPassword2(rs.getString("password2"));
            u.setPassword(rs.getString("password1"));
            u.setRole(rs.getString("role"));
            u.setUser_id(rs.getString("user_id"));
            u.setLast_login((LocalDateTime) rs.getObject("last_login"));
            u.setDob(rs.getString("dob"));
            u.setGender(rs.getString("gender"));
            u.setStatus(rs.getString("status"));
            u.setImage_path(rs.getString("image_path"));
        }
        return u;  
    }
    public Vector<Object> getAllUsers()throws SQLException
    {
        Vector<Object> users = new Vector<>();

        ps =con.prepareStatement(SELECT_ALL_USERS);

        rs = ps.executeQuery();
        
        

        while(rs.next() )
        {
            if ( users.size() == 0 )
            {
                users.add(getColumnNames().clone());
                users.add(getUsers().clone());
            }
            else
            {
                users.add(getUsers().clone());
            }
        }
        return users;
    }
    
    public static Vector<String> getColumnNames()
    {
        Vector<String> columns = new Vector<String>();

        try
        {
            for ( int index = 1 ; index <=  rs.getMetaData().getColumnCount() ; index++ )
            {
                try
                {
                    columns.add( CommonFactory.Init(rs.getMetaData().getColumnName(index).toLowerCase()) );
                }
                catch(Exception e)
                {
                    columns.add("");
                }
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return columns; 
    }
    
    public static Vector<Object> getUsers()
    {
        Vector<Object> columns = new Vector<Object>();

        try
        {

            for ( int index = 1 ; index <=  rs.getMetaData().getColumnCount() ; index++ )
            {
                try
                {
                    if( rs.getMetaData().getColumnTypeName(index).equals("DATETIME"))
                    {
                        columns.add( ((LocalDateTime) rs.getObject(index)).format(DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm:ss a")).toString() );
                    }
                    else if (rs.getMetaData().getColumnName(index).equals("dob") )
                    {
                        String[] Date = rs.getString(index).split("-");

                        if ( Date.length == 3)
                        {

                            LocalDate date = LocalDate.of(Integer.parseInt(Date[0]),Integer.parseInt(Date[1]),Integer.parseInt(Date[2]));

                            Period diff = Period.between( date, LocalDate.now() );

                            columns.add(diff.getYears() +" yrs "+diff.getMonths()+" mon "+diff.getDays()+" days ");
                        }
                        else
                            columns.add(" ");
                    }
                    else
                    {
                        columns.add(rs.getString(index));
                    }
                }
                catch(Exception e)
                {
                    columns.add("");
                    e.printStackTrace();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return columns ;
    }
}
