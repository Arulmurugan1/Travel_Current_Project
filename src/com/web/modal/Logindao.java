package com.web.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.web.objects.Login;
import com.web.util.Dbmanager;

public class Logindao {
    private static final String INSERT_USERS ="INSERT INTO login_info VALUES(?,?,?,?,?,?,?,?,sysdate(),sysdate())";
    private static final String UPDATE_USERS ="UPDATE login_info SET LAST_LOGIN=sysdate() WHERE USER_ID=?";
    private static final String DELETE_USERS ="DELETE FROM login_info WHERE USER_ID=?";
    private static final String CHECK_USER   ="SELECT * FROM login_info WHERE USER_ID=?";
    private static final String SELECT_USERS_BY_ID ="SELECT USERNAME,PASSWORD1,ROLE,USER_ID,LAST_LOGIN FROM login_info WHERE USER_ID=?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM login_info";

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
        public List<Login> getAllUsers()throws SQLException
        {
            List<Login> u = new ArrayList<>();

            Login u1 =null;
            ps =con.prepareStatement(SELECT_ALL_USERS);
            System.out.println(ps);
            rs =ps.executeQuery();
            while(rs.next()) {
                u1 = new Login();
                u1.setUsername(rs.getString(1));
                u1.setPassword(rs.getString(2));
                u1.setRole(rs.getString(3));
                u1.setCreate_time(rs.getString(4));
                u1.setUser_id(rs.getString(5));
                u1.setCreated_user(rs.getString(6));
                u.add(u1);
            }
            return u;
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
