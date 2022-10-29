package com.web.filter;

import java.io.IOException;
import java.util.Date;

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

//dispatcherTypes = {DispatcherType.FORWARD }
//, 

/**
 * Servlet Filter implementation class AuthFilter
 */

@WebFilter(urlPatterns ={"/*"})
public final class AuthFilter implements Filter{

	/**
	 * Default constructor. 
	 */
	public AuthFilter() 
	{

	}
	public void destroy() 
	{

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;

		HttpSession session = req.getSession() ;

		//	System.out.println("Session Id ["+session.getId()+"] Request Id"+req.getRequestedSessionId());
//		log.info("Session Id ["+session.getId()+"] Request Id"+req.getRequestedSessionId());
		if ( req !=null && ( req.getServletPath().contains(".css") || req.getServletPath().contains(".js") ) )
		{
			chain.doFilter(request, response);
		}
		else
		{
			System.out.println("--------------- Authentication starts --------------------");

			System.out.println("Time "+ new Date().toString());

			if ( session.isNew() )
			{
				System.out.println("Session Created "+ session.getId());
				session.setAttribute("localPath", req.getRequestURL() );
				session.setAttribute("sessionid", session.getId() );
			}

//			System.out.println(req.getRequestURL()   );
//			System.out.println(req.getServletPath()  );
//			System.out.println(req.getContextPath()  );
//			System.out.println(req.getRequestURI()   );  
//			
			req.getServletContext().setAttribute("localpath",req.getRequestURL());
			
			System.out.println("LocalPath :"+req.getServletContext().getAttribute("localpath"));

			System.out.println("--------------- Authentication Ends --------------------");

			if ( req.getServletPath().equals("/Login"))
			{
				chain.doFilter(request, response);
			}
			else if ( session.getAttribute("user") == null && session.getAttribute("role") == null )
			{
				request.getRequestDispatcher(Constant.INDEX_JSP).forward(request, response);
			}
			/*else if (( session != null && 
					session.getAttribute("localpath") != null && 
					session.getAttribute("localpath").equals("http://localhost:8080/Travel/") ) || 
					session.isNew())
			{
				System.out.println("--------------- Authentication Ends in index--------------------");
				request.getRequestDispatcher(Constant.INDEX_JSP).forward(request, response);
			}*/
			else
			{
//				System.out.println("--------------- Authentication Ends in chain--------------------");
				chain.doFilter(request, response);
			}	
		}
	}

	public void init(FilterConfig fConfig) throws ServletException 
	{}
}
