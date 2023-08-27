package com.web.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;

import com.web.objects.AccessLog;
import com.web.util.Dbmanager;

public class Generic extends CommonFactory
{

	public static Connection con = Dbmanager.getConnection();
	public static PreparedStatement ps = null ;
	public static ResultSet rs = null ;
	public static Generic generic = new Generic();
	public static HttpSession session = null ;
	public static HttpServletRequest request = null  ;
	public static HttpServletResponse response = null ;
	
	private String LogUrl = null ;

    PrintWriter out = null ;
	boolean ret = false ;

	private static PrintStream printStream = null;

	public Generic()
	{
		setConnection();
	}

	public Generic(HttpServletRequest req) throws Exception 
	{
		setRequest(req,req.getSession());
	}
	
	public static void setRequest(HttpServletRequest req,HttpSession ses) throws Exception 
	{
		session = ses;
		request = req ;
	}

	public boolean setLogConsoleProperties() throws Exception 
	{
		ret = false ;
		
		System.out.println("Inside Log Path from " +this.getClass().getSimpleName() +LOG_USER +"["+isNull(session.getAttribute("user_id"))+"]");
		
		ret = setProperties();
		
		ret = ret && setSystemOutLogs();
		
		return ret ;
	}

	public void closeAll() throws Exception
	{
		Dbmanager.closeAll(con, ps, rs);
	}
	
	public static void closeOpenConnections() throws Exception
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

		ps =con.prepareStatement(Dbmanager.buildQuery(al, INSERT, null));

