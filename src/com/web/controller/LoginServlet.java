package com.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.common.Constant;
import com.web.common.LoggerFactory;
import com.web.modal.Logindao;
import com.web.objects.Login_Info;
import com.web.util.Dbmanager;


@WebServlet("/Login")
public class LoginServlet extends CustomServlet {
    private static final long serialVersionUID = 1L;
    public Logindao dao = null;
    public Login_Info u =null;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session =request.getSession();
        
        Connection con = null ;
        
        if ( request.getParameter("txtUser") != null)
            session.setAttribute("user_id", request.getParameter("txtUser").trim() ); // Set UserName before checking 
        
        super.service(request,this);

        dao= new Logindao();

        u = new Login_Info();

        boolean success = false ;

        try {
            
            con = Dbmanager.getConnection() ;
            
            if (  con == null )
            {

                logContent("Connection error "+Dbmanager.error, LoggerFactory.DEBUG, null);
                request.setAttribute("msg",Dbmanager.error );
            }
            else	
            {   
                if ( mode.trim().equals("login"))
                { 
                    String user =request.getParameter("txtUser").trim();
                    String password =request.getParameter("txtPassword").trim();
                    
                    u = dao.selectUser(user);
                    
                    if( u.getUser_id() != null ) 
                    {
                        

                        logContent( " Last Login "+u.getLast_login(), LoggerFactory.DEBUG, null);

                        logContent("DB UserId : "+u.getUser_id()+" Username : "+ u.getUsername() + " Password : "+u.getPassword() , LoggerFactory.DEBUG, null);
                        
                        if(user.trim().equals( ( u.getUser_id().trim() )) && password.trim().equals( ( u.getPassword().trim()) )) 
                        {
                            session.setAttribute("password", u.getPassword());
                            session.setAttribute("user_id", u.getUser_id());
                            session.setAttribute("user", u.getUsername());
                            session.setAttribute("role", u.getRole());
                            session.setAttribute("gender", u.getGender().trim());
                            session.setAttribute("dob", u.getDob());
                            session.setAttribute("status", u.getStatus() == null ? " " : u.getStatus().trim().equals("Y") ? "Approved" : "Pending" );
                            session.setAttribute("timeStamp", u.getLast_login().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm:ss a")) );

                            dao.updateUserLoginTtsamp(u) ;
                            success = true ;
                        }
                        else 
                        {		
                            request.setAttribute("msg","Incorrect Username or Password ...");
                        }
                    }
                    else
                    {
                        request.setAttribute("msg","Create an account to login ..");
                    }

                    request.setAttribute("txtuser",user);
                              
                  }

                if ( mode.trim().equals("register"))
                {
                    String name 	=request.getParameter("username").trim();
                    String pass1	=request.getParameter("pass1").trim();
                    String pass2 	=request.getParameter("pass2").trim();
                    String id		=request.getParameter("user_id").trim();

                    if( ( name.equals("") || name.equalsIgnoreCase("null") )   || ( pass1.equals("") || pass1.equalsIgnoreCase("null") )  || 
                            ( pass2.equals("") || pass2.equalsIgnoreCase("null") )   || ( id.equals("") || id.equalsIgnoreCase("null") )   )
                    {
                        request.setAttribute("msg","Enter Login details first...");
                    }
                    else 
                    {
                        u.setUser_id(id);
                        u.setUsername(name);
                        u.setLast_login(LocalDateTime.now());
                        u.setPassword(pass1);
                        u.setRole("Guest");
                        u.setCreate_time(LocalDateTime.now());
                        u.setStatus("N");
                        
                        if(dao.selectUser(id).getUser_id() != null)
                        {
                            logContent( " In LoginServlet : name= "+name+" User already available" ,  LoggerFactory.DEBUG, null);
                            request.setAttribute("msg","User Account already exist as " + id);
                        }
                        else
                        {
                            if(dao.insertUser(u)) 
                            {
                                logContent( " In LoginServlet : name= "+name+" ; password1 = "+pass1+" Password 2 = "+pass2+" User_id = "+id ,  LoggerFactory.DEBUG, null);
                                request.setAttribute("msg","User Added Successfully ...");
                            }
                            else
                            {
                                logContent( " In LoginServlet : name= "+name+" Error in insertion" ,  LoggerFactory.DEBUG, null);
                                request.setAttribute("msg","Adding failed Check the error occured...");
                            }
                        }
                    }
                }

                if ( mode.equals("L"))
                {
                    request.setAttribute("mode",mode);
                    session.invalidate();
                    logContent("Session On Logout "+session, LoggerFactory.DEBUG, null);
                }

            }
        }
        catch(Exception e) 
        {
            logContent("Exception in Login Servlet" ,  LoggerFactory.DEBUG, e);
            e.printStackTrace();	
        }
        finally
        {
            if ( success )
            {
                try {
                    Logindao.InsertAccessLog(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            try {
                dao.closeAll();
                
                if ( con !=null)
                        con.close();
                
            } catch (Exception e) {
                logContent("Error in Login Servlet",LoggerFactory.ERROR,e);
            }
            
            if( success )
            {
                request.getRequestDispatcher(Constant.HOME_JSP).forward(request, response);  
            }
            else
            {
                request.getRequestDispatcher(Constant.INDEX_JSP).forward(request, response);
            }
        }	
    }
}
