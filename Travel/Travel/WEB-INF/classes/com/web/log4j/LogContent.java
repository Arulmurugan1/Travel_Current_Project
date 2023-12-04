package com.web.log4j;

import org.apache.log4j.Level;

import com.web.common.CommonFactory;
import com.web.common.Generic;

public class LogContent extends CommonFactory  
{

	/**
	 * @param message
	 * @param logLevel
	 * @param exception
	 * @param className
	 */
	public static void logContent(Object message, Level logLevel, Throwable exception, Object className) 
	{
		LoggerFactory.displayDiffLogLevels(isNull(message), logLevel, exception,className);
	}
	
	/**
	 * @param message
	 * @param logLevel
	 * @param exception
	 */
	public static void logContent(Object message, Level logLevel, Throwable exception) 
	{
		LoggerFactory.displayDiffLogLevels(isNull(message), logLevel, exception,Generic.generic);
	}
	
	/**
	 * @param message
	 * @param logLevel
	 * @param className
	 */
	public static void logContent(Object message, Level logLevel, Object className) 
	{
		LoggerFactory.displayDiffLogLevels(isNull(message), logLevel, null,className);
	}
	
	/**
	 * @param message
	 * @param logLevel
	 */
	public static void logContent(Object message, Level logLevel) 
	{
		LoggerFactory.displayDiffLogLevels(isNull(message), logLevel, null,Generic.generic);
	}
	
	/**
	 * @param message
	 * @param logLevel
	 * @param exception
	 * @param className
	 */
	public static void logContent(String message, Level logLevel, Throwable exception, Object className) 
	{
		LoggerFactory.displayDiffLogLevels(isNull(message), logLevel, exception,className);
	}
	
}
