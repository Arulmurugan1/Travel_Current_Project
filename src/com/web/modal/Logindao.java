package com.web.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;

import com.web.common.InitString;
import com.web.objects.Login;
import com.web.util.Dbmanager;

public class Logindao{
    private static final String INSERT_USERS ="INSERT INTO login_info VALUES(?,?,?,?,?,?,?,?,sysdate(),sysdate())";
    private static final String UPDATE_USERS ="UPDATE login_info SET LAST_LOGIN=sysdate() WHERE USER_ID=?";
    private static final String DELETE_USERS ="DELETE FROM login_info WHERE USER_ID=?";
    private static final String CHECK_USER   ="SELECT * FROM login_info WHERE USER_ID=?";
    private static final String SELECT_USERS_BY_ID ="SELECT USERNAME,PASSWORD1,ROLE,USER_ID,LAST_LOGIN FROM login_info WHERE USER_ID=?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM login_info  order by last_login desc";

    static Connection con =null;
    static PreparedStatement ps = null ;
    static ResultSet rs = null ;

    public Logindao()
    {
        con = Dbmanager.getConnection();
        if ( con != null)
            System.out.println("Login Connection created");
    }

    public boolean insertUser(Login user)throws SQLException
    {

        boolean rowsaffected = false;
        int c =1 ;
        ps =con.prepareStatement(INSERT_USERS);
        ps.setString(c++, user.getUser_id());
        ps.setString(c++, user.getUsername());
        ps.setString(c++, user.getPassword());
        ps.setString(c++, " ");
        ps.setString(c++, " ");
        ps.setString(c++, " ");
        ps.setString(c++, "Guest");			
        ps.setString(c++, "Others");

        System.out.println(ps);
        rowsaffected=ps.executeUpdate() > 0 ;


        return rowsaffected;
    }

    public boolean updateUser(Login user)throws SQLException
    {

        boolean rowsaffected = false;
        ps =con.prepareStatement(UPDATE_USERS) ;
        ps.setString(1 , user.getUser_id()) ;
        System.out.println(ps);
        rowsaffected = ps.executeUpdate() > 0 ;

        return rowsaffected;
    }

    public boolean deleteUser(String id)throws SQLException
    {

        boolean rowsaffected = false;
        ps =con.prepareStatement(DELETE_USERS) ;
        ps.setString(1,id);
        System.out.println(ps);
        rowsaffected=ps.executeUpdate()>0;
        return rowsaffected;
    }

    public Login selectUser(String id) throws SQLException
    {

        Login u =new Login();
        ps =con.prepareStatement(SELECT_USERS_BY_ID);
        ps.setString(1,id);
        System.out.println(ps);
        rs =ps.executeQuery();

        while(rs.next()) {
            u.setUsername(rs.getString(1));
            u.setPassword(rs.getString(2));
            u.setRole(rs.getString(3));
            u.setUser_id(rs.getString(4));
            u.setLast_login((LocalDateTime) rs.getObject(5));
        }
        return u;  
    }
    public Vector<Object> getAllUsers()throws SQLException
    {
        Vector<String> v1 = new Vector<>();
        Vector<String> v3 = new Vector<>();
        Vector<Object> v2 = new Vector<>();

        ps =con.prepareStatement(SELECT_ALL_USERS,ResultSet.TYPE_FORWARD_ONLY);

        rs = ps.executeQuery();

        int columnCount = rs.getMetaData().getColumnCount();

        int index = 0 ;

        while ( rs.next() )
        {
            if ( index == 0 )
            {
                for ( int i1 = 1 ; i1 <= columnCount ; i1++ )
                {
                    v3.add( InitString.Init(rs.getMetaData().getColumnName(i1).toLowerCase()) );
                }
                v2.addElement(v3.clone());
            }
            for ( int i = 1 ; i <= columnCount ; i++ )
            {
                if( rs.getMetaData().getColumnTypeName(i).equals("DATETIME"))
                {
                    v1.add( ((LocalDateTime) rs.getObject(i)).format(DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm:ss a")) );
                }
                else if (rs.getMetaData().getColumnName(i).equals("dob") )
                {
                    String[] Date = rs.getString(i).split("-");
                    
                    LocalDate date = LocalDate.of(Integer.parseInt(Date[0]),Integer.parseInt(Date[1]),Integer.parseInt(Date[2]));
                    
                    Period diff = Period.between( date, LocalDate.now() );
                    
                    v1.add(diff.getYears() +" yrs "+diff.getMonths()+" mon "+diff.getDays()+" days ");
                    
                }
                else
                {
                    v1.add(rs.getString(i));
                }
                
            }
            v2.addElement(v1.clone());
            v1.clear();
            index++;
        }
        return v2;
    }
    public boolean checkUser(String id) throws SQLException
    {

        boolean rowsaffected = false;
        System.out.println(CHECK_USER);
        ps =con.prepareStatement(CHECK_USER);
        ps.setString(1,id);
        System.out.println(ps);
        rs = ps.executeQuery();
        if( rs.next() ) {
            rowsaffected=true;
        }


        return rowsaffected;
    }
    public void closeAll() throws Exception
    {
        if ( con !=null && !con.isClosed())
        {
            con.close();
            con = null;
            System.out.println("Connection Closed ::"+con);
        }
        if ( ps !=null )
        {
            ps.close();
            ps = null;
            System.out.println("Statement Closed ::"+ps);
        }
        if ( rs !=null  )
        {
            rs.close();
            rs = null;
            System.out.println("Resultset Closed ::"+rs);
        }
    }
}
