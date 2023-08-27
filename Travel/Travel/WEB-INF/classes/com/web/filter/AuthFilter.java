package com.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.web.common.CommonFactory;
import com.web.common.Constant;
import com.web.common.Generic;
import com.web.common.LoggerFactory;
import com.web.util.Dbmanager;

@WebFilter(urlPatterns ={"/*"})
public final class AuthFilter implements Filter{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
	    try {
		HttpServletRequest req = (HttpServletRequest) request;

		HttpSession session = req.getSession() ;
		
		if(Generic.session == null)
		{
			Generic.setRequest(req, session);
		}
		
		if ( req.getServletPath().equals("/Login"))
		{
			System.out.println(" User Id : from auth filter "+req.getParameter("txtUser"));
			
			if(req.getParameter("txtUser") != null);
			{
				session.setAttribute("user_id", req.getParameter("txtUser"));
			}
			
			System.out.println( "propertiesSet "+session.getAttribute("propertiesSet") );
			
			String propUser = CommonFactory.isNull(req.getParameter("txtUser"));
			
			if( !propUser.equals(session.getAttribute("user_id"))  ||  session.getAttribute("propertiesSet") == null || !(Boolean)session.getAttribute("propertiesSet")  )
			{
				System.out.println("Inside Log Path from " +this.getClass().getSimpleName());
				Generic prop = new Generic(req);
				session.setAttribute("propertiesSet", prop.setLogConsoleProperties() );
				prop.closeAll();
			}
			
		}
		
		if ( req !=null && ( req.getServletPath().contains(".css") || req.getServletPath().contains(".js") ) )
		{
			chain.doFilter(request, response);
		}
		else
		{
		    session.setMaxInactiveInterval(50000); 
		    
		    
			if ( session.isNew() )
			{
				Generic.logContent("Session Created "+ session.getId(),LoggerFactory.DEBUG , null, this );
				session.setAttribute("localPath", req.getRequestURL() );
				session.setAttribute("sessionid", session.getId() );
			}

			req.getServletContext().setAttribute("localpath",req.getRequestURL());
			
			if ( req.getServletPath().equals("/Login"))
			{
				
			    Generic.logContent("Inside do fIletr",LoggerFactory.DEBUG , null, this);
				chain.doFilter(request, response);
			}
			else if ( session.getAttribute("user") == null && session.getAttribute("role") == null )
			{
				request.getRequestDispatcher(Constant.INDEX_JSP).forward(request, response);
			}
			else
			{
				chain.doFilter(request, response);
			}	
		}
	    }catch(Exception e) {
	    	request.setAttribute("msg", e.getMessage());
	    	Generic.logContent("Auth Filter Exception "+e.getStackTrace()[0],LoggerFactory.DEBUG , null, this);
	    	request.getRequestDispatcher(Constant.INDEX_JSP).forward(request, response);
	    }
	}

	public void init(FilterConfig fConfig) throws ServletException 
	{}
}
