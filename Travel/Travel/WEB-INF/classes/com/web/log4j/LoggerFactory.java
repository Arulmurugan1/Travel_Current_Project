package com.web.log4j;
import java.sql.Timestamp;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.web.common.CommonFactory;
import com.web.common.Constant;
import com.web.common.Generic;
import com.web.util.Dbmanager;

public class LoggerFactory extends CommonFactory
{
	

	public static Logger logger = Logger.getLogger(LoggerFactory.class) ;
	
	public static boolean isLog4jInitiated			  = false ;
	public static boolean isConsoleLogInitiated			  = false ;
	public static boolean isCreateFilesCalled			  = false ;
	
	public static String LOG_FILE			  			  = "" ;
	public static String CONSOLE_FILE			  = "" ;
	public static String LOG_PATH			  	  	  = "" ;
	
    /**
     * @param whattoprint
     * @param logLevel
     * @param exception
     * @param d
     */
    public static void displayDiffLogLevels(String whattoprint,Level logLevel, Throwable exception, Object d) 
    {
        try {

            StringBuffer sb = new StringBuffer();

            String user = isNull(Generic.session.getAttribute("user_id"));
            
            String className = d.getClass().getSimpleName();
            
            boolean isLoggable = false ;
            
            String isLog = Dbmanager.getLogProperties(IS_SYSOUT_LOGGABLE);
            
            if( isBlankCheck(isLog))
            {
            	isLog	= Dbmanager.getDBPropertiesPath(IS_SYSOUT_LOGGABLE) ;
            }
            
            isLoggable = Boolean.parseBoolean(isLog) ;
            
            boolean isSessionPrintAlive = isNull( Generic.session.getAttribute("ConsoleLog")).length() != 0 ;

            if( exception != null)
            {
            	Throwable t = exception ;
            	
                sb.append(String.format("[%-20s] ["+user+"] "+whattoprint+"  ** Error in *** %-20s => %-20s ", className,t.getClass().getName(),t.getMessage() )).append("\r\n");
            }
            else
            {
                sb.append(String.format("[%-20s] ["+user+"] "+whattoprint, className ));
            }

            if ( !( isLoggable || isSessionPrintAlive ) )
            {
                System.out.println(sb.toString().trim());
                return;
            }

            if(exception == null)
            	logger.log(logLevel,sb.toString().trim());
            else
            	logger.error(sb.toString().trim(), exception);
        }
        catch(Exception e)
        {
        	logger.error(" ", exception);
        }

    }    
    
    static
	{
    	System.out.println(" Static Block Called inside LoggerFactory");
		
    	try {
			setVariables();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
	}
    
    public static void initLog4j()
    {
    	try 
		{
    		
			if ( /*createFiles()*/ true )
			{
				setAppenders();
				isLog4jInitiated = true ;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
    }
    
    private static void setVariables() throws Exception 
    {
    	LOG_PATH 				= LogProcessor.getLogPathForToday() ;
    	String getDayToday = LogProcessor.getDayToday() ;

    	LOG_FILE 					= LOG_FILENAME + getDayToday.replaceAll(seperator, "_").replaceAll("-", "_")+".log";
    	CONSOLE_FILE 		= CONSOLE_FILENAME + getDayToday.replaceAll(seperator, "_").replaceAll("-", "_")+".log";
	}

	public static void setConsoleLog()
    {
    	try 
    	{
    		System.out.println(" Inside setConsoleLog .. Console log Process starts .... ");
    		
    		if ( ! isCreateFilesCalled )
    		{
    			createFiles();
    		}
    		
    		LogProcessor.setSystemOutLogs(CONSOLE_FILE,LOG_PATH);
    		
    		isConsoleLogInitiated = true ;
    	}
    	catch (Exception e) 
    	{
    		e.printStackTrace();
    	}
    }
    
    public static void setAppenders() throws Exception
    {
    	System.out.println(" Inside SetAppenders() proceed to set the Layout ..... ");
    	setLayout();
    }
    
    public static void setLayout() throws Exception
    {
    	System.out.println(" Inside setLayout() setting the Layout ..... ");
    	
    	FileAppender appender =  new FileAppender(Log4jLayout.getLogLayout(null), LOG_PATH + LOG_FILE, true) ;
    	appender.activateOptions();
    	
		System.out.println(" Appender ... "+appender);
		
		System.out.println(" Log File >> " + appender.getFile());
		System.out.println(" Preferred Layout >> " + appender.getLayout());
		
		System.out.println(" Log is going to be appended >> " + appender.getAppend());

		logger.setLevel(Constant.DEBUG);

		logger.addAppender(appender);
		
		System.out.println(" Log4j Setup is done ready to Use ");

    }
    
    
    private static boolean createFiles() throws Exception
    {
    	
    	Timestamp starts = new Timestamp(System.currentTimeMillis());
    	
    	System.out.println(" File Creation for Logger Starts ......");
    	
    	boolean ret = false ;

    	ret = Generic.createFolders(LOG_PATH)  ;

    	System.out.println(" Log Path "+( ret ? "Created ": " Creation Failed ")+" for ["+LOG_PATH+"] ");
    	
		/* Commented as Log4j default creates it 
		 * ret = Generic.createFiles(LOG_FILE) ;
		 * 
		 * System.out.println(" Log File "+( ret ? "Created ":
		 * " Creation Failed ")+" for ["+LOG_FILE+"] ");
		 */
    	
    	ret = Generic.createFiles(CONSOLE_FILE);
    	
    	System.out.println(" Generated Log File Name ["+LOG_FILE+"]");
    	System.out.println(" Generated Console File Name ["+CONSOLE_FILE+"]");
    	System.out.println(" Generated Log File Path ["+LOG_PATH+"]");
    	
    	System.out.println(" File Creation for Logger Ends ......");
    	
    	System.out.println("Time taken to Complete .... "+ ( new Timestamp(System.currentTimeMillis()).getTime() - starts.getTime() ) +" milli seconds  ");

    	return isCreateFilesCalled = ret ;
	}
    
    public static boolean checkLogPathStatus() throws Exception
    {
    	return LogProcessor.getLogPathForToday().equals(LOG_PATH) ;
    }
    
}
