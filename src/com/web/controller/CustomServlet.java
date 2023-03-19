package com.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;

import com.web.common.LoggerFactory;
import com.web.objects.Login_Info;

@WebServlet("/CustomServlet")
public class CustomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public String mode = null;
    private PrintStream ps = null;
    private HttpServletRequest req = null;

    public CustomServlet() {
        super();
    }

    protected void service(HttpServletRequest request, Object d) throws ServletException, IOException {

        this.req = request;
        
        mode = req.getParameter("mode") == null ? "" : req.getParameter("mode");
        
        setFilePath();
        
        PrintDetails(d);
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
            System.out.println(e.getMessage());
        }

    }


    private void setFilePath() 
    {

        if ( req.getSession().getAttribute("consoleFilePath") == null )
        {
            String consoleFilePath = "/Git/My Repository/Completed/Travel_Current_Project/Logs/"
                    + LocalDate.now().toString().replaceAll("-", "/")  +"/Console";

            String consoleFileName ="/"+System.currentTimeMillis() + "_console.log";

            String loggerFilePath = "/Git/My Repository/Completed/Travel_Current_Project/Logs/"
                    + LocalDate.now().toString().replaceAll("-", "/") + "/Log4j/";
            
            System.out.println("consoleFile =>"+consoleFilePath + consoleFileName);
            System.out.println("Log File Path =>"+loggerFilePath);

            req.getSession().setAttribute("consoleFilePath", consoleFilePath);
            req.getSession().setAttribute("consoleFileName", consoleFileName);
            req.getSession().setAttribute("loggerFilePath", loggerFilePath);
            
            System.out.println(" File Path Added to session ");
        }

//        setLogAppenders(request.getSession());  
//        setConsoleLogPrintStream(req.getSession());
    }

    /**
     * @param request
     * @throws Exception
     */
    protected void setParameters() throws Exception {

        Enumeration<String> e = req.getParameterNames();

        while (e.hasMoreElements()) {
            String s = e.nextElement();
            String value = req.getParameter(s);

            req.setAttribute(s, value);
        }
    }

    /**
     * @param d
     */
    private void PrintDetails(Object d) {
        Enumeration<String> e = req.getParameterNames();

        while (e.hasMoreElements()) {
            String s = e.nextElement();
            String value = req.getParameter(s);

            logContent(String.format(Locale.getDefault(),
                    " %-25s =>  %s " , s , value) , LoggerFactory.DEBUG, null);
        }
    }

    /**
     * @param session
     * @return
     */
    private void setConsoleLogPrintStream(HttpSession session) 
    {
        if ( session.getAttribute("ConsoleLog") == null )
        {
            try 
            {
                String filePath = session.getAttribute("consoleFilePath").toString();

                String fileName = session.getAttribute("consoleFileName").toString();
                
                File f = new File(filePath + fileName);

                if ( f.exists() ) 
                {   
                    session.setAttribute("ConsoleLog", new PrintStream(filePath + fileName));
                } 
                else 
                {
                    if ( new File(filePath).exists() == false )
                    {
                        new File(filePath).mkdirs();
                    }
                    
                    if ( f.createNewFile() ) 
                    {
                        logContent("File created " + f.exists() + " File Name :" + f.getName() , LoggerFactory.DEBUG, null);
                        logContent("Get File Path => " + f.getAbsolutePath() , LoggerFactory.DEBUG, null);

                        session.setAttribute("ConsoleLog", new PrintStream(filePath + fileName));
                        
                    } else 
                    {
                        logContent("File Creation Issue" ,  LoggerFactory.DEBUG, null);
                    }
                }
                
            } catch (Exception e) 
            {
                System.out.println("Exception in File Path Creation =>"+e.getMessage());
                return;
            }
        }
        
        this.ps =  (PrintStream) session.getAttribute("ConsoleLog");
        
        if ( this.ps != null)
        {
            System.setOut(ps);
        }
    }

    /**
     * @param message
     * @param logLevel
     * @param e
     */
    public void logContent(String message, Level logLevel, Exception e)
    {
        LoggerFactory.displayDiffLogLevels(message, logLevel, e, this);
    }

}

