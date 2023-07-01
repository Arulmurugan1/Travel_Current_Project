package com.web.common;

import org.apache.log4j.Logger;

public interface Log4jLogger 
{
	 Logger log = Logger.getLogger( Object.class.getName() );	 
}