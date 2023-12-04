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
import com.web.log4j.LoggerFactory;
import com.web.util.Dbmanager;

@WebFilter(urlPatterns ={"/*"})
public final class AuthFilter implements Filter
{
	private HttpServletRequest request ;
	private HttpSession session ;
	private boolean redirect;

	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		redirect = false ;

		try 
		{

			this.request = (HttpServletRequest) req ;
			this.session = request.getSession() ;
			
			Generic.setRequest(request, session);

			if ( getPathStatus() )
			{		
				redirect = true ;
				return;
			}
			
			if (  request.getServletPath().contains(".")   )
			{
				chain.doFilter(req, response);
			}
			else
			{
				
				if( LoggerFactory.checkLogPathStatus() )
				{
					System.out.println( " LoggerFactory .... isLog4jInitiated ["+LoggerFactory.isLog4jInitiated+"]");
					
					if( ! LoggerFactory.isLog4jInitiated )
					{
						LoggerFactory.initLog4j();
					}
					
					System.out.println( " LoggerFactory .... isConsoleLogInitiated ["+LoggerFactory.isConsoleLogInitiated+"]");
					
					if( session.getAttribute("printStream") == null )
					{
						LoggerFactory.setConsoleLog();
					}

				}
				
				/* logProcess.doCheckLog4jLogs();  -- Commented as Logger Configuration is set by java to avoid wrong file location log */
				
				if ( session.isNew() )
				{
					Generic.logContent("Session Created "+ session.getId(),LoggerFactory.DEBUG ,this );
					session.setAttribute("localPath", request.getRequestURL() );
					session.setAttribute("sessionid", session.getId() );
				}

				req.getServletContext().setAttribute("localpath",request.getRequestURL());
				chain.doFilter(req, response);
			}
		
		}
		catch(Exception e)
		{
			request.setAttribute("msg", e.getMessage());
			System.out.println("Auth Filter Exception : "+e);
			e.printStackTrace();
			redirect = true ;
		}
		finally
		{
			System.out.println("Response Commited for > "+request.getRequestURI()+" ["+response.isCommitted()+"] Redirect to Login Page ["+(redirect ? "Yes" : "No")+"]");
		
			if(!response.isCommitted() && redirect)
			{
				if(  !CommonFactory.isBlankCheck(Dbmanager.error))
				{
					request.setAttribute("msg", Dbmanager.error);
					Dbmanager.error = "";
				}
				
				request.getRequestDispatcher(Constant.INDEX_JSP).forward(request, response);
			}
		}
	}

	private boolean getPathStatus() 
	{
		String url = request.getRequestURI() ;
		
		boolean checkStatus = !( url.contains("Login") || url.contains(".") ) ;
		
		System.out.println( " Url [" + url + "] checkStatus :" + checkStatus );
		
		if ( checkStatus )
		{
			checkStatus =  session == null || session.getAttribute("user_id") == null ;
			System.out.println(" session checkStatus :" + checkStatus );
		}
		
		if(session == null)
		{
			request.setAttribute("msg", "Error session becomes null");
			System.out.println(" Redirect for null session :)");
			
			checkStatus = true ;
		}
		
		return checkStatus;
	}

	public void init(FilterConfig fConfig) throws ServletException 
	{

	}
}
