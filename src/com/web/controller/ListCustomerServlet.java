package com.web.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.modal.Customerdao;
import com.web.objects.Customer;
import com.web.util.Dbmanager;



@WebServlet("/ListCustomerServlet")
public class ListCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ListCustomerServlet() {
        super();
        
    }

	
	

	
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    	Customerdao customerdao = new Customerdao();
    	List<Customer> listUser = new ArrayList<>();

    	String mode=request.getParameter("mode");

    	if( mode !=null && mode.equals("QE"))
    	{
    		String id =request.getParameter("customer_id");
    		listUser = customerdao.selectCustomerById(id);
    		request.setAttribute("listUser", listUser);
    	}
    	else
    	{
    		listUser = customerdao.getAllCustomer();
    	}
    	request.setAttribute("listUser", listUser);
    	RequestDispatcher rd = request.getRequestDispatcher("customer.jsp");
    	rd.forward(request, response);
    	
    	Dbmanager.closeConnection();

    	//
    	//		if(mode.equals("I"))	    
    	//		{
    	//			  
    	//			        String name = request.getParameter("customer_name");
    	//			        String city =request.getParameter("city");
    	//			        
    	//			        Customer c = new Customer(name, city);
    	//			        customerdao.insertCustomer(c);
    	//			        	try {
    	//							listUser = customerdao.getAllCustomer();
    	//						} catch (SQLException e) {
    	//							
    	//							e.printStackTrace();
    	//						}
    	//				        request.setAttribute("listUser", listUser);
    	//				        RequestDispatcher rd = request.getRequestDispatcher("ListCustomer/customer.jsp");
    	//				        rd.forward(request, response);
    	//				    }
    	//			        
    	//			        
    	//			    
    	//
    	//		if(mode.equals("U"))	    
    	//		{
    	//			       
    	//			        String name = request.getParameter("customer_name");
    	//			        String city = request.getParameter("city");
    	//			        
    	//
    	//			        Customer c = new Customer(name, city);
    	//			        if(customerdao.updateCustomer(c))
    	//			       {
    	//			 //Again Query the Db
    	//			        try {
    	//						listUser = customerdao.getAllCustomer();
    	//					} catch (SQLException e) {
    	//						
    	//						e.printStackTrace();
    	//					}
    	//			        request.setAttribute("listUser", listUser);
    	//			        RequestDispatcher rd = request.getRequestDispatcher("ListCustomer/customer.jsp");
    	//			        rd.forward(request, response);
    	//			    }
    	//		}
    	//
    	//		if(mode.equals("D"))
    	//		{
    	//			        int id = Integer.parseInt(request.getParameter("customer_id"));
    	//			        
    	//		
    	//			        if(customerdao.deleteCustomer(id));
    	//				       {
    	//				 //Again Query the Db
    	//				        try {
    	//							listUser = customerdao.getAllCustomer();
    	//						} catch (SQLException e) {
    	//							
    	//							e.printStackTrace();
    	//						}
    	//				        request.setAttribute("listUser", listUser);
    	//				        RequestDispatcher rd = request.getRequestDispatcher("ListCustomer/customer.jsp");
    	//				        rd.forward(request, response);
    	//				    }
    	//		
    	//	}


    }

}
