package com.web.Excel;

import java.time.LocalDate;

import com.web.common.CommonFactory;

public class LocalDateEx 
{
	public static void main(String[] args) 
	{
		LocalDate dateInstance = LocalDate.now();
		String seperator = "/";
		int month = dateInstance.getMonthValue() ;
		int day = dateInstance.getDayOfMonth() ;
		String monthDesc = dateInstance.getMonth().name();
		int year = dateInstance.getYear();
		
		String dayDesc = dateInstance.getDayOfWeek().name();
		
		String path = year +seperator+ month +"-"+CommonFactory.StringInit(monthDesc).trim()  +seperator+ day + seperator ;
		
		System.out.println(" folder ["+path+"] "+dayDesc);
	}
}
