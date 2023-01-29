package com.web.filter;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.web.common.Constant;

@WebFilter(urlPatterns ={"/*"})
public final class AuthFilter implements Filter{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;

		HttpSession session = req.getSession() ;
		
		if ( req !=null && ( req.getServletPath().contains(".css") || req.getServletPath().contains(".js") ) )
		{
			chain.doFilter(request, response);
		}
		else
		{
		    session.setMaxInactiveInterval(50000); 
		    
			if ( session.isNew() )
			{
				System.out.println("Session Created "+ session.getId() );
				session.setAttribute("localPath", req.getRequestURL() );
				session.setAttribute("sessionid", session.getId() );
			}

			req.getServletContext().setAttribute("localpath",req.getRequestURL());
			
			if ( req.getServletPath().equals("/Login"))
			{
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
	}

	public void init(FilterConfig fConfig) throws ServletException 
	{}
}
