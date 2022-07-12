package com.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommonServlet
 */
@WebServlet("/CommonServlet")
public class CommonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String hPickup			= "";
		String hDrop 			= "";
		String status			= request.getParameter("hStatus");
		String hFare 			= " ";
		try {
			if ( status.trim().equals("I") )
			{
				
				hPickup = request.getParameter("hPickup");
				hDrop   = request.getParameter("hDrop");
				hFare	= request.getParameter("hFare");
				request.setAttribute("pickup", hPickup);
				request.setAttribute("drop", hDrop);
				request.setAttribute("fare", hFare);
			}
			RequestDispatcher rd = request.getRequestDispatcher("bookinginsertform.jsp");
			rd.forward(request, response);
		}
		catch(Exception e ) {}
		doGet(request, response);
	}

}
