package com.web.common;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LoggerFactory
{
    private static Logger logger = null;
    private static Appender appender ;
    
    public static Level INFO   = Level.INFO;
    public static Level ERROR  = Level.ERROR;
    public static Level DEBUG  = Level.DEBUG;
    public static Level WARN   = Level.WARN;
    public static Level FATAL  = Level.FATAL;
    /**
     * @param whattoprint
     * @param logLevel
     * @param fileName
     * @param exception
     * @param d
     * @param session
     */
    public static void displayDiffLogLevels(String whattoprint,Level logLevel, Exception exception, Object d,HttpSession session) 
    {
        try {

            logger = Logger.getLogger(d.getClass());
            
            appender = (Appender) session.getAttribute("loggerappender");
            
            logger.addAppender(appender);
            
            if ( exception !=null)
            {
                logger.log(logLevel
                        , "["+session.getAttribute("user_id")+"] - "+ whattoprint+" ** Error in ***"
                                +exception.getClass().getCanonicalName() +" => "+exception.getMessage());
            }   
            else
            {
                logger.log(logLevel,"["+session.getAttribute("user_id")+"] - "+whattoprint);
            }
                
            
            
        }catch(Exception e)
        {
            System.out.println(e);
        }

    }    
}
