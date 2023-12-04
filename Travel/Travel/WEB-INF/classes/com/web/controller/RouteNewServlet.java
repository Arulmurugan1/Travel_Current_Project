package com.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;

import com.web.Factory.HibernateHelper;
import com.web.common.CommonFactory;
import com.web.common.Constant;
import com.web.log4j.LoggerFactory;
import com.web.modal.Routedao;
import com.web.objects.Route;


@WebServlet("/RouteNew")
public class RouteNewServlet extends CustomServlet {
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
        try
        {
            String start ="";
            String end ="";
            String no ="";
            double fare = 0;
            
            super.service(request,response);
            
            if( mode !=null && !mode.equals(""))	    
            {
                logContent("Inside if mode",LoggerFactory.DEBUG , null);

                start = CommonFactory.isNull(request.getParameter("start"));
                end   = CommonFactory.isNull(request.getParameter("end"));
                no = CommonFactory.isNull(request.getParameter("vehicle_no"));

                if ( CommonFactory.isNull(request.getParameter("fare")) !="")
                    fare = Double.parseDouble( request.getParameter("fare").trim() );
                
                r = new Route(no, start, end,fare);
                
                String checkRoutes = dao.check(r); 

                if ( checkRoutes.length() == 0 )
                {

                    if ( HibernateHelper.Operation(r,mode) )
                    {	
                        String msg ="";

                        if ( mode.equalsIgnoreCase("I") )
                            msg="Added";
                        if ( mode.equalsIgnoreCase("U") )
                            msg="Updated";
                        if ( mode.equalsIgnoreCase("D") )
                            msg="Deleted";
                        request.setAttribute("msg", " Routes "+msg+" for "+no);
                    }	
                    else 
                    {
                        request.setAttribute("msg", "Something Went Wrong! Please try again.") ;
                    }
                }
                else
                {
                    request.setAttribute("msg", "Routes already added to "+checkRoutes) ;
                }
            }
        }
        catch(Exception e ) 
        {
            request.setAttribute("msg",e.getMessage()) ;
            logContent(e.toString(), LoggerFactory.ERROR, e);
        }
        finally
        {
            c= HibernateHelper.getSession().createCriteria(Route.class); //passing class argument
            request.setAttribute("list", c.list() );
            request.getRequestDispatcher(Constant.ROUTE_JSP).forward(request, response);
        }
    }

}
