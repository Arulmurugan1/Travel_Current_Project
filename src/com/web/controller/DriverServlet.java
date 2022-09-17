package com.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.modal.Driverdao;
import com.web.objects.Driver;


@WebServlet("/Driver")
public class DriverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public DriverServlet() {
		super();

	}



	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Driver v = new Driver();
		Driverdao dao = new Driverdao();
		List<Driver> l = new ArrayList<Driver>();

		String mode=request.getParameter("mode") , message = "";
		System.out.println("mode Driver Servlet ::"+mode);

		try
		{
			if ( !mode.trim().equals("N") && !mode.trim().equals("")) 
			{
				String name   = request.getParameter("driver_name");
				String gender = request.getParameter("gender");
				String city   = request.getParameter("city");
				String phone  = request.getParameter("phone");
				String no     = request.getParameter("vehicle_no");
				int age    	  = Integer.parseInt( request.getParameter("age") );	

				if( mode !=null && mode.equals("E"))
				{
					int id 		  = Integer.parseInt( request.getParameter("driver_id") );
					v = dao.selectDriver(id);
					request.setAttribute("listUser", v);
				}
				if( mode !=null && mode.equals("I"))	    
				{
					v = new Driver(name, gender, city, phone, no, age, 0);
					if (dao.insertDriver(v) )
					{
						message ="success";
					}
					else 
					{
						message ="Failed";
					}
				}	    

				if( mode !=null && mode.equals("U"))	    
				{
					int id = Integer.parseInt( request.getParameter("driver_id") );
					v = new Driver(name, gender, city, phone, no, age, id);

					if( dao.updateDriver(v) ) 
					{			    
						message ="success";
					} 
					else 
					{
						message ="Failed";
					}
				}

				if( mode !=null && mode.equals("D"))
				{
					no  = request.getParameter("vehicle_no");

					if(dao.deleteDriver(no))
					{			    
						message ="success";
					}
					else 
					{
						message ="Failed";
					}

				}
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally {
			request.setAttribute("msg",message);

			if ( message == "success" || mode.equals("") )
			{
				l = dao.getAllDriver();
				request.setAttribute("listUser", l);
				request.getRequestDispatcher("driver.jsp").forward(request, response);
			}
			else
				request.getRequestDispatcher("driverform.jsp").forward(request, response);
		}
	}
}
