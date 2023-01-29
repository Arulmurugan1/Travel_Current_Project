package com.web.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.web.common.InitString;
import com.web.objects.AccessLog;
import com.web.objects.Login_Info;
import com.web.util.Dbmanager;

public class Logindao{
    private static final String UPDATE_USERS_TSTAMP ="UPDATE login_info SET LAST_LOGIN=sysdate() WHERE USER_ID=?";
    private static final String UPDATE_USERS_INFO       ="UPDATE login_info SET gender=?,dob=STR_to_date(?,'%Y-%m-%d'),altered_user=? WHERE USER_ID=?";
    private static final String DELETE_USERS ="DELETE FROM login_info WHERE USER_ID=?";
    private static final String CHECK_USER   ="SELECT * FROM login_info WHERE USER_ID=?";
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

    public boolean insertUser(Login_Info user)throws SQLException, IllegalArgumentException, IllegalAccessException
    {
        boolean rowsaffected = false;
        ps =con.prepareStatement( Dbmanager.buildQuery(user, "insert") ) ;
        rowsaffected=ps.executeUpdate() > 0 ;
        return rowsaffected;
    }
    
    
    public boolean updateUserLoginTtsamp(Login_Info user)throws SQLException
    {

        boolean rowsaffected = false;
        ps =con.prepareStatement(UPDATE_USERS_TSTAMP) ;
        ps.setString(1 , user.getUser_id()) ;
        System.out.println(ps);
        rowsaffected = ps.executeUpdate() > 0 ;

        return rowsaffected;
    }
    public boolean updateUserInfo(Login_Info user)throws SQLException
    {

        boolean rowsaffected = false;
        ps =con.prepareStatement(UPDATE_USERS_INFO) ;
        ps.setString(1 , user.getGender()) ;
        ps.setString(2 , user.getDob()) ;
        ps.setString(3 , user.getAltered_user()) ;
        ps.setString(4 , user.getUser_id()) ;
        
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

    public Login_Info selectUser(String id) throws SQLException
    {

        Login_Info u =new Login_Info();
        ps =con.prepareStatement(CHECK_USER);
        ps.setString(1,id);
        System.out.println(ps);
        rs =ps.executeQuery();

        while(rs.next()) {
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password1"));
            u.setRole(rs.getString("role"));
            u.setUser_id(rs.getString("user_id"));
            u.setLast_login((LocalDateTime) rs.getObject("last_login"));
            u.setDob(rs.getString("dob"));
            u.setGender(rs.getString("gender"));
            u.setStatus(rs.getString("status"));
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
        }
        if ( ps !=null && !ps.isClosed())
        {
            ps.close();
        }
        if ( rs !=null  && !rs.isClosed())
        {
            rs.close();
        }
        System.out.println("Connection Closed ["+con.isClosed()+"] Statement Closed ["+ps.isClosed()+"] Resultset Closed ["+rs.isClosed()+"]");
    }

    public static void InsertAccessLog(HttpServletRequest req) throws SQLException, IllegalArgumentException, IllegalAccessException 
    {
        AccessLog al = new AccessLog();

        al.setUser_id(req.getSession().getAttribute("user_id")+"");
        al.setUsername(req.getSession().getAttribute("user")+"");
        al.setRole(req.getSession().getAttribute("role")+"");
        al.setProtocol(req.getProtocol());
        al.setUrl(req.getRequestURL()+"");
        al.setRemote_host(req.getRemoteHost()+"");
        al.setRemote_address(req.getRemoteAddr()+"");
        al.setLocal_address(req.getLocalAddr()+"");
        al.setLocal_name(req.getLocalName()+"");
        al.setLocal_lang(req.getLocale()+"");
        al.setLogged_time(req.getSession().getAttribute("timeStamp")+"");
        al.setAccess_time(LocalDateTime.now());
        al.setPlatform(req.getHeader("sec-ch-ua-platform")+"");
        al.setAccept_language(req.getHeader("accept-language")+"");

        String InsertQuery = Dbmanager.buildQuery(al, "insert");
        ps =con.prepareStatement(InsertQuery);
        System.out.println("Access Log Entered "+( ps.executeUpdate() > 0 ) );

    } 
    
}
