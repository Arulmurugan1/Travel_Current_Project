package com.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.Messaging.SyncScopeHelper;

import com.web.modal.Driverdao;
import com.web.modal.Vehicledao;
import com.web.objects.Driver;
import com.web.objects.Vehicle;
import com.web.util.Dbmanager;


@WebServlet("/Driver")
public class ListDriverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public ListDriverServlet() {
		super();

	}



	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Driver v = new Driver();
		Driverdao dao = new Driverdao();
		List<Driver> l = new ArrayList<Driver>();

		String name ="";
		String gender ="";
		String city ="";
		String phone ="";
		String no ="";
		int age =0;
		int id =0;


		String mode=request.getParameter("mode");


		if( mode !=null && mode.equals("N"))	    
		{
			RequestDispatcher rd = request.getRequestDispatcher("driverform.jsp");
			rd.forward(request, response);

		}

		if( mode !=null && mode.equals("E"))
		{
			id = Integer.parseInt(request.getParameter("driver_id"));		        

			try {
				v = dao.selectDriver(id);
			} catch (Exception e) {

				e.printStackTrace();
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("driverform.jsp");
			request.setAttribute("listUser", v);
			dispatcher.forward(request, response);

		}

		if( mode !=null && mode.equals("I"))	    
		{

			name =request.getParameter("driver_name");
			gender = request.getParameter("gender");
			city = request.getParameter("city");
			phone = request.getParameter("phone");
			no = request.getParameter("vehicle_no");
			age = Integer.parseInt(request.getParameter("age"));

			v = new Driver(name, gender, city, phone, no, age, id);
			if (dao.insertDriver(v) )
			{
				request.setAttribute("msg", "success"); 
			}
			else {
				request.setAttribute("msg", "Failed");
				RequestDispatcher rd = request.getRequestDispatcher("driverform.jsp");
				rd.forward(request, response); 
			}



		}	    

		if( mode !=null && mode.equals("U"))	    
		{

			name =request.getParameter("driver_name");
			gender = request.getParameter("gender");
			city = request.getParameter("city");
			phone = request.getParameter("phone");
			no = request.getParameter("vehicle_no");
			age = Integer.parseInt(request.getParameter("age"));
			id  = Integer.parseInt(request.getParameter("driver_id"));

			v = new Driver(name, gender, city, phone, no, age, id);

			if( dao.updateDriver(v) ) 
			{			    
				request.setAttribute("msg", "success");
			} 
			else 
			{
				request.setAttribute("msg", "Failed");
				RequestDispatcher rd = request.getRequestDispatcher("driverform.jsp");
				rd.forward(request, response); 
			}
		}

		if( mode !=null && mode.equals("D"))
		{
			id  = Integer.parseInt(request.getParameter("driver_id"));


			if(dao.deleteDriver(id))
			{			    
				request.setAttribute("msg", "success");
			}
			else 
			{
				request.setAttribute("msg", "Failed");
				RequestDispatcher rd = request.getRequestDispatcher("driverform.jsp");
				rd.forward(request, response); 
			}

		}
		try
		{

			l = dao.getAllDriver();
			request.setAttribute("listUser", l);
			RequestDispatcher rd = request.getRequestDispatcher("driver.jsp");
			rd.forward(request, response);
		}catch(Exception e) {System.out.println("Request dispatched");}
		Dbmanager.closeConnection();
	}
}
