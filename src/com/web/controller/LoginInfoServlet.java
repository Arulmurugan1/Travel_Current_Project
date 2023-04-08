package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.common.LoggerFactory;
import com.web.modal.Logindao;
import com.web.objects.Login_Info;

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
                        
                        Login_Info detail = new Login_Info();
                        detail.setDob(request.getParameter("dob"));
                        detail.setGender(request.getParameter("gender"));
                        detail.setUser_id(request.getSession().getAttribute("user_id").toString());
                        detail.setAltered_user(request.getSession().getAttribute("user").toString());
                        
                        logContent(detail.toString() , LoggerFactory.INFO, null);
                        
                        boolean result = data.updateUserInfo(detail);
                        
                        logContent(result ? "Updated Successfully" : " Failed To Update" , LoggerFactory.INFO, null);
                        out.println(result ? "Updated Successfully^"+detail.getDob()+"^"+detail.getGender() : " Failed To Update") ;
                        logContent(out.toString() , LoggerFactory.INFO, null);;
                    }
                    catch (Exception e1) {
                        logContent(e1.toString(), LoggerFactory.ERROR, e1);
                            out.println(e1.getMessage());
                            e1.printStackTrace();
                    }  
                    finally
                    {
                        out.close();
                    }
                    break;
                }
                default :
                {
                    Vector<Object> users = data.getAllUsers();
                    
//                    users.forEach(System.out::println);
                    
                    request.setAttribute("list", users );
                }
            }
            data.closeAll();
        }
        catch(Exception e )
        {
            logContent(e.toString(), LoggerFactory.ERROR, e);
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
