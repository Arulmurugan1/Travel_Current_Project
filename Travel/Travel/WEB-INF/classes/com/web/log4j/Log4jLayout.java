package com.web.log4j;

import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.SimpleLayout;

import com.web.common.CommonFactory;

public class Log4jLayout extends CommonFactory 
{
		public static Layout getLogLayout(String logFormat)
		{
			
			String layout = isNull(logFormat).isBlank() ? PATTERN_LAYOUT : logFormat ;
			
			switch ( layout )
			{
				case SIMPLE_LAYOUT :
				{
					SimpleLayout sm = new SimpleLayout();
					return sm ;
				}
				case PATTERN_LAYOUT :
				{
					PatternLayout pm = new PatternLayout();
					pm.setConversionPattern("[%d{dd-MM-yyyy hh:mm:ss:sss a z}] %-5p  - %m %n");
					return pm;
				}
				case HTML_LAYOUT :
				{
					HTMLLayout hm = new HTMLLayout();
					hm.setLocationInfo(true);
					hm.setTitle(" SK Travels Log ");
					return hm;
				}
				default :
				{
					return new PatternLayout();
				}
					
			}
		}
}
