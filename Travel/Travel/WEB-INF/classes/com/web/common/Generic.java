package com.web.common;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;

import com.web.objects.AccessLog;
import com.web.util.Dbmanager;

public class Generic extends StringChecker
{

	public static Connection con = Dbmanager.getConnection();
	public static PreparedStatement ps = null ;
	public static ResultSet rs = null ;
	public static Generic generic = new Generic();
	HttpSession session = null ;
	HttpServletRequest request = null  ;
	boolean ret = false ;

	private static PrintStream printStream = null;

	public Generic()
	{
		setConnection();
	}

	public Generic(HttpServletRequest request) throws Exception 
	{
		this.session = request.getSession();
		this.request = request ;
	}

	public boolean setLogConsoleProperties() throws Exception 
	{
		ret = false ;
		System.out.println("Inside Log Path from " +this.getClass().getSimpleName());
		
		if( setProperties("log_user",session.getAttribute("user_id").toString()))
			ret = setProperties();
		
		ret = ret && setSystemOutLogs();
		
		return ret ;
	}

	public void closeAll() throws Exception
	{
		Dbmanager.closeAll(con, ps, rs);
	}

	public static void closeAll(Connection con,PreparedStatement ps , ResultSet rs,Object object) throws Exception
	{
		Dbmanager.closeAll(con, ps, rs);
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

		ps =con.prepareStatement(Dbmanager.buildQuery(al, INSERT));

		logContent("Access Log Entered "+( ps.executeUpdate() > 0 ) ,LoggerFactory.DEBUG , null );

	} 

	public static void logContent(Object message, Level logLevel, Throwable e, Object object) 
	{
		LoggerFactory.displayDiffLogLevels(message != null ? message.toString() : "", logLevel, e,object);
	}
	public static void logContent(Object message, Level logLevel, Throwable e) 
	{
		LoggerFactory.displayDiffLogLevels(message != null ? message.toString() : "", logLevel, e,generic);
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

	protected Throwable getRootException(Throwable exception)
	{
		Throwable rootException= exception;

		while(rootException.getCause()!=null)
		{
			rootException = rootException.getCause();
		}

		logContent("rootException =>"+rootException, LoggerFactory.DEBUG, rootException, this);

		return rootException;
	}

	@SuppressWarnings("unused")
	private void setLogAppenders(HttpSession session) 
	{

		try
		{

			if ( session.getAttribute("loggerlayout") == null )
			{
				String filePath = session.getAttribute("loggerFilePath").toString();

				//Set Layout 

				Layout layout = new PatternLayout("[%d{dd-MM-yy hh:mm:ss:sss a z}] %-5p %-2c{1}[Line:%-2L] - %m %n");

				//Set Appender 

				Appender appender = new FileAppender(layout, filePath + "CommonLogs.log");

				session.setAttribute("loggerlayout", layout);
				session.setAttribute("loggerappender", appender);
			}

		}
		catch(Exception e)
		{
			logContent(e.getMessage() ,LoggerFactory.ERROR , null, this );
		}

	}

	private boolean setProperties() throws Exception 
	{
		ret = false ;

		String url = Dbmanager.getPropertiesPath(LOG_PATH);

		String folder = "Logs".concat( seperator + LocalDate.now().toString().replaceAll( "-", seperator ))+seperator   ;

		System.out.println("Inside setProperties() Log Path ["+ url +"] folder ["+folder+"]");
		
		if(url.trim().equals(""))
		{
			URL fileURL = currentThread().getContextClassLoader().getResource(LOG_PROPERTIES) ;

			url = URLDecoder.decode(isNull(fileURL), "UTF-8") ;

			if( !url.isEmpty())
			{
				int endString = url.lastIndexOf("Travel") ;
				url = url.substring(url.lastIndexOf(":")+1, endString != -1 ? endString : url.length() ) +folder;
			}
		}
		else
		{
			url += folder;
		}

		System.out.println("Log will be redirected to "+url);

		if( setProperties("log",url)  )
			ret = setConsoleFilePath();

		return ret ;

	}

	private boolean setConsoleFilePath() throws Exception
	{
		ret = false ;

		logContent(" Console File Path ["+session.getAttribute("consoleFilePath")+"]",LoggerFactory.DEBUG,null);
		
		if ( session.getAttribute("consoleFilePath") == null )
		{	
			String logPath = Dbmanager.getKeyProperties("log");

			String consoleFileName =  System.currentTimeMillis() + CONSOLE_FILENAME;

			String loggerFilePath = logPath + LOG_FOLDERNAME    ;

			logContent("Log File Path =>"+loggerFilePath,LoggerFactory.DEBUG,null);
			
			File f = new File(loggerFilePath);
			
			if(!f.exists())
				f.mkdirs();

			session.setAttribute("consoleFileName", consoleFileName);
			session.setAttribute("loggerFilePath", loggerFilePath);
			
			logContent(" File Path Added to session ",LoggerFactory.DEBUG,null);
			
			ret = true ;
		}
		
		return ret ;
	}

	
	public boolean setSystemOutLogs() throws Exception
	{
		ret = false ;
		
		boolean isLog = isNull(Dbmanager.getKeyProperties(IS_SYSOUT_LOGGABLE)).equalsIgnoreCase("Y");
		
		logContent(" Console File Path ["+session.getAttribute("ConsoleLog")+"] isLog ["+isLog+"] ",LoggerFactory.DEBUG,null);
		
		if(isLog)
		{
			if (session.getAttribute("ConsoleLog") == null )
			{
				try 
				{
					String filePath = Dbmanager.getKeyProperties("log").concat(CONSOLE_FOLDERNAME) + seperator
							, fileName = session.getAttribute("consoleFileName").toString();

					logContent("Inside setSystemOutLogs filePath["+filePath+"] fileName ["+fileName+"]",LoggerFactory.DEBUG,null);
					
					File f = new File(filePath.concat(fileName));
					
					if ( f.exists() ) 
					{   
						logContent("File exists " + f.exists() + " File Name :" + f.getName(),LoggerFactory.DEBUG,null);
					} 
					else 
					{
						if ( !f.getParentFile().exists() )
						{
							f.getParentFile().mkdirs();
						}

						if ( f.createNewFile() && f.exists() ) 
						{
							logContent("File created " + f.exists() + " File Name :" + f.getName(),LoggerFactory.DEBUG,null);
						} 
						else 
						{
							new Throwable("File Creation Issue");
						}
					}
					session.setAttribute( "ConsoleLog", new PrintStream( f.getAbsolutePath() ) );
				} 
				catch (Exception e) 
				{
					logContent("Exception in File Path Creation" ,LoggerFactory.DEBUG,e,null);
					throw e ;
				}
			}
			
			printStream =  (PrintStream) session.getAttribute("ConsoleLog");

			if ( printStream != null )
			{
				System.setOut(printStream);
			}
			logContent("Get PrintStream [" + printStream +"]",LoggerFactory.DEBUG,null );

			ret = session.getAttribute("ConsoleLog") != null ;

			}
		
		return ret ;
	}
	
	
	private boolean setProperties(String key, String value) throws Exception 
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

}
