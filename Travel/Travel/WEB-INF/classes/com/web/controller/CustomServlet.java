package com.web.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;

import com.web.common.Generic;
import com.web.log4j.LoggerFactory;

@WebServlet("/CustomServlet")
public class CustomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public String mode = "";
    private HttpServletRequest req = null;

    public CustomServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException 
    {

    	req = request;
    	
    	Generic.setHttpServlets(res,request);

    	mode = req.getParameter("mode") == null ? "" : req.getParameter("mode").trim();

    	PrintDetails();
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
    private void PrintDetails() 
    {
        Enumeration<String> e = req.getParameterNames();

        while (e.hasMoreElements()) 
        {
            String s = e.nextElement();
            String value = req.getParameter(s);

            logContent(String.format(Locale.getDefault()," %-25s =>  %s " , s , value) , LoggerFactory.DEBUG);
        }
    }
    
    /** 
     * @param message
     * @param logLevel
     * @param e
     */
    public void logContent(String message, Level logLevel)
    {
        Generic.logContent(message, logLevel, null, this);
    }
    
    /**
     * @param message
     * @param logLevel
     * @param e
     */
    public void logContent(String message, Level logLevel, Throwable e)
    {
        Generic.logContent(message, logLevel, e, this);
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
 * logContent(
 * String.format(Locale.getDefault(),"%-"+d.getClass().getSimpleName().
 * length()+"s :: %-25s =>  %s ","Attributes Names", s, value) );
 * }
 * while(headerNames.hasMoreElements())
 * {
 * String s = headerNames.nextElement();
 * String value = request.getHeader(s);
 * 
 * logContent(
 * String.format(Locale.getDefault(),"%-"+d.getClass().getSimpleName().
 * length()+"s :: %-25s =>  %s ","Header Names", s, value) );
 * }
 */ /*
 * while(sess.hasMoreElements())
 * {
 * String s = sess.nextElement();
 * String value = request.getSession().getAttribute(s)+"";
 * 
 * logContent(
 * String.format(Locale.getDefault(),"%-"+d.getClass().getSimpleName().
 * length()+"s :: %-25s =>  %s ","Session Names", s, value) );
 * }
 */
/*
 * logContent(
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
