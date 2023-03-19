<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="dbconnection.jsp"%>
<jsp:useBean id="list" scope="request" class="java.util.Vector" ></jsp:useBean> 
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="styleSheet/UserDetails.css" type="text/css">
<script
      src="https://kit.fontawesome.com/a33530bb41.js"
      crossorigin="anonymous"
    ></script>
</head>
<body>
<div class="w-100 m-4 mx-auto text-center">
<a type="button" href="home.jsp" style="width:20%;" class="btn btn-success button-length" >Back</a>
</div>
<div class="card__collection clear-fix">
<%
				if (list !=null && list.size() > 0 )
				{
					for ( int i=1 ; i < list.size() ; i++ )
					{
							Vector<String> data = (Vector)list.elementAt(i);

							String id = data.elementAt(0) ;
							String Name = data.elementAt(1) ;
							String Password = data.elementAt(2);
							String age 		= data.elementAt(9);
							String gender = data.elementAt(3);
							String Last_Login = data.elementAt(7);
							String Status = data.elementAt(8).toString().equals("Y") ? "Approved" : "Pending";
%>
      <div class="cards cards--two">
      <%
      if ( gender !=null && ( gender.equals("F") || gender.equals("T") ))
		{
	%>
        <img
          src="https://images.unsplash.com/photo-1504703395950-b89145a5425b?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=d702cb99ca804bffcfa8820c46483264&auto=format&fit=crop&w=651&q=80"
          class="img-responsive"
          alt="Cards Image"
        />
        <%
		}
      else
      {
        %>
        <img
          src="https://images.unsplash.com/photo-1480408144303-d874c5e12201?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=575213599ae24b3dbdfd84be79425c50&auto=format&fit=crop&w=634&q=80"
          class="img-responsive"
          alt=""
        />
        <%
      }
        %>
        <span class="cards--two__rect"></span>
        <p> Name : <%= Name%><br>
        		Login Id : <%=id %><br>
        		Password : <%= Password%><br> 
        		Age :  <%=age %><br>
        		Last Login :	<%= Last_Login%> <br> 
        		Status : <%= Status%>
        </p>		
      </div>
      
      <%
						}
				}
    %>
    </div>
    </body>
    </html>
      