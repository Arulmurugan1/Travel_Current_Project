<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../dbconnection.jsp" %>
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
	<a type="button" onclick='location.reload();' style="width:20%;" class="btn btn-success button-length" >Refresh</a>
</div>
<div class="profile__cards">
<%
	if (list !=null && list.size() > 0 )
	{
		list.remove(0);
		
		for(Object users : list)
		{
				Vector<Object> data = (Vector)users;

				String id 			= data.elementAt(0).toString();
				String Name 		= data.elementAt(1).toString();
				String Password 	= data.elementAt(2).toString();
				String age 			= data.elementAt(10).toString();
				String gender 		= data.elementAt(4).toString();
				String Last_Login 	= data.elementAt(8).toString();
				String access		= data.elementAt(9).toString();
				String Status 		= access.equals("Y") ? "Approved" : "Pending";
				
				boolean genders     = gender !=null && ( gender.equals("F") || gender.equals("T") ) ;
%>

	<div class="profile__card">
        <div class="profile__card__inner">
            <div class="profile__card__front" 
            	style="background-image :url('<%=genders ?"Images/user/wanda.jpg" : "Images/user/spider-man.jpg" %>');"
            	>
                <div class="title">
                    <h2><%=Name %></h2>
                    <p><%=Status %></p>
                </div>
            </div>
            <div class="profile__card__back">
            	<img
		          src="<%= genders ?"Images/user/female.png" : "Images/user/male.png"%>"
		          class="img-responsive"
		          alt="<%= genders ?"Female" : "Male"%>"
		        />
	       		<h2><%= Name%></h2>
	            <span> 
	            	<%= id +" / " + Password%>
	            </span>
	            <span> 
	            	<%=age %>    
	            </span> 
	            <span> 
	            	<%= Last_Login%>         
	            </span>
	            
	            <div class="profile__btn">
	                <input 
	                	type="button" 
	                	id="<%= id %>" 
	                	class="btn <%=Status.equals("Approved") ? "btn-success" : "btn-danger" %>" 
	                	value="<%=Status%>" 
	                	<%=session.getAttribute("user_id").toString().trim().equals("arul")  ? "" : "disabled"  %>
	      				onclick ="callAjaxUserAccess('<%= id %>','<%=access%>')" 
	      			/>
	            </div>
	             </div>
        </div>
    </div>
      <%
		}
	}
    %>
  </div> 
    </body>
    </html>