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
		

		try
		{
			String name   = request.getParameter("driver_name");
			String gender = request.getParameter("gender");
			String city   = request.getParameter("city");
			String phone  = request.getParameter("phone");
			String no     = request.getParameter("vehicle_no");
			int age    	  = Integer.parseInt( request.getParameter("age")==null?"0":request.getParameter("age") );
			int id 		  = Integer.parseInt( request.getParameter("driver_id") == null ?"0":request.getParameter("driver_id") );	
			
			if( mode !=null && mode.equals("E"))
			{
				v = dao.selectDriver(id);
				request.setAttribute("listUser", v);
			}
			if( mode !=null && mode.equals("I"))	    
			{
				v = new Driver(name, gender, city, phone, no, age, id);
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
			l = dao.getAllDriver();
			request.setAttribute("listUser", l);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally {
			request.setAttribute("msg",message);
			
			if ( message == "success")
				request.getRequestDispatcher("driver.jsp").forward(request, response);
			else
				request.getRequestDispatcher("driverform.jsp").forward(request, response);
		}
	}
}
