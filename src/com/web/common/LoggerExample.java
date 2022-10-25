package com.web.common;
import org.apache.log4j.Logger;

public class LoggerExample
{
	
	final static Logger logger = Logger.getLogger(LoggerExample.class);
	
	public static void main(String[] args) 
	{
	LoggerExample log4j = new LoggerExample();
	log4j.displayDiffLogLevels("Learning Log4j");
	}
	private void displayDiffLogLevels(String whattoprint) 
	{

	if (logger. isDebugEnabled()) 
		logger.debug("Log4j This is debug : " + whattoprint);
	if (logger.isInfoEnabled()) 
		logger.debug("Log4j This is info : " + whattoprint);
	
	logger.warn("Log4j This warn : " + whattoprint);
	logger.error("Log4j This is error " + whattoprint);
	logger.fatal("Log4j This is fatal : " + whattoprint);

	}    
}
