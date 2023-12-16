<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>SK Travels</title>

<%
	final String CACHE_VERSION = "?version=18" ; 
	session.setAttribute("CACHE_VERSION", CACHE_VERSION);
%>
<link rel="icon" type="image/x-icon" href="<%=request.getContextPath() %>/Images/favIcon/fav.png">

<link rel="stylesheet" href="<%=request.getContextPath() %>/styleSheet/bootstrap.css<%= CACHE_VERSION %>" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/styleSheet/index.css<%= CACHE_VERSION %>" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/styleSheet/Pagination.css<%= CACHE_VERSION %>" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/styleSheet/searchBox.css<%= CACHE_VERSION %>" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/styleSheet/header.css<%= CACHE_VERSION %>" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/styleSheet/ajax-loader.css<%= CACHE_VERSION %>" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/styleSheet/loader/loader.css<%=CACHE_VERSION %>" type="text/css">

<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css<%= CACHE_VERSION %> type="text/css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css<%= CACHE_VERSION %>">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css<%= CACHE_VERSION %>" rel="stylesheet">

<script src="<%=request.getContextPath() %>/scripts/ajax/libs/jquery/3.5.1/jquery.min.js<%= CACHE_VERSION %>" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/scripts/ajax/libs/jquery/3.6.0/jquery.min.js<%= CACHE_VERSION %>" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/scripts/ajax/libs/jquery/1.9.1/jquery.min.js<%= CACHE_VERSION %>" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/scripts/ajax/libs/jqueryUi/1.10.2/jquery-ui.min.js<%= CACHE_VERSION %>" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/scripts/Validator.js<%= CACHE_VERSION %>"></script>
<script src="<%=request.getContextPath() %>/scripts/ajaxCall.js<%= CACHE_VERSION %>"></script>
<script src="<%=request.getContextPath() %>/scripts/loader/loader.js<%=CACHE_VERSION %>"></script>
<script src="<%=request.getContextPath() %>/scripts/common.js<%= CACHE_VERSION %>" type="text/javascript"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js" type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>  
<link href="https://fonts.googleapis.com/css?family=Roboto:400,500" rel="stylesheet" />

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js" type="text/javascript"></script>

<%@ include file="loader/loader.jsp" %>
<body><div class="loader-ajax" style="display: none"></div></body>

