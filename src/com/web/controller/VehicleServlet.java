package com.web.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.web.common.Constant;
import com.web.common.LoggerFactory;
import com.web.modal.*;
import com.web.objects.*;


@WebServlet("/Vehicle")
public class VehicleServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
	Vehicle v = null; 
	Vehicledao dao = new Vehicledao();
	List<Vehicle> l = new ArrayList<>();

	public VehicleServlet() {
		super();
	}




	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	    String no   = null ;
        String model = null;
        String type  = null;
        String color = null;
        
        super.service(request,this);
        
		try
		{

			if( mode!=null && mode.equals("I"))	    
			{

				no 	= request.getParameter("vehicle_no").trim();
				model =request.getParameter("vehicle_model").trim();
				type = request.getParameter("vehicle_type").trim();
				color = request.getParameter("vehicle_color").trim();

				v = new Vehicle(no, model, type, color);

				if( dao.insertVehicle(v) )
				{
					request.setAttribute( "msg", "Vehicle Added for "+no );
				}


			}	    

			if( mode!=null && mode.equals("U"))	    
			{

				no = request.getParameter("vehicle_no").trim();
				model =request.getParameter("vehicle_model").trim();
				type = request.getParameter("vehicle_type").trim();
				color = request.getParameter("vehicle_color").trim();

				v = new Vehicle(no, model, type, color); 

				if( dao.updateVehicle(v) ) 
				{
					request.setAttribute( "msg", "Updated successfully for "+no );
				}

			}

			if( mode!=null && mode.equals("D"))
			{
				no = request.getParameter("no").trim();
				System.out.println(" Inside Delete in Vehicle no "+no);

				if( new Routedao().delete(no) && new Driverdao().deleteDriver(no) )
				{
					if ( dao.deleteVehicle(no) )
						request.setAttribute("msg", "Deleted "+no);
					else
						request.setAttribute("msg", "Deletion failed");
				}
				else 
				{
					request.setAttribute("msg", "Deletion failed");
				}
					

			}

			l = dao.getAllVehicle();
			
			request.setAttribute("listUser", l);
		}
		catch(Exception e)
		{
		    logContent(e.toString(), LoggerFactory.ERROR, e);
		}
		finally
		{
		    if ( mode.trim().equals("dummy") ) 
		    {
		        request.getRequestDispatcher(Constant.VEHICLE_INSERT_JSP).forward(request, response);
		    }
		    else
		    {
		        request.getRequestDispatcher(Constant.VEHICLE_JSP).forward(request, response);   
		    }
		}

	}

}
