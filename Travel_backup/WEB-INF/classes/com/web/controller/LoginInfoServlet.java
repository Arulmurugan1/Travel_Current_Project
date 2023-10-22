package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.common.Constant;
import com.web.common.LoggerFactory;
import com.web.modal.Logindao;

@WebServlet("/LoginInfo")
public class LoginInfoServlet extends CustomServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        
        super.service(request,this, response);
        
        Logindao data = null;
        
        PrintWriter out = response.getWriter();

        try
        {
            
            data = new Logindao();
            
            data.setHttpServlets(request, response);

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
                case "ajax":
                {}
                default :
                {
                    
                    request.setAttribute("list", data.getAllUsers() );
                }
            }
            data.closeAll();
        }
        catch(Exception e )
        {
            logContent(e.toString(), LoggerFactory.ERROR, e);
            request.setAttribute("msg", e.getMessage());
        }
        finally
        {
            if ( !mode.equals("ajax"))
                request.getRequestDispatcher(Constant.USER_DETAIL_JSP).forward(request, response);
            else
                out.close();
        }

    }


}
