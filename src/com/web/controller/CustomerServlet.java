package com.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.common.Constant;
import com.web.modal.Customerdao;
import com.web.objects.Customer;



@WebServlet("/Customer")
public class CustomerServlet extends CustomServlet {
    private static final long serialVersionUID = 1L;


    public CustomerServlet() {
        super();

    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        Customerdao customerdao = new Customerdao();
        List<Customer> listUser = new ArrayList<>();
        String mode=request.getParameter("mode");
        try {

            super.service(request,this);

            if( mode !=null && mode.equals("QE"))
            {
                String id =request.getParameter("customer_id");
                request.setAttribute("customer_id", id);
            }
            else
            {
                listUser = customerdao.getAllCustomer();
                request.setAttribute("listUser", listUser);
                customerdao.closeAll();
                request.setAttribute("mode", mode);			
            }

        }
        catch(Exception e ) 
        {
            e.printStackTrace();
        }
        finally
        {
            if( mode !=null && mode.equals("QE"))
            {
                request.getRequestDispatcher(Constant.CUSTOMER_DETAIL_JSP).forward(request, response); 
            }
            else
            {
                request.getRequestDispatcher(Constant.CUSTOMER_JSP).forward(request, response); 
            }
        }
    }

}
