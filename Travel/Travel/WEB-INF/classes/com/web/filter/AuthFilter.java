package com.web.filter;

import java.io.IOException;
import java.sql.Connection;

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
		boolean redirect = false ;
		
		Connection con = Dbmanager.getConnection() ;
		
		try 
		{
		
			HttpServletRequest req = (HttpServletRequest) request;

			HttpSession session = req.getSession() ;

			if ( session == null )
			{
				redirect = true ;
				System.out.println(" Redirect from null session :)");
				return;
			}
			
			if( con == null)
			{
				request.setAttribute("msg", Dbmanager.error);
				System.out.println(" Redirect from Connection is null :)");
				redirect = true ;
				return;
			}
			
			con.close();
			
			if(Generic.session == null)
			{
				Generic.setRequest(req, session);
			}
			
			System.out.println(" Request Path : "+req.getServletPath());
			
			System.out.println(" PrintStream [" +session.getAttribute("printStream")+"]");

			if ( req.getServletPath().equals("/Login"))
			{
				System.out.println(" User Id : from auth filter "+req.getParameter("txtUser"));
				System.out.println(" User Id : from Session filter "+session.getAttribute("txtUser"));

				String propUser = CommonFactory.isNull(req.getParameter("txtUser"));
				String sesUser = CommonFactory.isNull(session.getAttribute("user_id"));

				if(propUser.equals(""))
				{
					System.out.println(" Redirect from propUser in Empty :)");
					redirect = true ;
					return;
				}
					

				if( !propUser.equals(sesUser)  && propUser.trim().length() != 0 )
				{
					session.setAttribute("user_id", propUser);
					
					if(new Generic().setLogConsoleProperties())
					{
						Generic.closeOpenConnections();
						chain.doFilter(request, response);
					}
					else
					{
						Generic.logContent("Something went Wrong in LogProperties Set :",LoggerFactory.DEBUG , null, this);
						redirect = true ;
					}

				}

			}
			
			if( ! (Generic.LOG_PATH_TODAY.equals(Dbmanager.getKeyProperties(Constant.LOG_PROP_PATH)) ))
				new Generic().setLogConsoleProperties();
			
			if( session.getAttribute("printStream") == null)
			{
				new Generic().setSystemOutLogs();
			}
			
			if ( req !=null && ( req.getServletPath().contains(".css") || req.getServletPath().contains(".js") )  )
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
				chain.doFilter(request, response);
			}

		}
		catch(Exception e)
		{
			request.setAttribute("msg", e.getMessage());
			Generic.logContent("Auth Filter Exception :",LoggerFactory.DEBUG , e, this);
			redirect = true ;
		}
		finally
		{
			System.out.println("Response Commited ["+response.isCommitted()+"] Redirect to Login Page ["+(redirect ? "Yes" : "No")+"]");
			if(!response.isCommitted() && redirect)
				request.getRequestDispatcher(Constant.INDEX_JSP).forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException 
	{
		
	}
}
