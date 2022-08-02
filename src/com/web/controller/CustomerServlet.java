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



@WebServlet("/Customer")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public CustomerServlet() {
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
    	request.setAttribute("mode", mode);
    	RequestDispatcher rd = request.getRequestDispatcher("customer.jsp");
    	rd.forward(request, response);    
    }

}