// Comments

//Enumeration<String> attrnames = request.getAttributeNames();
//Enumeration<String> sess = request.getSession().getAttributeNames();
//Enumeration<String> headerNames = request.getHeaderNames();

/*
 * while(attrnames.hasMoreElements())
 * {
 * String s = attrnames.nextElement();
 * String value = request.getAttribute(s)+"";
 * 
 * System.out.println(
 * String.format(Locale.getDefault(),"%-"+d.getClass().getSimpleName().
 * length()+"s :: %-25s =>  %s ","Attributes Names", s, value) );
 * }
 * while(headerNames.hasMoreElements())
 * {
 * String s = headerNames.nextElement();
 * String value = request.getHeader(s);
 * 
 * System.out.println(
 * String.format(Locale.getDefault(),"%-"+d.getClass().getSimpleName().
 * length()+"s :: %-25s =>  %s ","Header Names", s, value) );
 * }
 */ /*
 * while(sess.hasMoreElements())
 * {
 * String s = sess.nextElement();
 * String value = request.getSession().getAttribute(s)+"";
 * 
 * System.out.println(
 * String.format(Locale.getDefault(),"%-"+d.getClass().getSimpleName().
 * length()+"s :: %-25s =>  %s ","Session Names", s, value) );
 * }
 */
/*
 * System.out.println(
 * " getMethod "+"=>"+ request.getMethod()
 * +"\ngetScheme "+"=>"+ request.getScheme()
 * +"\ngetProtocol "+"=>"+ request.getProtocol()
 * +"\ngetContentLength "+"=>"+ request.getContentLength()
 * +"\ngetContentLengthLong "+"=>"+ request.getContentLengthLong()
 * +"\ngetContentType "+"=>"+ request.getContentType()
 * +"\ngetQueryString "+"=>"+ request.getQueryString()
 * +"\ngetLocale "+"=>"+ request.getLocale()
 * +"\ngetLocalName "+"=>"+ request.getLocalName()
 * +"\ngetCookies "+"=>"+ request.getCookies()
 * +"\ngetServletContext "+"=>"+ request.getServletContext()
 * +"\ngetSession "+"=>"+ request.getSession()
 * +"\ngetSession "+"=>"+ request.getSession()
 * +"\ngetCharacterEncoding "+"=>"+ request.getCharacterEncoding()
 * +"\ngetParameterNames "+"=>"+ request.getParameterNames()
 * +"\ngetRequestedSessionId "+"=>"+ request.getRequestedSessionId()
 * +"\ngetDispatcherType "+"=>"+ request.getDispatcherType()
 * +"\ngetPathTranslated "+"=>"+ request.getPathTranslated()
 * +"\ngetAttributeNames "+"=>"+ request.getAttributeNames()
 * +"\ngetServerName "+"=>"+ request.getServerName()
 * +"\ngetServerPort "+"=>"+ request.getServerPort()
 * +"\ngetRemoteAddr "+"=>"+ request.getRemoteAddr()
 * +"\ngetRemoteHost "+"=>"+ request.getRemoteHost()
 * +"\ngetLocalAddr "+"=>"+ request.getLocalAddr()
 * +"\ngetLocales "+"=>"+ request.getLocales()
 * +"\ngetParameterMap "+"=>"+ request.getParameterMap()
 * +"\ngetRequestURI "+"=>"+ request.getRequestURI()
 * +"\ngetContextPath "+"=>"+ request.getContextPath()
 * +"\ngetRemoteUser "+"=>"+ request.getRemoteUser()
 * +"\ngetServletPath "+"=>"+ request.getServletPath()
 * +"\ngetUserPrincipal "+"=>"+ request.getUserPrincipal()
 * +"\ngetAuthType "+"=>"+ request.getAuthType()
 * +"\ngetPathInfo "+"=>"+ request.getPathInfo()
 * +"\ngetRequestURL "+"=>"+ request.getRequestURL()
 * +"\ngetLocalPort "+"=>"+ request.getLocalPort()
 * +"\ngetHeaderNames "+"=>"+ request.getHeaderNames()
 * );
 */
