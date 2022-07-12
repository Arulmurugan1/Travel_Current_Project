package com.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.web.modal.Bookingdao;
import com.web.modal.Customerdao;
import com.web.modal.Driverdao;
import com.web.modal.Vehicledao;
import com.web.objects.Booking;
import com.web.objects.Customer;
import com.web.objects.Driver;
import com.web.objects.Vehicle;

@WebServlet("/ListBookingServlet")
public class ListBookingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public Bookingdao bookingdao =null;
    public Customerdao customerdao =null;
    public List<Booking> listUser =null;
    public Booking existingUser =new Booking();
    public Customer cust1		=new Customer();
    public Driverdao driverdao = null;
    public Vehicledao vehicledao =null;
    public List<Driver> listdriver =null;
    public List<Vehicle> listvehicle =null;
    
    public ListBookingServlet() {
        super();
        bookingdao =new Bookingdao();
        customerdao =new Customerdao();
        driverdao =new Driverdao();
        vehicledao = new Vehicledao();
    }

	
    
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{



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
		
		
		if(mode.equals("Q"))
		{
			listUser = bookingdao.getAllBooking();
			request.setAttribute("listUser", listUser);
			RequestDispatcher rd = request.getRequestDispatcher("booking.jsp");
			rd.forward(request, response);
		}

		if(mode.equals("N"))	    
		{
			RequestDispatcher rd = request.getRequestDispatcher("bookinginsertform.jsp");
			rd.forward(request, response);

		}

		if(mode.equals("E"))
		{
			int id = Integer.parseInt(request.getParameter("booking_no"));

			existingUser = bookingdao.selectBooking(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("bookingupdateform.jsp");
			request.setAttribute("user", existingUser);
			dispatcher.forward(request, response);

		}

		if( mode.equals("I")  )	    
		{
			pickup 			= request.getParameter("pickup_from").trim();
			drop 			= request.getParameter("drop_at").trim();
			vehicle 		= request.getParameter("vehicle_no").trim();
			customer_name 	= request.getParameter("customer_name").trim();


			if(vehicle.trim().length() > 0 && vehicle.contains("-")) {
				String arr[] = vehicle.split("-");
				vehicle =arr[0].trim();
			}

			driver = request.getParameter("driver_id");

			if(driver.trim().length() > 0 && driver.contains("-")) {
				String arr[] = driver.split("-");
				driver =arr[0].trim();
			}

			fare	= Double.parseDouble(request.getParameter("fare"));
			age 	= request.getParameter("age").trim();
			email	= request.getParameter("email").trim();
			gender	= request.getParameter("gender").substring(0, 1);
			phone	= request.getParameter("phone").trim();
			cust1	=new Customer(customer_name, pickup, drop, age, gender, email, phone);

				if(customerdao.insertCustomer(cust1) )
				{
					cust1 = customerdao.selectCustomerByName(customer_name); 

					int cid =cust1.getCustomer_id();

					existingUser =new Booking(pickup, drop, cid, vehicle, driver, fare);
					
					bookingdao.insertBooking(existingUser);

					existingUser = bookingdao.selectBookingByCustomerId(cid);

					request.setAttribute("result", existingUser.getBooking_no()+","+cid+","+existingUser.getFare());
					
					listUser = bookingdao.getAllBooking();
					request.setAttribute("listUser", listUser);
					request.setAttribute("msg", "Booking no " +existingUser.getBooking_no() + " Added" );
					RequestDispatcher rd = request.getRequestDispatcher("bookinginsertform.jsp");
					rd.forward(request, response);
				}
				else
				{
					request.setAttribute("msg", "Booking failed ");
					RequestDispatcher rd = request.getRequestDispatcher("booking.jsp");
					rd.forward(request, response);
				}
			
			
		}




		if(mode.equals("U"))	    
		{
			int id = Integer.parseInt(request.getParameter("booking_no"));

			int customer_id =Integer.parseInt(request.getParameter("customer_id"));

			Booking book = new Booking(id, customer_id , vehicle, driver) ;
			if(bookingdao.updateBooking(book))
			{
				
				request.setAttribute("msg", "Booking edit Success");
				listUser = bookingdao.getAllBooking();
				request.setAttribute("listUser", listUser);
				RequestDispatcher rd = request.getRequestDispatcher("booking.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("msg", "Booking edit failed");
				RequestDispatcher rd = request.getRequestDispatcher("booking.jsp");
				rd.forward(request, response);
			}
		}

		if(mode.equals("D"))
		{
			int id 				= Integer.parseInt(request.getParameter("booking_no"));
			int customerId		= Integer.parseInt(request.getParameter("customer_id"));


			if(bookingdao.deleteBooking(id) && customerdao.deleteCustomer(customerId))
			{
				listUser = bookingdao.getAllBooking();
				request.setAttribute("listUser", listUser);
				request.setAttribute("msg", "Booking deleted");
				RequestDispatcher rd = request.getRequestDispatcher("booking.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("msg", "Booking failed to delete");
				RequestDispatcher rd = request.getRequestDispatcher("booking.jsp");
				rd.forward(request, response);
			}

		}

		
		
	}

}
