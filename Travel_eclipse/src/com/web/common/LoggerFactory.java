package com.web.common;
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
            
            boolean isLoggable = StringChecker.isNull(Dbmanager.getKeyProperties("isLoggable")).trim().equalsIgnoreCase("Y");

            if( exception != null)
            {
                sb.append("["+d.getClass().getName()+"]\t"+
                        	"["+user+"]\t"
                                +whattoprint 
                                +" ** Error in ***"
                                +exception.getClass().getName() 
                                +" => "+exception.getMessage()).append("\n").append(exception.getStackTrace()[0]);
            }
            else
            {
                sb.append("["+d.getClass().getName()+"]\t"+"["+user+"]\t"+whattoprint );
            }

            if ( !isLoggable )
            {
                System.out.println(sb.toString());
                return;
            }

            Logger.getRootLogger().log(logLevel,sb.toString());

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }    
}
