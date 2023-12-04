package com.web.log4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import com.web.common.Generic;
import com.web.util.Dbmanager;

/*This  can be used with log4j with properties like XML / property data */

public class LogProcessor extends Generic 
{
	
	public static String LOG_PATH_TODAY 		= "" ;
	private static PrintStream printStream 			= null;
	
	boolean ret 													= false ;

	public LogProcessor(HttpServletRequest request) throws Exception 
	{
		super(request);
	}
	
	public LogProcessor() {
		// TODO Auto-generated constructor stub
	}

	public boolean setLogConsoleProperties() throws Exception 
	{
		System.out.println("Inside Log Path from " +this.getClass().getSimpleName() +" "+LOG_USER +" ["+isNull(session.getAttribute("user_id"))+"]");
		return setProperties() && setConsoleFilePath();
	}
	
	private boolean setProperties() throws Exception 
	{
		ret = false ;
		
		System.out.println(" Inside setProperties() ....... " + " printStream " +printStream );

		getLogPathForToday();

		ret = setProperties("log",LOG_PATH_TODAY) ;

		System.out.println("  setProperties done Log will be redirected to "+LOG_PATH_TODAY);

		return ret ;

	}
	
	private boolean setConsoleFilePath() throws Exception
	{
		ret = false ;

		System.out.println("consoleFilePath ["+session.getAttribute("consoleFilePath")+"]");

		if ( session.getAttribute("consoleFilePath") == null )
		{	
			String getDayToday = getDayToday() ;

			String Logfile 			= LOG_FILENAME + getDayToday.replaceAll(seperator, "_").replaceAll("-", "_")+".log";
			String consoleFile 	= CONSOLE_FILENAME + getDayToday.replaceAll(seperator, "_").replaceAll("-", "_")+".log";

			ret = createFolders(LOG_PATH_TODAY) && createFiles(LOG_PATH_TODAY + Logfile);

			ret = setProperties("log4j.appender.file.File","${log}"+Logfile) ;

			System.out.println(" setProperties done for ${log} Log File Path available in Log4j property ["+ret+"]");

			session.setAttribute("logFileName", Logfile);
			session.setAttribute("consoleFileName", consoleFile);
			session.setAttribute("loggerFilePath", LOG_PATH_TODAY);
			session.setAttribute("consoleFilePath", LOG_PATH_TODAY);
			
			System.out.println(" Generated Log File Name ["+Logfile+"]");
			System.out.println(" Generated Console File Name ["+consoleFile+"]");
			System.out.println(" Generated Log File Path ["+LOG_PATH_TODAY+"]");
			

			System.out.println(" File Path Added to session ");

		}

		return ret ;
	}
	
	public void doCheckLog4jLogs() throws Exception 
	{
		
		System.out.println(" Inside doCheckLog4jLogs .......");

		boolean ignorelog = getLogPathForToday().equals(Dbmanager.getLogProperties( LOG_PROP_PATH) )   ;
		
		System.out.println(" Validation of Log Path update Needed ["+!ignorelog+"] ");
		
		if( ! ignorelog  )
		{
			System.out.println(" Validation of Log Path update starts .... ");
			
			boolean checkStatus = request.getServletPath().contains("Login")  ;
			
			if( ! checkStatus )
			{
				checkStatus = isNull(request.getParameter("mode")).equals("L")  ;
			}
			
			System.out.println(" Need to Validate the user authentication ["+(checkStatus ? "No" : "Yes")+"] ");
			
			if (   checkStatus )
			{

				String propUser 	= isNull(request.getParameter("txtUser"));
				String sesUser 		= isNull(session.getAttribute("user_id"));

				System.out.println(" User Id : for authentication ::: "+propUser );
				System.out.println(" User Id : from Session ::: "+sesUser );

				if(propUser.equals(""))
				{
					System.out.println(" Redirect for authentication is Empty :)");
					throw new Exception(" User details is Empty . Please Re-login :) ") ;
				}
				else if( ! propUser.equals(sesUser) )
				{
					session.setAttribute("user_id", propUser);					
					System.out.println(" User authentication done for user ["+propUser+"] ");
				}
			}
	
			if( setLogConsoleProperties() )
			{
				ignorelog =  true ;
				System.out.println("Log Path Set for Today ");
			}
			else
			{
				System.out.println("Something went Wrong in LogProperties Set ");
				throw new Exception(" Something went Wrong in LogProperties Set ") ;
			}
			
		}
		
		if( ! ignorelog  )
			setConsoleFilePath() ;

		if( session.getAttribute("printStream") == null)
		{
			setSystemOutLogs();
		}
		
		Generic.closeOpenConnections();
		
	}
	
