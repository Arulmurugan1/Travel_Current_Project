<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="com.web.log4j.LoggerFactory"%>
<%@page import="com.web.common.Generic"%>
<%@page import="com.web.common.CommonFactory"%>
<%@page import="com.web.common.Constant"%>
<%@page import="java.io.FileOutputStream"%>

<%

String res = "";

try
{
	Generic.LogObject = this ;
	
	String fileName	 = "" , filePath = "";	
	String seperator  = Constant.seperator ;
	String uploadPath = getServletContext().getRealPath("/Images");
	
	Generic.logContent( " Inside UploadImage.jsp ", LoggerFactory.DEBUG);

	if(uploadPath.endsWith(seperator))
	{
		uploadPath			 += CommonFactory.isNull(session.getAttribute("user_id")) +seperator  ;
	}
	else
	{
		uploadPath			 += seperator + CommonFactory.isNull(session.getAttribute("user_id")) +seperator  ;
	}
	
	Generic.logContent( " Image Upload Path [" +uploadPath+"] ", LoggerFactory.DEBUG);

	if( Generic.createFolders(uploadPath) );
	{
		
		Part filePart = request.getPart("imageFile");
		
		if(filePart == null)
		{
			res = " Cannot read Image / File Part is null ";
			return;
		}
		
	    String header = filePart.getHeader("content-disposition");
	    
	    Generic.logContent( "Upload header ["+header+"]", LoggerFactory.DEBUG);
	    
	    for (String elem : header.split(";")) 
	    {
	    	Generic.logContent( "Upload header elem ["+elem+"]", LoggerFactory.DEBUG, null); 
	    	
	        if (elem.trim().startsWith("filename")) 
	        {
	        	String name = elem.substring(elem.indexOf('=') + 1).trim().replace("\"", "") ;
	        	
	        	Generic.logContent( "Upload header element ["+elem+"] FileName ["+name+"]", LoggerFactory.DEBUG, null); 
	        	
	        	fileName = name ;
	        }
	    }
	    
		filePath = uploadPath + seperator + fileName;
		
		Generic.logContent( " filePart ["+filePart+"] fileName [" + fileName+"] filePath [" + filePath+"] ", LoggerFactory.DEBUG);

		if( fileName == "" )
		{
			res = " Cannot read Image File Name .... ";
			return;
		}
		
		InputStream fileContent 		= filePart.getInputStream();
		
		OutputStream fileOutputStream 	= new FileOutputStream(filePath);
		
		try 
		{
			
			Generic.logContent( " fileContent ["+fileContent+"]\t fileOutputStream [" + fileOutputStream+"]", LoggerFactory.DEBUG);
			
			int read;
			
			final byte[] bytes = new byte[request.getContentLength()];
			
			fileOutputStream.write(bytes, 0, request.getContentLength());
			
			res = "Uploaded Successfully" ;
			
			Generic.logContent( "Uploaded Successfully", LoggerFactory.DEBUG, null);
			
		}
		finally
		{
			fileContent.close();
			fileOutputStream.flush();
			fileOutputStream.close();
		}
	}
}
catch(FileNotFoundException e)
{
	out.println(" FileNotFoundException in UploadImage");
	Generic.logContent(" FileNotFoundException in UploadImage", LoggerFactory.ERROR,e);
}
catch(IOException e )
{
	out.println(" IOException in UploadImage");
	Generic.logContent(" IOException in UploadImage", LoggerFactory.ERROR,e);
}
catch(ServletException e )
{
	out.println(" ServletException in UploadImage");
	Generic.logContent(" ServletException in UploadImage", LoggerFactory.ERROR,e);
}
catch(Exception e )
{
	out.println(" Exception in UploadImage");
	Generic.logContent(" Exception in UploadImage", LoggerFactory.ERROR,e);
}
finally
{
	out.println(res);
}

%>