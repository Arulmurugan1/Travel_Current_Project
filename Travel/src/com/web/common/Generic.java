package com.web.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;

import com.web.objects.AccessLog;
import com.web.util.Dbmanager;

public abstract class Generic 
{

    public static Connection con = Dbmanager.getConnection();
    public static PreparedStatement ps = null ;
    public static ResultSet rs = null ;

    public Generic()
    {
        setConnection();
    }
    public void closeAll() throws Exception
    {

        String connectionInfo = "";

        if ( con !=null && !con.isClosed() ) 
        {
            con.close();
            connectionInfo+= " Connection ["+con.isClosed()+"] ";
        }

        if ( ps !=null && !ps.isClosed() ) 
        {
            ps.close();
            connectionInfo+=  " Statement ["+ps.isClosed()+"] ";
        }

        if ( rs !=null && !rs.isClosed() ) 
        {
            rs.close();
            connectionInfo+=  " ResultSet ["+rs.isClosed()+"]";
        }
        System.out.println(getClass().getSimpleName() + connectionInfo );
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

    public static void logContent(String message, Level logLevel, Exception e) 
    {
        LoggerFactory.displayDiffLogLevels(message, logLevel, e,Object.class);
    }

    public static void setConnection()
    {
        try {
            if ( con == null || con.isClosed() )
            {
                con = Dbmanager.getConnection();
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
