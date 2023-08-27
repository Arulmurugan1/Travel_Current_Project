package com.web.common;
import javax.servlet.http.HttpSession;

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
     * @param session 
     * @param logInfo
     * @param request 
     */
    public static void displayDiffLogLevels(String whattoprint,Level logLevel, Throwable exception, Object d, HttpSession session) 
    {
        try {

            StringBuffer sb = new StringBuffer();

            String user = CommonFactory.isNull(session.getAttribute("user_id"));
            
            String className = d.getClass().getSimpleName();
            
            boolean isLoggable = CommonFactory.isNull(Dbmanager.getKeyProperties("isLoggable")).trim().equalsIgnoreCase("Y");

            if( exception != null)
            {
            	Throwable t = exception ;
            	
                sb.append(String.format("[%-15s] ["+user+"] "+whattoprint+"  ** Error in *** %-20s => %-20s ", className,t.getClass().getName(),t.getMessage() )).append("\r\n");
            }
            else
            {
                sb.append(String.format("[%-20s] ["+user+"] "+whattoprint, className ));
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
        	Logger.getRootLogger().error(" ", exception);
        }

    }    
}
