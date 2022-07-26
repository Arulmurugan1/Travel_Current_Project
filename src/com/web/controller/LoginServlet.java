package com.web.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.modal.user_user_profile_dao;
import com.web.objects.user_user_profile;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public user_user_profile_dao userdao = null;
	public user_user_profile u =null;
	RequestDispatcher rd ;
	
public LoginServlet() {
        super();
        userdao= new user_user_profile_dao();
        u = new user_user_profile();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session =request.getSession();
		try //Logout
		{
			String mode = request.getParameter("mode");
			
			if ( mode.equals("L"))
			{
				request.setAttribute("mode",mode);
				System.out.println("Logged Out at : "+ session.getLastAccessedTime());
				session.invalidate();
				request.getRequestDispatcher("index.jsp").include(request, response);
			}
			
		}catch(Exception e)
		{
			System.out.println("Exception in logout "+ e);
		}

	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session =request.getSession(true);

		String mode =request.getParameter("mode");


		if ( mode.trim().equals("login"))
		{
			try // Login
			{
				String user =request.getParameter("txtUser").trim();
				String password =request.getParameter("txtPassword").trim();

				System.out.println(" Username : "+ user + " Password : "+password);

				LocalDateTime myDate = LocalDateTime.now();  
				System.out.println("Before Formatting: " + myDate);  
				DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm:ss a");  

				String formattedDate = myDate.format(myFormatObj);  
				System.out.println("After Formatting: " + formattedDate);  


				if( ( user.equals("") || user.equalsIgnoreCase("null") )   || ( password.equals("") || password.equalsIgnoreCase("null") ) )
				{
					request.setAttribute("msg","Enter login credentials first ...");
				}
				else
				{
					if(userdao.checkUser(user)) 
					{

						u = userdao.selectUser(user);

						String name 	  =u.getUsername();
						String pass 	  =u.getPassword();
						String role		  =u.getRole();
						String id 		  =u.getUser_id();
						System.out.println( " Last Login "+u.getLast_login());
						String last_login = u.getLast_login().format(myFormatObj);

						System.out.println("DB Username : "+ name + " Password : "+pass);
						if(user.trim().equals(id) && password.trim().equals(pass)) {
							session.setAttribute("user", name);
							session.setAttribute("role", role);
							session.setAttribute("timeStamp", last_login);
							
							if (userdao.updateUser(u) )
									System.out.println(" Last logged in updated ");

							System.out.println("Logged in at : "+ session.getCreationTime());
							rd = request.getRequestDispatcher("home.jsp");
							rd.forward(request, response);
						}
						else 
						{
							request.setAttribute("txtuser",user);
							request.setAttribute("msg","Incorrect Username or Password ...");
						}
					}
					else
					{
						request.setAttribute("txtuser",user);
						request.setAttribute("msg","Create an account to login ..");
					}


				}
			}catch(Exception e) 
			{

				System.out.println("Exception in login Page "+e);
			}

		}

		if ( mode.trim().equals("register"))
		{
			try //register
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
					if(pass1.contentEquals(pass2)) 
					{
						u =new user_user_profile(name, pass1, id);

						if(userdao.checkUser(id))
						{
							System.out.println( " In LoginServlet : name= "+name+" User already available");
							request.setAttribute("msg","User Account already exist as " + id);
						}
						else
						{
							if(userdao.insertUser(u)) 
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
			}catch(Exception e) 
			{
				System.out.println("Exception in user addition ");e.printStackTrace();			
			}
		}
		try 
		{
		rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		}catch(Exception e) {System.out.println("Request dispatched already  ["+rd.toString() +"]");}
	}
}
