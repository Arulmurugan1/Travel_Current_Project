package com.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;

import com.web.Factory.HibernateHelper;
import com.web.modal.Routedao;
import com.web.objects.Route;


@WebServlet("/RouteNew")
public class RouteNewServlet extends HttpServlet {
	Route r = null;
	Routedao dao  = new Routedao();
	Criteria c = null ;

	private static final long serialVersionUID = 1L;

	public RouteNewServlet() 
	{
		super();
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			String start ="";
			String end ="";
			String no ="";
			double fare = 0;

			String mode = request.getParameter("mode").trim();
			System.out.println("mode"+mode);

			if( mode !=null && !mode.equals(""))	    
			{
				System.out.println("Inside if mode");

				start =request.getParameter("start").trim();
				end   = request.getParameter("end").trim();
				no = request.getParameter("vehicle_no").trim();

				if ( request.getParameter("fare").trim() !="")
					fare = Double.parseDouble( request.getParameter("fare").trim() );

				System.out.println("Start["+start+"] End["+end+"] No["+no+"] Fare["+fare+"]");

				r = new Route(no, start, end,fare);

				if ( dao.check(r).length() == 0 )
				{

					if ( HibernateHelper.Operation(r,mode) )
					{	
						String msg ="";
						System.out.println("Inside After Operation");

						if ( mode.equalsIgnoreCase("I") )
						{
							msg="added";
						}
						if ( mode.equalsIgnoreCase("U") )
						{
							msg="updated";
						}
						if ( mode.equalsIgnoreCase("D") )
						{
							msg="deleted";
						}
						request.setAttribute("msg", " Routes "+msg+" for "+no);
					}	
					else 
					{
						request.setAttribute("msg", "Something Went Wrong! Please try again.") ;
					}
				}
				else
				{
					request.setAttribute("msg", "Routes already added to "+dao.check(r)) ;
				}
			}

			c= HibernateHelper.getSession().createCriteria(Route.class); //passing Class class argument
			request.setAttribute("list", c.list() );
			RequestDispatcher rd = request.getRequestDispatcher("route.jsp");
			rd.forward(request, response);
		}catch(Exception e ) {e.printStackTrace();}
	}

}