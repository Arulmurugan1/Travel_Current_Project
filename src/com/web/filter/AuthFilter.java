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
//dispatcherTypes = {DispatcherType.FORWARD }
//, 
/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(urlPatterns = { 
				"/AuthFilter", 
				"/home.jsp",
		})
public final class AuthFilter implements Filter {

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

		HttpSession sess = req.getSession();
		
		Object user = sess.getAttribute("user");
		
		System.out.println("IP "+request.getRemoteAddr() + ", Time "+ new Date().toString() +" Session "+sess.isNew());
		System.out.println(user);
		if ( user != null || sess.isNew() )
		{
			chain.doFilter(request, response);
		}
		else
		{
			request.setAttribute("msg","Login First ...");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException 
	{
		//Get init parameter
		String testParam = fConfig.getInitParameter("test-param");

		//Print the init parameter
		System.out.println("Test Param: " + testParam);
	}

}
