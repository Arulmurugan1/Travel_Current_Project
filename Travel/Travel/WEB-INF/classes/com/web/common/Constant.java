package com.web.common;

import org.apache.log4j.Level;

public class Constant extends Thread
{
	
	public static final String BOOKING_JSP = "booking/booking.jsp";
	public static final String BOOKING_INSERT_JSP = "booking/bookinginsertform.jsp";
	public static final String CUSTOMER_JSP = "customer/customer.jsp";
	public static final String CUSTOMER_DETAIL_JSP = "customer/customer.jsp";
	public static final String VEHICLE_JSP = "vehicle/Vehicle.jsp";
	public static final String VEHICLE_INSERT_JSP = "vehicle/Vehicleform.jsp";
	public static final String DRIVER_JSP = "driver/driver.jsp";
	public static final String DRIVER_INSERT_JSP = "driver/driverform.jsp";
	public static final String ROUTE_JSP = "route/route.jsp";
	public static final String USER_DETAIL_JSP = "User/UserDetails.jsp";
	
	public static final String ROUTE_SERVLET = "Route";

	public static final String LOG_PROP_PATH ="log";
	public static final String IS_SYSOUT_LOGGABLE ="isLoggable";
	public static final String LOG_USER 						="user";
	public static final String LOG_FILENAME = "log4j";
	public static final String LOG_FOLDERNAME = "log4j";
	public static final String CONSOLE_FILENAME = "Console";
	public static final String CONSOLE_FOLDERNAME = "Console";
	public static final String seperator ="/";
	
	public static final String INDEX_JSP = "index.jsp";
	public static final String HOME_JSP = "home.jsp";

	public static final String DBCONNECTION_JSP = "";
	public static final String HEADER_JSP = "../header.jsp";

	public static final String INSERT   = "INSERT";
	public static final String UPDATE = "UPDATE";
	public static final String DELETE   = "DELETE";
	public static final String LOG_PATH = "LOG_PATH";

	public static final String ASSIGN_OPERATOR = "  =  ";
	public static final String IMAGE_PATH = "Images/";


	public static final String CONNECTION_PROPERTIES = "ConnectionFile.properties";
	public static final String LOG_PROPERTIES = "log4j.properties";
	public static final String PROFILE_IMAGE_PATH       		= "PROFILE_IMAGE_PATH";

	public static final String USER       			= "db.user";
	public static final String PASSWORD   	= "db.password";
	public static final String DRIVER     		= "db.driver";
	public static final String URL        			= "db.url";
	
	public static final Level INFO				= Level.INFO ;
	public static final Level DEBUG				= Level.DEBUG ;
	public static final Level ERROR				= Level.ERROR ;
	
	public static final String SIMPLE_LAYOUT				= "SIMPLE_LAYOUT" ;
	public static final String HTML_LAYOUT				    =  "HTML_LAYOUT"  ;
	public static final String PATTERN_LAYOUT			=  "PATTERN_LAYOUT";
	

	public static final String LOG_PATH_NOT_FOUND        			= "Log Path Not found in PROPERTIES_PATH table . Ask Your admin ";
	public static final String LOG_PROPERTIES_FAILED        			= "Unable to Load the Log Proprties . Please check :) ";
}
