package com.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.common.CommonFactory;
import com.web.common.Constant;
import com.web.common.Generic;
import com.web.log4j.LoggerFactory;
import com.web.modal.Bookingdao;
import com.web.modal.Customerdao;
import com.web.objects.Booking;
import com.web.objects.Customer;

@WebServlet("/Booking")
public class BookingServlet extends CustomServlet{
    private static final long serialVersionUID = 1L;

    public BookingServlet() 
    {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {

        super.service(request, response);

        Booking b = null ;
        Customer c = null ;
        
        Bookingdao bookingDao = new Bookingdao() ;
        Customerdao customerDao = new Customerdao() ;
       
        String user = CommonFactory.isNull( request.getSession().getAttribute("user") )  ;

        try
        {
 
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

            switch (mode)
            {
                case "E":
                {
                    int id = Integer.parseInt(request.getParameter("booking_no"));

                    b = bookingDao.selectBooking(id);
                    request.setAttribute("user", b);
                    break;
                }
                case "I" :	    
                {
                    pickup 			= request.getParameter("pickup_from").trim();
                    drop 			= request.getParameter("drop_at").trim();
                    vehicle 		= request.getParameter("vehicle_no").trim();
                    customer_name 	= request.getParameter("customer_name").trim();
                    driver 			= request.getParameter("driver_id");
                    fare			= Double.parseDouble(request.getParameter("fare"));
                    age 			= request.getParameter("age").trim();
                    email			= request.getParameter("email").trim();
                    gender			= request.getParameter("gender").substring(0, 1);
                    phone           = request.getParameter("phone");

                    c	=new Customer(customer_name, pickup, drop, age, gender, email, phone);

                    int customerId = customerDao.insertCustomer(c) ;

                    if( customerId > 0 )
                    {

                        logContent("Customer Added " , LoggerFactory.INFO, null);

                        b= new Booking();
                        b.setPickup_from(pickup);
                        b.setDrop_at(drop);
                        b.setCustomer_id(customerId);
                        b.setVehicle_no(vehicle);
                        b.setDriver_id(driver);
                        b.setFare(fare);
                        b.setBooked_by(user);

                        int bookingNo = bookingDao.insertBooking(b);

                        if ( bookingNo > 0 )
                        {
                            request.setAttribute("result", bookingNo +","+customerId+","+fare);
                            logContent("Booking Added for No["+bookingNo +"] custId ["+customerId+"] fare["+fare+"]" , LoggerFactory.INFO, null);;
                        }
                        else 
                        {
                            request.setAttribute("msg", "Customer Added But Booking failed ");
                            request.setAttribute("result", " ");
                        }

                    }
                    else
                    {
                        request.setAttribute("msg", "Booking failed ");
                    }
                    break;
                }
                case  "U" :	    
                {
                    String status = request.getParameter("status");

                    int id = Integer.parseInt(request.getParameter("booking_no"));

                    if ( status.equals(""))
                    {
                        int customer_id =Integer.parseInt(request.getParameter("customer_id"));

                        b = new Booking();
                        b.setBooking_no(id);
                        b.setCustomer_id(customer_id);
                        b.setVehicle_no(vehicle);
                        b.setDriver_id(driver);

                        if( bookingDao.updateBooking(b))
                        {
                            request.setAttribute("msg", "Booking edit Success");
                        }
                        else
                        {
                            request.setAttribute("msg", "Booking edit failed");
                        }
                        break;
                    }
                    else
                    {
                        b = new Booking();
                        b.setBooking_no(id);
                        b.setStatus(status);
                        if( bookingDao.updateBooking(b))
                        {

                        }
                        else
                        {
                            request.setAttribute("msg", "Booking update failed");
                        }
                    }
                    break;
                }
                case "D" :
                {
                    int id 				= Integer.parseInt(request.getParameter("booking_no"));
                    int customerId		= Integer.parseInt(request.getParameter("customer_id"));


                    if( customerDao.deleteCustomer(customerId) && bookingDao.deleteBooking(id))
                    {
                        request.setAttribute("msg", "Booking No "+id+" deleted");
                        logContent("Booking deleted " , LoggerFactory.INFO, null);;
                    }
                    else
                    {
                        request.setAttribute("msg", "Booking failed for no. "+id);
                        logContent("Booking deletion failed " , LoggerFactory.ERROR, null);;
                    }
                    break;
                }
                case "dummy" :
                {
                    super.setParameters();
                }
                default :
                {
                    
                }
            }
            
            request.setAttribute("listUser", bookingDao.getAllBooking());
        } 
        catch (Exception e) 
        {
            request.setAttribute("msg", e.getLocalizedMessage() );
            logContent(e.getMessage(), LoggerFactory.ERROR, e);
        }
        finally
        {

        	try 
        	{
        		Generic.closeOpenConnections();
        	}
        	catch(Exception e ) {
        		logContent(e.getMessage(), LoggerFactory.ERROR, e);
        	}

        	if ( mode.trim().equals("") || mode.trim().equals("D") || mode.trim().equals("U") )
        	{
        		request.getRequestDispatcher(Constant.BOOKING_JSP).forward(request, response);
        	}
        	else
        	{
        		request.getRequestDispatcher(Constant.BOOKING_INSERT_JSP).forward(request, response);
        	}

        }
    }
}
