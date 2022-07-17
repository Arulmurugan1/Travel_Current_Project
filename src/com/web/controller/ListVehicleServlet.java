package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.modal.Vehicledao;
import com.web.objects.Vehicle;
import com.web.util.Dbmanager;


@WebServlet("/ListVehicleServlet")
public class ListVehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ListVehicleServlet() {
        super();
    }

	
	

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		Vehicle v = new Vehicle(); 
		Vehicledao dao = new Vehicledao();
		List<Vehicle> l = new ArrayList<>();

		String mode=request.getParameter("mode");

		if( mode!=null && mode.equals("E"))
		{
			String id =request.getParameter("no");


			try {
				v = dao.selectVehicle(id);
			} catch (Exception e) {

				e.printStackTrace();
			}
			request.setAttribute("user", v);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Vehicleupdateform.jsp");

			dispatcher.forward(request, response);

		}

		if( mode!=null && mode.equals("I"))	    
		{
		
			String no = request.getParameter("vehicle_no").trim();
			String model =request.getParameter("vehicle_model").trim();
			String type = request.getParameter("vehicle_type").trim();
			String color = request.getParameter("vehicle_color").trim();

			v = new Vehicle(no, model, type, color);

			System.out.println(no +"-"+model+"-"+type+"-"+color);

			if(dao.insertVehicle(v))
			{
				request.setAttribute("msg", "Added Success");
			}


		}	    

		if( mode!=null && mode.equals("U"))	    
		{

			String no = request.getParameter("vehicle_no");
			String model =request.getParameter("vehicle_model");
			String type = request.getParameter("vehicle_type");
			String color = request.getParameter("vehicle_color");

			v = new Vehicle(no, model, type, color);		

			boolean success = dao.updateVehicle(v);

			if(success) {

				l = dao.getAllVehicle();


				request.setAttribute("listUser", l);
				RequestDispatcher rd = request.getRequestDispatcher("Vehicle.jsp");
				rd.forward(request, response);
			}

		}

		if( mode!=null && mode.equals("D"))
		{
			String id = request.getParameter("no");


			if(dao.deleteVehicle(id));
			{
				request.setAttribute("msg", "Deleted Success");
			}

		}

		try
		{
			l = dao.getAllVehicle();

			request.setAttribute("listUser", l);
			RequestDispatcher rd = request.getRequestDispatcher("Vehicle.jsp");
			rd.forward(request, response);
		}catch(Exception e) {
			System.out.println("Request Dispatched..");
		}

		if ( mode !=null && !mode.equals("N"))
			Dbmanager.closeConnection();

	}

}
