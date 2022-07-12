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
       public Vehicle v;
       public Vehicledao dao ;
       public List<Vehicle> l = new ArrayList<>();
    
    public ListVehicleServlet() {
        super();
        v= new Vehicle();
        dao =new Vehicledao();
        
    }

	
	

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{




		String mode=request.getParameter("mode");

		if(mode.equals("Q"))
		{

			l = dao.getAllVehicle();

			request.setAttribute("listUser", l);
			System.out.println(l);
			RequestDispatcher rd = request.getRequestDispatcher("Vehicle.jsp");
			rd.forward(request, response);
		}

		if(mode.equals("N"))	    
		{
			Connection con = null;
			String storeArray = "";
			try {
				con = Dbmanager.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement("select * from car");
				ResultSet rs = preparedStatement.executeQuery();   
				

				while (rs.next()) {
					storeArray += rs.getString("vehicle_no")+",";
				}
				storeArray =storeArray.substring(0,storeArray.length()-1);
			}
			catch (Exception e) {} 
			finally {
				try {
					if (con != null && !con.isClosed())
					{
						con.close();
						System.out.println();
					}
				} catch (SQLException e) {

				}
			}
			request.setAttribute("Vehicles", storeArray);
			RequestDispatcher rd = request.getRequestDispatcher("Vehicleinsertform.jsp");
			rd.forward(request, response);

		}
		

		if(mode.equals("E"))
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

		if(mode.equals("I"))	    
		{

			String no = request.getParameter("vehicle_no");
			String model =request.getParameter("vehicle_model");
			String type = request.getParameter("vehicle_type");
			String color = request.getParameter("vehicle_color");

			v = new Vehicle(no, model, type, color);

			System.out.println(no +"-"+model+"-"+type+"-"+color);

			if(dao.insertVehicle(v))
			{
				l = dao.getAllVehicle();
				request.setAttribute("listUser", l);
				RequestDispatcher rd = request.getRequestDispatcher("Vehicle.jsp");
				rd.forward(request, response);
			}


		}	    

		if(mode.equals("U"))	    
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

		if(mode.equals("D"))
		{
			String id = request.getParameter("no");


			if(dao.deleteVehicle(id));
			{


				l = dao.getAllVehicle();

				request.setAttribute("listUser", l);
				RequestDispatcher rd = request.getRequestDispatcher("Vehicle.jsp");
				rd.forward(request, response);
			}

		}



	}

}
