package com.web.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

import org.apache.log4j.Level;

import com.web.objects.AccessLog;
import com.web.util.Dbmanager;

public class Generic extends CommonFactory
{

	public static Connection con 						= null ;
	public static PreparedStatement ps 				= null ;
	public static ResultSet rs 								= null ;
	public static Generic generic 						= new Generic();
	public static HttpSession session 					= null ;
	public static HttpServletRequest request 		= null  ;
	public static HttpServletResponse response = null ;

    PrintWriter out 	= null ;
	boolean ret 		= false ;

	private static PrintStream printStream = null;

	public Generic()
	{
		setConnection();
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

	public boolean setLogConsoleProperties() throws Exception 
	{
		System.out.println("Inside Log Path from " +this.getClass().getSimpleName() +LOG_USER +"["+isNull(session.getAttribute("user_id"))+"]");
		return setProperties() && setSystemOutLogs();
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

	/*
	 * @SuppressWarnings("unused") private void setLogAppenders(HttpSession session)
	 * {
	 * 
	 * try {
	 * 
	 * if ( session.getAttribute("loggerlayout") == null ) { String filePath =
	 * session.getAttribute("loggerFilePath").toString();
	 * 
	 * //Set Layout
	 * 
	 * Layout layout = new
	 * PatternLayout("[%d{dd-MM-yy hh:mm:ss:sss a z}] %-5p %-2c{1}[Line:%-2L] - %m %n"
	 * );
	 * 
	 * //Set Appender
	 * 
	 * Appender appender = new FileAppender(layout, filePath + "CommonLogs.log");
	 * 
	 * session.setAttribute("loggerlayout", layout);
	 * session.setAttribute("loggerappender", appender); }
	 * 
	 * } catch(Exception e) { logContent(e.getMessage() ,LoggerFactory.ERROR , null,
	 * this ); }
	 * 
	 * }
	 */

	private boolean setProperties() throws Exception 
	{
		ret = false ;
		
		System.out.println("printStream " +printStream) ;

		String url = Dbmanager.getPropertiesPath(LOG_PATH);
		
		if(url.equals(""))
		{
			throw new FileNotFoundException(LOG_PATH_NOT_FOUND);
		}
		
		LocalDate dateInstance = LocalDate.now();
		
		int month 			= dateInstance.getMonthValue() ;
		int day 				= dateInstance.getDayOfMonth() ;
		int year 				= dateInstance.getYear();
		
		String monthDesc 	= CommonFactory.StringInit(dateInstance.getMonth().name());
		String dayDesc 		= CommonFactory.StringInit(dateInstance.getDayOfWeek().name());
		
		String path = year +seperator+ month +"-"+monthDesc  +seperator+ day +"-"+dayDesc + seperator ;
		
		System.out.println("Inside setProperties() Log Path ["+ url +"] folder ["+path+"]");

		if( !url.endsWith(seperator) )
			url += seperator + path;
		else
			url += path;
		
		ret = setProperties("log",url) &&  setConsoleFilePath(url);
		
		logContent(" File Path Added to session ",LoggerFactory.DEBUG,null);
		logContent(" Console File Path ["+session.getAttribute("consoleFilePath")+"]",LoggerFactory.DEBUG,null);
		System.out.println("Log will be redirected to "+url);

		return ret ;

	}

	private boolean setConsoleFilePath(String url) throws Exception
	{
		ret = false ;
		
		System.out.println("consoleFilePath ["+session.getAttribute("consoleFilePath")+"]");

		if ( session.getAttribute("consoleFilePath") == null )
		{	
			String loggerFilePath 	= url    ;
			String consoleFilePath = url    ;

			ret = createFolders(loggerFilePath) 
						&& createFiles(loggerFilePath+LOG_FILENAME);
			
			System.out.println("Log File Path available ["+ret+"]");
			
			session.setAttribute("consoleFileName", CONSOLE_FILENAME);
			session.setAttribute("loggerFilePath", loggerFilePath);
			session.setAttribute("consoleFilePath", consoleFilePath);
		}
		
		return ret ;
	}
	
	public boolean setSystemOutLogs() throws Exception
	{
		ret = false ;

		boolean isLog = Boolean.parseBoolean( Dbmanager.getKeyProperties(IS_SYSOUT_LOGGABLE) ) ;
		
		System.out.println(" Console Log ["+session.getAttribute("printStream")+"] isLoggable ["+isLog+"] ");

		if (session.getAttribute("printStream") == null || printStream == null )
		{
			try 
			{
				String filePath = (String) session.getAttribute("consoleFilePath") ;
				
				if(filePath == null)
					filePath = Dbmanager.getKeyProperties("log") ;
				
				System.out.println(" Console File Path ["+filePath+"]");
				
				String fileName = session.getAttribute("consoleFileName").toString();

				System.out.println(" Console File Name ["+fileName+"]");
				
				System.out.println("Inside setSystemOutLogs filePath["+filePath+"] fileName ["+fileName+"]");

				ret = createFolders(filePath) && createFiles( filePath + fileName);

				if(ret)
				{
					printStream = new PrintStream(new FileOutputStream(filePath+fileName, true)) ;
					session.setAttribute( "printStream", printStream );
				}
				
				System.out.println("printStream " +printStream) ;

			} 
			catch (Exception e) 
			{
				logContent("Exception in File Path Creation" ,LoggerFactory.ERROR,e,null);
				throw e ;
			}
		}
		else
			System.out.println("printStream " +printStream) ;

		printStream =  (PrintStream) session.getAttribute("printStream");

		System.setOut(printStream);
		System.setErr(printStream);

		System.out.println(" Errors exists in PrintStream [" + printStream.checkError() +"]");

		return printStream != null ;
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

	public void setHttpServlets(HttpServletRequest req, HttpServletResponse res)
    {
    	session = req.getSession() ;
    	request = req ;
    	response = res ;
    }
	
}
