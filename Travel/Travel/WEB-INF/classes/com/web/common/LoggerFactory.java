package com.web.common;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.web.util.Dbmanager;

public class LoggerFactory extends org.apache.log4j.Level
{
	private static final long serialVersionUID = 1L;

	protected LoggerFactory(int level, String levelStr, int syslogEquivalent) 
    {
        super(level, levelStr, syslogEquivalent);
    }
    
    /**
     * @param whattoprint
     * @param logLevel
     * @param fileName
     * @param exception
     * @param d 
     * @param d
     * @param logInfo
     * @param request 
     */
    public static void displayDiffLogLevels(String whattoprint,Level logLevel, Throwable exception, Object d) 
    {
        try {

            StringBuffer sb = new StringBuffer();

            String user = Dbmanager.getKeyProperties("log_user");
            
            String className = d.getClass().getSimpleName();
            
            boolean isLoggable = StringChecker.isNull(Dbmanager.getKeyProperties("isLoggable")).trim().equalsIgnoreCase("Y");

            if( exception != null)
            {
            	Throwable t = exception ;
            	
                sb.append(String.format("[%-15s] [%-10s] "+whattoprint+"  ** Error in *** %-20s => %-20s ", className,user,t.getClass().getName(),t.getMessage() )).append("\r\n");
            }
            else
            {
                sb.append(String.format("[%-20s] [%-20s] "+whattoprint, className,user ));
            }

            if ( !isLoggable )
            {
                System.out.println(sb.toString());
                return;
            }

            if(exception == null)
            	Logger.getRootLogger().log(logLevel,sb.toString());
            else
            	Logger.getRootLogger().error(sb.toString(), exception);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }    
}