		logContent("Access Log Entered "+( ps.executeUpdate() > 0 ) ,LoggerFactory.DEBUG , null );

	} 

	/**
	 * @param message
	 * @param logLevel
	 * @param e
	 * @param className
	 */
	public static void logContent(Object message, Level logLevel, Throwable e, Object className) 
	{
		LoggerFactory.displayDiffLogLevels(message != null ? message.toString() : "", logLevel, e,className,session);
	}
	/**
	 * @param message
	 * @param logLevel
	 * @param e
	 */
	public static void logContent(Object message, Level logLevel, Throwable e) 
	{
		LoggerFactory.displayDiffLogLevels(message != null ? message.toString() : "", logLevel, e,generic,session);
	}
	
	/**
	 * @param message
	 * @param logLevel
	 * @param e
	 * @param className
	 * @param session
	 */
	public static void logContent(String message, Level logLevel, Throwable e, Object className,HttpSession session) 
	{
		LoggerFactory.displayDiffLogLevels(message != null ? message.toString() : "", logLevel, e,className,session);
	}
	public static void setConnection()
	{
		try {
			if ( con == null || con.isClosed() )
			{
				con = Dbmanager.getConnection();
			}
		} catch (SQLException e) {

			logContent("setConnection Exception =>"+e.getLocalizedMessage(), LoggerFactory.DEBUG, e);
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
			
			throw new FileNotFoundException("Log Path Not found in PROPERTIES_PATH table");
			/*
			 * URL fileURL =
			 * currentThread().getContextClassLoader().getResource(LOG_PROPERTIES) ;
			 * 
			 * url = URLDecoder.decode(isNull(fileURL), "UTF-8") ;
			 * 
			 * if( !url.isEmpty()) { int endString = url.lastIndexOf("Travel") ; url =
			 * url.substring(url.lastIndexOf(":")+1, endString != -1 ? endString :
			 * url.length() ) +folder; }
			 */
		}
		else
		{
			url += folder;
		}
		
		LogUrl = url ;
		
		System.out.println("Log will be redirected to "+url);

		if( setProperties(LOG_USER,isNull(session.getAttribute("user_id"))) && setProperties("log",url)  )
			ret = setConsoleFilePath(url);

		return ret ;

	}

	private boolean setConsoleFilePath(String url) throws Exception
	{
		ret = false ;

		logContent(" Console File Path ["+session.getAttribute("consoleFilePath")+"]",LoggerFactory.DEBUG,null);
		
		if ( session.getAttribute("consoleFilePath") == null )
		{	
			String logPath = url;

			String consoleFileName =  System.currentTimeMillis() + CONSOLE_FILENAME;

			String loggerFilePath = logPath + LOG_FOLDERNAME + seperator   ;
			
			String consoleFilePath = logPath + CONSOLE_FOLDERNAME + seperator   ;

			ret = createFolders(loggerFilePath) && createFiles(loggerFilePath+LOG_FILENAME) && createFolders(consoleFilePath);
			
			session.setAttribute("consoleFileName", consoleFileName);
			session.setAttribute("loggerFilePath", loggerFilePath);
			session.setAttribute("consoleFilePath", consoleFilePath);
			
			logContent(" File Path Added to session ",LoggerFactory.DEBUG,null);
		}
		
		return ret ;
	}

	public static  boolean createFolders(String url)
	{
		boolean ret = false ;
		
		if(url !=null && !url.trim().equals(""))
		{
			File f = new File(url);
			
			ret = f.exists() || f.mkdirs();
			
			System.out.println("FilePath ["+f.getAbsolutePath()+"] available ["+ret+"]");
			
		}
		else
			System.out.println("FilePath ["+url+"] is null or empty");
		
		return ret ;
		
	}
	
	public static  boolean createFiles(String url) throws Exception
	{
		boolean ret = false ;
		
		if(url !=null && !url.trim().equals(""))
		{
			File f = new File(url);
			
			ret = f.exists() || f.createNewFile();
			
			System.out.println("File ["+f.getAbsoluteFile()+"] available ["+ret+"]");
			
		}
		else
			System.out.println("FilePath  ["+url+"] is null or empty");
		
		return ret ;
		
	}
	
	public boolean setSystemOutLogs() throws Exception
	{
		ret = false ;
		
		boolean isLog = isNull(Dbmanager.getKeyProperties(IS_SYSOUT_LOGGABLE)).equalsIgnoreCase("Y");
		
		logContent(" Console File Path ["+session.getAttribute("ConsoleLog")+"] isLog ["+isLog+"] ",LoggerFactory.DEBUG,null);
		
		if(isLog)
		{
			if (session.getAttribute("ConsoleLog") == null || printStream == null )
			{
				try 
				{
					String filePath = LogUrl != null && !LogUrl.trim().equals("") ? LogUrl : Dbmanager.getKeyProperties("log")  
							, fileName = session.getAttribute("consoleFileName").toString();

					filePath += CONSOLE_FOLDERNAME +seperator ;
					
					logContent("Inside setSystemOutLogs filePath["+filePath+"] fileName ["+fileName+"]",LoggerFactory.DEBUG,null);
					
					ret = createFolders(filePath) && createFiles( filePath + fileName);
					
					session.setAttribute( "ConsoleLog", new PrintStream(filePath+fileName) );
				} 
				catch (Exception e) 
				{
					logContent("Exception in File Path Creation" ,LoggerFactory.DEBUG,e,null);
					throw e ;
				}
			}
			
			printStream =  (PrintStream) session.getAttribute("ConsoleLog");
			
			System.setOut(printStream);
			
			logContent("Get PrintStream [" + printStream +"]",LoggerFactory.DEBUG,null );

			ret = printStream != null ;

			}
		
		return ret ;
	}
	
	private synchronized boolean setProperties(String key, String value) throws Exception 
	{
		return Dbmanager.setProperties(key, value);
	}
	
	public static Generic getInstance()
	{
		if(generic !=null)
			return generic;
		else
			return new Generic();
	}
	
	public static StringWriter getRootCauseException(Throwable e)
	{
		StringWriter sw = new StringWriter();
    	
    	PrintWriter pw = new PrintWriter(sw);
    	
    	if( e !=null)
    		e.printStackTrace(pw);
    	
    	return sw ;
	}

	public void setHttpServlets(HttpServletRequest req, HttpServletResponse res)
    {
    	session = req.getSession() ;
    	request = req ;
    	response = res ;
    }
	
}
