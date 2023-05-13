package com.web.common;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LoggerFactory
{
    private static Logger logger = null;
//  private static Appender appender ;

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
     * @param d
     * @param logInfo
     * @param request 
     */
    public static void displayDiffLogLevels(String whattoprint,Level logLevel, Throwable exception, Object d) 
    {
        try {
            
            boolean isLoggable = false ;
            
            if ( !isLoggable )
            {
                if( exception !=null && exception instanceof Throwable)
                    System.out.println(exception.getStackTrace()[0] );
                else
                    System.out.println(whattoprint);
                
                return;
            }
            
            logger = Logger.getLogger(d.getClass());

            //            appender = (Appender) session.getAttribute("loggerappender");

            //            logger.addAppender(appender);

            if ( exception !=null)
            {
                    logger.log(logLevel
                            , whattoprint+" ** Error in ***"
                                    +exception.getClass().getCanonicalName() +" => "+exception.getMessage());
            }         
            else
            {
               logger.log(logLevel,whattoprint);
            }

        }catch(Exception e)
        {
            throw e ;
        }

    }    
}
