package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.web.common.AjaxProcess;
import com.web.common.Constant;
import com.web.common.LoggerFactory;
import com.web.modal.Bookingdao;
import com.web.objects.Booking;


@WebServlet("/Ajax")
@MultipartConfig 
public class AjaxServlet extends CustomServlet 
{
	private static final long serialVersionUID = 1L;
	static JSONArray data  = null;
	PrintWriter out  ;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 

		AjaxProcess ret = AjaxProcess.getInstance();
		ret.setHttpServlets(request,response);
		
		try
		{
			response.setHeader("Cache-control", "no-cache, no-store");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");

			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type");
			response.setHeader("Access-Control-Max-Age", "86400");
			
			response.setContentType("text/html;charset=UTF-8");  

			super.service(request,this, response);

			out = response.getWriter();
			
			logContent("Context Real Path  : "+getServletContext().getRealPath("/Images") , LoggerFactory.INFO, null);

			if( mode.equals("userProfileSubmit") )
			{
				logContent("Inside ajax" , LoggerFactory.INFO, null);

				request.setAttribute("msg", ret.getUploadResponse() ? "success" : "failed");
				request.setAttribute("action", "editProfile");
				
				request.getRequestDispatcher(Constant.HOME_JSP).forward(request, response);
				
			}
			else
			{
				logContent("Inside ajax" , LoggerFactory.INFO, null);
				
				if(mode.trim().equals("updateUserAccess"))
				{
					out.println( ret.setUserAcess());
				}
				else 
				{
					String vehicleNo = request.getParameter("vehicle_no");
					String vehicleModel = request.getParameter("vehicle_model");
					String status = request.getParameter("status");
					String booking_no = request.getParameter("booking_no");
					logContent("VehicleNo ["+vehicleNo+"] Vehicle Model ["+vehicleModel+"]" , LoggerFactory.DEBUG, null);;

					if ( vehicleNo!=null && vehicleNo.trim().length() > 0)
					{
						String no = ret.getInfo(vehicleNo).trim();

						if ( no !="" )
							out.print(no);
						else
							out.print("No Vehicle found");
					}
					if ( vehicleModel!=null && vehicleModel.trim().length()  > 0)
					{
						data = ret.getVehicleType(vehicleModel);
						if( data !=null &&  !data.toString().equals(""))
						{
							logContent("data found.." , LoggerFactory.INFO, null);
						}
						else 
						{
							logContent("No data found.." , LoggerFactory.INFO, null);
						}
						logContent(data.toString() , LoggerFactory.INFO, null);

						out.println(data.toString());
					}
					if ( status !=null && booking_no !=null )
					{
						Booking b = new Booking();
						b.setStatus(status);
						b.setBooking_no(Integer.parseInt(booking_no));

						boolean result = Bookingdao.getInstance().updateBooking(b);

						if ( result )
						{
							out.println("success");
						}
						else
						{
							out.println("failed");
						}
					}
				}
			}
		}
		catch(Exception s) {
			logContent("Exception in Ajax Servlet", LoggerFactory.ERROR, s);
			out.print("Exception in Ajax Servlet  "+s.getMessage());
		}
		finally
		{
			try {
				ret.closeAll();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
