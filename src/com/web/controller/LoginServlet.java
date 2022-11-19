package com.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.common.Constant;
import com.web.modal.Logindao;
import com.web.objects.Login;
import com.web.util.Dbmanager;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Logindao dao = null;
    public Login u =null;

    public LoginServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session =request.getSession();

        String mode =request.getParameter("mode");

        System.out.println("Mode "+mode);

        dao= new Logindao();

        u = new Login();

        boolean success = false ;

        Connection con = null;

        try {
            con = Dbmanager.getConnection() ;
            
            if (  con == null )
            {

                System.out.println("Connection error "+Dbmanager.error);
                request.setAttribute("msg",Dbmanager.error);
            }
            else	
            {
                Dbmanager.close();
                
                if ( mode.trim().equals("login"))
                { 
                    String user =request.getParameter("txtUser").trim();
                    String password =request.getParameter("txtPassword").trim();

                    System.out.println(" Username : "+ user + " Password : "+password);

                    //						DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm:ss a");  
                    //						String formattedDate = LocalDateTime.now().format(myFormatObj);  
                    //						System.out.println("After Formatting: " + formattedDate);  


                    if(dao.checkUser(user)) 
                    {

                        u = dao.selectUser(user);

                        System.out.println( " Last Login "+u.getLast_login());

                        System.out.println("DB Username : "+ u.getUsername() + " Password : "+u.getPassword() );
                        if(user.trim().equals( ( u.getUser_id().trim() )) && password.trim().equals( ( u.getPassword().trim()) )) 
                        {
                            session.setAttribute("user", u.getUsername());
                            session.setAttribute("role", u.getRole());
                            session.setAttribute("timeStamp", u.getLast_login().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm:ss a")) );

                            if (dao.updateUser(u) )
                                System.out.println(" Last logged in updated ");

                            System.out.println("Logged in at : "+ session.getCreationTime());
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
                    dao.closeAll();                }

                if ( mode.trim().equals("register"))
                {
                    String name 	=request.getParameter("username").trim();
                    String pass1	=request.getParameter("pass1").trim();
                    String pass2 	=request.getParameter("pass2").trim();
                    String id		=request.getParameter("user_id").trim();

                    if( ( name.equals("") || name.equalsIgnoreCase("null") )   || ( pass1.equals("") || pass1.equalsIgnoreCase("null") )  || 
                            ( pass2.equals("") || pass2.equalsIgnoreCase("null") )   || ( id.equals("") || id.equalsIgnoreCase("null") )   )
                    {
                        System.out.println( " In LoginServlet : name= "+name+" ; password1 = "+pass1+" Password 2 = "+pass2+" User_id = "+id);
                        request.setAttribute("msg","Enter Login details first...");
                    }
                    else 
                    {
                        System.out.println( " In LoginServlet : name= "+name+" ; password1 = "+pass1+" Password 2 = "+pass2+" User_id = "+id);
                        if(pass1.contentEquals(pass2)) 
                        {
                            u.setUser_id(id);
                            u.setUsername(name);
                            u.setLast_login(null);
                            u.setPassword(pass1);
                            if(dao.checkUser(id))
                            {
                                System.out.println( " In LoginServlet : name= "+name+" User already available");
                                request.setAttribute("msg","User Account already exist as " + id);
                            }
                            else
                            {
                                if(dao.insertUser(u)) 
                                {
                                    System.out.println( " In LoginServlet : name= "+name+" ; password1 = "+pass1+" Password 2 = "+pass2+" User_id = "+id);
                                    request.setAttribute("msg","User Added Successfully ...");
                                }
                                else
                                {
                                    System.out.println( " In LoginServlet : name= "+name+" Error in insertion");
                                    request.setAttribute("msg","Adding failed Check the error occured...");
                                }
                            }

                        }
                        else
                        {
                            request.setAttribute("msg","Password doesn't match...");
                        }

                    }
                    dao.closeAll();
                }

                if ( mode.equals("L"))
                {
                    request.setAttribute("mode",mode);
                    System.out.println( "Logged Out at : "+ request.getSession().getLastAccessedTime() );
                    session.invalidate();
                    System.out.println("Session On Logout "+request.getSession());
                }

            }
        }
        catch(Exception e) 
        {
            System.out.println("Exception in Login Servlet");
            e.printStackTrace();	
        }
        finally
        {
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
