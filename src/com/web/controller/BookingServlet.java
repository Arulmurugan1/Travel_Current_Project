package com.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.modal.Bookingdao;
import com.web.modal.Customerdao;
import com.web.objects.Booking;
import com.web.objects.Customer;

@WebServlet("/Booking")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookingServlet() {
		super();
	}




	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Bookingdao dao =new Bookingdao();
		Customerdao cdao = new Customerdao();
		Booking b = null ;
		Customer c = null ;

		String mode				= request.getParameter("mode");

		String pickup 			= "";
		String drop 			="";
		String vehicle 			="";
		String customer_name 	="";
		String driver 			= "";
		double fare				= 0;
		String age				= "";
		String email			= "";
		String gender			= "";
		String phone			= "";

		if(mode.equals("E"))
		{
			int id = Integer.parseInt(request.getParameter("booking_no"));

			b = dao.selectBooking(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("bookingupdateform.jsp");
			request.setAttribute("user", b);
			dispatcher.forward(request, response);

		}

		if( mode.equals("I")  )	    
		{
			pickup 			= request.getParameter("pickup_from").trim();
			drop 			= request.getParameter("drop_at").trim();
			vehicle 		= request.getParameter("vehicle_no").trim();
			customer_name 	= request.getParameter("customer_name").trim();
			driver = request.getParameter("driver_id");
			fare	= Double.parseDouble(request.getParameter("fare"));
			age 	= request.getParameter("age").trim();
			email	= request.getParameter("email").trim();
			gender	= request.getParameter("gender").substring(0, 1);
			phone	= request.getParameter("phone").trim();
			
			if( vehicle.trim().length() > 0 && vehicle.contains("-") ) 
				vehicle = vehicle.split("-")[0].trim();
			if( driver.trim().length() > 0 && driver.contains("-") )
				driver = driver.split("-")[0].trim();
			c	=new Customer(customer_name, pickup, drop, age, gender, email, phone);
			
			int customerId = cdao.insertCustomer(c) ;
			
			if( customerId > 0 )
			{
				b =new Booking(pickup, drop, customerId, vehicle, driver, fare);
				int bookingNo = dao.insertBooking(b);
				request.setAttribute("result", bookingNo +","+customerId+","+fare);
				
				RequestDispatcher rd = request.getRequestDispatcher("bookinginsertform.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("msg", "Booking failed ");
			}


		}




		if(mode.equals("U"))	    
		{
			int id = Integer.parseInt(request.getParameter("booking_no"));

			int customer_id =Integer.parseInt(request.getParameter("customer_id"));

			b = new Booking(id, customer_id , vehicle, driver) ;
			if( dao.updateBooking(b))
			{
				request.setAttribute("msg", "Booking edit Success");
			}
			else
			{
				request.setAttribute("msg", "Booking edit failed");
			}
		}

		if(mode.equals("D"))
		{
			int id 				= Integer.parseInt(request.getParameter("booking_no"));
			int customerId		= Integer.parseInt(request.getParameter("customer_id"));


			if(dao.deleteBooking(id) && cdao.deleteCustomer(customerId))
			{
				request.setAttribute("msg", "Booking deleted");
			}
			else
			{
				request.setAttribute("msg", "Booking failed to delete");
			}

		}

		request.setAttribute("listUser", dao.getAllBooking());

		if ( !(mode =="I" || mode == "E" ) )
		{

			RequestDispatcher rd = request.getRequestDispatcher("booking.jsp");
			rd.forward(request, response);
		}
		
		try{
			dao.closeAll();cdao.closeAll();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
