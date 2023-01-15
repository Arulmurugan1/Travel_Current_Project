package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.web.modal.Logindao;
import com.web.objects.Login;

@WebServlet("/LoginInfo")
public class LoginInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Logindao data ;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String mode = request.getParameter("mode") == null ? "":request.getParameter("mode").trim();
        
        PrintWriter out = response.getWriter();

        try
        {
            Enumeration<String> e = request.getParameterNames();
            
            while(e.hasMoreElements())
            {
                String s = e.nextElement();
                System.out.println( s+" ["+request.getParameter(s) +"] ");
            }
            
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
                case "ajax":
                {
                    
                    response.setContentType("text/html");
                    response.setHeader("Cache-control", "no-cache, no-store");
                    response.setHeader("Pragma", "no-cache");
                    response.setHeader("Expires", "-1");

                    response.setHeader("Access-Control-Allow-Origin", "*");
                    response.setHeader("Access-Control-Allow-Methods", "POST");
                    response.setHeader("Access-Control-Allow-Headers", "Content-Type");
                    response.setHeader("Access-Control-Max-Age", "86400");

                    try {
                        Login detail = new Login();
                        detail.setDob(request.getParameter("dob"));
                        detail.setGender(request.getParameter("gender"));
                        detail.setUser_id(request.getSession().getAttribute("user_id").toString());
                        System.out.println(detail);
                        boolean result = data.updateUserInfo(detail);
                        System.out.println(result);
                        System.out.println(result ? "Updated Successfully" : " Failed To Update");
                        out.println(result ? "Updated Successfully" : " Failed To Update") ;
                        System.out.println(out);
                    }
                    catch (Exception e1) {
                            out.println(e1.getMessage());
                    }  
                    finally
                    {
                        out.close();
                    }
                    break;
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
            if ( !mode.equals("ajax"))
                request.getRequestDispatcher("UserDetails.jsp").forward(request, response);
            else
                out.close();
        }

    }


}
