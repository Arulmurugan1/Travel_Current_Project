package com.web.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import com.web.modal.*;
import com.web.objects.*;


@WebServlet("/Vehicle")
public class VehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Vehicle v = null; 
	Vehicledao dao = new Vehicledao();
	List<Vehicle> l = new ArrayList<>();

	public VehicleServlet() {
		super();
	}




	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			String no 	 = null ;
			String model = null;
			String type  = null;
			String color = null;

			String mode = request.getParameter("mode");
			
			System.out.println("Mode ::"+mode);

			if( mode!=null && mode.equals("I"))	    
			{

				no 	= request.getParameter("vehicle_no").trim();
				model =request.getParameter("vehicle_model").trim();
				type = request.getParameter("vehicle_type").trim();
				color = request.getParameter("vehicle_color").trim();

				v = new Vehicle(no, model, type, color);

				System.out.println(no +"-"+model+"-"+type+"-"+color);

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
			new Driverdao().closeAll();new Routedao().closeAll();
			RequestDispatcher rd = request.getRequestDispatcher("Vehicle.jsp");
			rd.forward(request, response);
		}
		catch(Throwable e)
		{
			System.out.println("Exception in Vehicle Servlet "+e);
		}

	}

}
