package com.web.common;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.log4j.LogContent;
import com.web.objects.AccessLog;
import com.web.util.Dbmanager;

public class Generic extends LogContent
{

	public static Connection con 						= null ;
	public static PreparedStatement ps 				= null ;
	public static ResultSet rs 								= null ;
	public static Object LogObject 						= new Generic();
	public static HttpSession session 					= null ;
	public static HttpServletRequest request 		= null  ;
	public static HttpServletResponse response = null ;

	PrintWriter out 												= null ;
	boolean ret 													= false ;

	public Generic()
	{
		try
		{
			setConnection();
		}
		catch(Exception e)
		{
			Dbmanager.error = e.toString() ;
			logContent(e, ERROR, e);
		}
	}

	public Generic(HttpServletRequest req) throws Exception 
	{
		this();
		setRequest(req,req.getSession());
	}

	public static void setRequest(HttpServletRequest req,HttpSession ses) throws Exception 
	{
		session = ses;
		request = req ;
	}

	public void closeAll() throws Exception
	{
		Dbmanager.closeAll(con, ps, rs,"Generic closeAll");
	}

	public static void closeOpenConnections() throws SQLException
	{
		Dbmanager.closeAll(con, ps, rs,"closeOpenConnections");
	}

	public static void closeAll(Connection con,PreparedStatement ps , ResultSet rs,Object object) throws Exception
	{
		Dbmanager.closeAll(con, ps, rs,"Object Connection");
	}

	public static void InsertAccessLog(HttpServletRequest req) throws IllegalArgumentException, IllegalAccessException, SQLException 
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

		ps =con.prepareStatement(Dbmanager.buildQuery(al, INSERT, null));

		logContent("Access Log Entered "+( ps.executeUpdate() > 0 ) ,DEBUG , null );

	} 

	public static void setConnection() throws Exception
	{
		if ( con == null || con.isClosed() )
		{
			con = Dbmanager.getConnection();
		}
	}
	protected Throwable getRootException(Throwable exception)
	{
		Throwable rootException= exception;

		while(rootException.getCause()!=null)
		{
			rootException = rootException.getCause();
		}

		logContent("rootException =>"+rootException, DEBUG, rootException, this);

		return rootException;
	}

	public synchronized boolean setProperties(String key, String value) throws Exception 
	{
		return Dbmanager.setProperties(key, value);
	}

	public static StringWriter getRootCauseException(Throwable e)
	{
		StringWriter sw = new StringWriter();

		PrintWriter pw = new PrintWriter(sw);

		if( e !=null)
			e.printStackTrace(pw);

		return sw ;
	}

	public static  boolean createFolders(String url)
	{
		boolean ret = false ;

		if(!isBlankCheck(url))
		{
			File f = new File(url);
			
			if ( ret = f.exists()   )
			{
				System.out.println("FilePath available ["+f.getAbsolutePath()+"]");
			}
			else if (  ret = f.mkdirs()  )
			{
				System.out.println("FilePath Created ["+f.getAbsoluteFile() +"]");
			}

		}
		else
			System.out.println("FilePath ["+url+"] is null or empty");


		return ret ;

	}

	public static  boolean createFiles(String url) throws Exception
	{
		boolean ret = false ;

		if(! isBlankCheck(url))
		{
			File f = new File(url);

			if ( ret = f.exists()   )
			{
				System.out.println("File available ["+f.getAbsoluteFile()+"]" );
			}
			else if (  ret = f.createNewFile()  )
			{
				System.out.println("File Created ["+f.getAbsoluteFile()+"] created ");
			}

		}
		else
			System.out.println("FilePath  ["+url+"] is null or empty");

		return ret ;

	}

	public static void setHttpServlets(HttpServletResponse res,HttpServletRequest req)
	{
		session = req.getSession() ;
		request = req ;
		response = res ;
	}

}
