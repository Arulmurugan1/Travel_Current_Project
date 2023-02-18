package com.web.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/CustomServlet")
public class CustomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String mode ;
    
	protected void service(HttpServletRequest request,Object d) throws ServletException, IOException 
	{
	    mode = request.getParameter("mode") == null ? "" : request.getParameter("mode");
	    PrintDetails(request, d);
	}
	
	protected void setParameters(HttpServletRequest request) throws Exception
    {
        
	    Enumeration<String> e = request.getParameterNames();
	    
	    while(e.hasMoreElements())
        {
            String s = e.nextElement();
            String value = request.getParameter(s);
            
            request.setAttribute(s, value);
        }
    }
	
	private void PrintDetails(HttpServletRequest request,Object d) 
	{
	    Enumeration<String> e = request.getParameterNames();	    
//	    Enumeration<String> attrnames = request.getAttributeNames();
//	    Enumeration<String> sess = request.getSession().getAttributeNames();
//	    Enumeration<String> headerNames = request.getHeaderNames();

	    while(e.hasMoreElements())
	    {
	        String s = e.nextElement();
	        String value = request.getParameter(s);

	        System.out.println( String.format(Locale.getDefault(),"%-"+d.getClass().getSimpleName().length()+"s :: %-25s =>  %s ",d.getClass().getSimpleName(), s, value) );
	    }
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
	}

}
