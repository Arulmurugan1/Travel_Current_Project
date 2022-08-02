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
@WebServlet("/Common")
public class CommonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommonServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("pickup", request.getParameter("pickup_from") );
			request.setAttribute("drop", request.getParameter("drop_at"));
			request.setAttribute("fare", request.getParameter("hFare"));
		}
		catch(Exception e ) { System.out.println(e);}
		finally
		{
			RequestDispatcher rd = request.getRequestDispatcher("bookinginsertform.jsp");
			rd.forward(request, response);
		}
		doGet(request, response);
	}

}