	public static String  getLogPathForToday() throws Exception
	{
		String url = Dbmanager.getDBPropertiesPath(LOG_PATH);

		if(url.equals(""))
		{
			throw new FileNotFoundException(LOG_PATH_NOT_FOUND);
		}
		
		String path = getDayToday();
		
		System.out.println("Inside getLogPathForToday() Log Path ["+ url +"] folder ["+path+"]");

		if( !url.endsWith(seperator) )
			LOG_PATH_TODAY = url+seperator + path;
		else
			LOG_PATH_TODAY = url+ path;

		return LOG_PATH_TODAY ;

	}

	public static String getDayToday() throws Exception
	{

		LocalDate dateInstance = LocalDate.now();

		int month 			= dateInstance.getMonthValue() ;
		int day 				= dateInstance.getDayOfMonth() ;
		int year 				= dateInstance.getYear();

		String monthDesc 	= StringInit( dateInstance.getMonth().name() );
		String dayDesc 		= StringInit( dateInstance.getDayOfWeek().name() );
		
		System.out.println( " Today Log Date " + dateInstance );

		return year +seperator+ ( month +"-"+monthDesc ) +seperator+ ( day +"-"+dayDesc ) + seperator ;	
	}

	public static boolean setSystemOutLogs(String fileName, String filePath) throws Exception
	{
		boolean ret = false ;
		
		System.out.println("Inside setSystemOutLogs filePath["+filePath+"] fileName ["+fileName+"]");
		
		if( ! isBlankCheck(fileName) && ! isBlankCheck(filePath) )
		{
			System.out.println(" Inside setSystemOutLogs .. Console log Process starts .... ");

			System.out.println(" Console Log ["+session.getAttribute("printStream")+"] isLoggable ["+Dbmanager.getLogProperties(IS_SYSOUT_LOGGABLE) +"] ");
			
			if (session.getAttribute("printStream") == null || printStream == null )
			{
				printStream = new PrintStream(new FileOutputStream(filePath+fileName, true)) ;
				session.setAttribute( "printStream", printStream );
			}
			
			printStream =  (PrintStream) session.getAttribute("printStream");

			System.setOut(printStream);
			System.setErr(printStream);

			System.out.println(" Errors exists in PrintStream [" + printStream.checkError() +"]");

			ret =  printStream != null ;
			
		}
		else
		{
			System.out.println(" Inside setSystemOutLogs .. Both FileName and File Path becomes empty ...... ");
		}
		
		return ret ;
	}

	public boolean setSystemOutLogs() throws Exception
	{
		ret = false ;
		
		System.out.println(" Inside setSystemOutLogs .. Console log Process starts .... ");

		System.out.println(" Console Log ["+session.getAttribute("printStream")+"] isLoggable ["+Dbmanager.getLogProperties(IS_SYSOUT_LOGGABLE)+"] ");

		if (session.getAttribute("printStream") == null || printStream == null )
		{
			try 
			{
				String filePath = (String) session.getAttribute("consoleFilePath") ;

				if(filePath == null)
					filePath = Dbmanager.getLogProperties(LOG_PROP_PATH) ;

				String fileName = (String) session.getAttribute("consoleFileName");

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
				logContent("Exception in File Path Creation" ,ERROR,e);
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
	
}
