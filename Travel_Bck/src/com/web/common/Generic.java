package com.web.common;

import java.io.File;
import java.io.PrintStream;
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
    
    private static PrintStream printStream = null;
    
    private static final String seperator ="/";

    public Generic()
    {
        setConnection();
    }
    
    public void closeAll() throws Exception
    {

        StringBuilder sb = new StringBuilder();

        if ( con !=null && !con.isClosed() ) 
        {
            con.close();
            sb.append(" Connection Closed [").append(con.isClosed()).append("] ");
        }

        if ( ps !=null && !ps.isClosed() ) 
        {
            ps.close();
            sb.append(" Statement Closed [").append(ps.isClosed()).append("] ");
        }

        if ( rs !=null && !rs.isClosed() ) 
        {
            rs.close();
            sb.append(" ResultSet Closed [").append(rs.isClosed()).append("] ");
        }
        
        logContent(getClass().getSimpleName() + sb.toString() ,LoggerFactory.DEBUG , null);
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
        
        logContent("Access Log Entered "+( ps.executeUpdate() > 0 ) ,LoggerFactory.DEBUG , null );

    } 

    public static void logContent(String message, Level logLevel, Throwable e) 
    {
        LoggerFactory.displayDiffLogLevels(message, logLevel, e,Generic.class);
    }
    
    public static void logContent(Object message, Level logLevel, Throwable e) 
    {
        LoggerFactory.displayDiffLogLevels(message != null ? message.toString() : "", logLevel, e,Generic.class);
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
        
        logContent("rootException =>"+rootException, LoggerFactory.DEBUG, rootException);
        
        return rootException;
    }
    
    
    /**
     * @param session
     * @return
     * @throws Exception 
     */
    public static void setConsoleLogPrintStream(HttpSession session) throws Exception 
    {
    	boolean isLoggable = isNull(Dbmanager.getKeyProperties("isLoggable")).equalsIgnoreCase("Y");

    	if(isLoggable)
    	{
    		if (session.getAttribute("ConsoleLog") == null )
    		{
    			try 
    			{
    				String filePath = Dbmanager.getKeyProperties("log").concat(seperator+"Console");

    				String fileName = session.getAttribute("consoleFileName").toString();

    				File f = new File( filePath.concat(fileName) );

    				if ( f.exists() ) 
    				{   
    					logContent("Get File Path for console log=> " + f.getAbsolutePath() , LoggerFactory.DEBUG, null);
    					session.setAttribute( "ConsoleLog", new PrintStream( f.getAbsolutePath() ) );
    				} 
    				else 
    				{
    					if ( f.getParentFile().exists() == false )
    					{
    						f.getParentFile().mkdirs();
    					}

    					if ( f.createNewFile() ) 
    					{
    						logContent("File created " + f.exists() + " File Name :" + f.getName() , LoggerFactory.DEBUG, null);
    						logContent("Get File Path => " + f.getAbsolutePath() , LoggerFactory.DEBUG, null);

    						session.setAttribute( "ConsoleLog", new PrintStream( f.getAbsolutePath() ) );

    					} 
    					else 
    					{
    						logContent("File Creation Issue" ,  LoggerFactory.DEBUG, null);
    					}
    				}

    			} catch (Exception e) 
    			{
    				logContent("Exception in File Path Creation =>"+e.getMessage() ,LoggerFactory.ERROR , null );
    				return;
    			}
    		}

    		printStream =  (PrintStream) session.getAttribute("ConsoleLog");

    		String printProperty = isNull(System.getProperty("PrintStream")) ;

    		logContent("Get PrintStream [" + printStream +"Property ["+printProperty+"]" , LoggerFactory.DEBUG, null);


    		if ( printStream != null && !printProperty.equals(printStream.toString()) )
    		{
    			System.setOut(printStream);
    			System.setProperty("PrintStream", printStream.toString());
    		}

    	}
    }
    
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
            logContent(e.getMessage() ,LoggerFactory.ERROR , null );
        }

    }


    public static void setFilePath(HttpSession session) throws Exception 
    {

        if ( session.getAttribute("consoleFilePath") == null )
        {
            
            String logPath = Dbmanager.getKeyProperties("log");
            
            String datePath = LocalDate.now().toString().replaceAll("-", "/");

            String consoleFileName = seperator + System.currentTimeMillis() + "_console.log";

            String loggerFilePath = logPath +seperator+ datePath + seperator + "Log4j" + seperator;
            
            logContent("Log File Path =>"+loggerFilePath ,LoggerFactory.DEBUG , null );

            session.setAttribute("consoleFileName", consoleFileName);
            session.setAttribute("loggerFilePath", loggerFilePath);
            
            logContent(" File Path Added to session " ,LoggerFactory.DEBUG , null );
        }

    }

    
}
