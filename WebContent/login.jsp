<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    
<%@ include file="dbconnection.jsp" %>
<jsp:include page="header.jsp" />

<jsp:useBean id="list" scope="request" class="java.util.Vector" ></jsp:useBean> 

<style>
body {
	counter-reset: section;
}

th.no::before {
	counter-increment: section;
	content: counter(section);
}
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 8px;
}
</style>


<div class=" m-3 p-3" style="overflow-x:auto;">

<table class="table  text-center table-info">
		<caption class="text-white text-center m-auto" style="font-size:30px">List of users</caption>
			<thead>
				<tr class=text-white>
					<th scope="col">S.No</th>
				<%
				if (list !=null && list.size() > 0)
				{
					for ( int i=0 ; i < ( (Vector)list.elementAt(0)).size() ; i++)
					{
				%>
						<th align=center scope="col"><%= ( (Vector)list.elementAt(0)).elementAt(i) %></th>
				<%
					}
				 %>
					<c:if test="${sessionScope.role == 'Admin'}">
						<th>Action</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
					<%
						for ( int i=1 ; i < list.size() ; i++ )
						{
					%>
						<tr >
							<th scope="row" class="no text-center"></th>
						<% 
							for ( int j=0 ; j < ( (Vector)list.elementAt(0) ).size() ;j++)
							{
					 %>
					 				<td align=center><%= ( (Vector)list.elementAt(i) ).elementAt(j)  %></td>
					 <%
					 		}
					 %>
					 		<c:if test="${sessionScope.role == 'Admin'}">
								<td align=center>
								<a href="#">Delete</a>
								</td>
						</c:if>
					 	</tr>
					 <% 
					 	}
					  %>
			</tbody>
		<% 
		}
		%>
		</table>
</div>

	