package com.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.common.Constant;
import com.web.common.LoggerFactory;
import com.web.common.CommonFactory;
import com.web.modal.Routedao;
import com.web.objects.Route;


@WebServlet("/Route")
public class RouteServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;

	public RouteServlet() 
	{
		super();
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	    super.service(request,this, response);
	    
	    Route r = null ;
	    Routedao dao  = new Routedao();
	    
	    dao.setHttpServlets(request, response);
	    
		try
		{
			String start ="";
			String end ="";
			String no ="";
			 double fare = 0;

			if(mode.equals("I"))	    
			{

				start = CommonFactory.isNull(request.getParameter("start"));
                end   = CommonFactory.isNull(request.getParameter("end"));
                no = CommonFactory.isNull(request.getParameter("vehicle_no"));

                if ( CommonFactory.isNull(request.getParameter("fare")) !="")
                    fare = Double.parseDouble( request.getParameter("fare").trim() );
                
                r = new Route(no, start, end,fare);
				
				if ( start !="" && end !="" && no!="" && dao.check(r).length() == 0 )
				{
					if (dao.insert(r) )
					{		
						request.setAttribute("msg", " Routes Added for "+no);
					}
					else {
						request.setAttribute("msg", "Something Went Wrong failed to add") ;
					}
				}
				else
				{
					request.setAttribute("msg", "Route already added to "+dao.check(r)) ;
				}

			}	    

			if(mode.equals("U"))	    
			{

				start =request.getParameter("ustart");
				end   = request.getParameter("uend");
				no = request.getParameter("uvehicle_no");

				r = new Route(no, start, end);
				if ( dao.check(r).length() > 0  )
				{
					if (dao.update(r) )
					{
						request.setAttribute("msg", "Updation success");
					}
					else {
						request.setAttribute("msg", "Failed to Update");
					}
				}
				else
				{
					request.setAttribute("msg", "Route already exists");
				}

			}

			if(mode.equals("D"))
			{
				no  = request.getParameter("vehicle_no");


				if(dao.delete(no))
				{			    
					request.setAttribute("msg", "success");
				}
				else 
				{
					request.setAttribute("msg", "Failed");
				}


			}
			request.setAttribute("list",dao.getAllRoutes());
			dao.closeAll();
		}
		catch( Throwable e) 
		{
			logContent("Exception in Route ", LoggerFactory.ERROR, e);
		}
		finally
		{
			RequestDispatcher rd = request.getRequestDispatcher(Constant.ROUTE_JSP);
			rd.forward(request, response);
		}
	}
}
