package com.web.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.web.modal.Logindao;

@WebServlet("/LoginInfo")
public class LoginInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Logindao data ;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String mode = request.getParameter("mode") == null ? "":request.getParameter("mode").trim();

        try
        {
            Enumeration<String> e = request.getParameterNames();
            data = new Logindao();

            switch(mode) {
                case "I":
                {

                }
                case "U":
                {

                }
                case "D":
                {

                }
                default :
                {
                    request.setAttribute("list", data.getAllUsers() );
                }
            }
            data.closeAll();
        }
        catch(Exception e )
        {
            e.printStackTrace();
            request.setAttribute("msg", e.getMessage());
        }
        finally
        {
            
            request.getRequestDispatcher("UserDetails.jsp").forward(request, response);
        }

    }


}
