<%@page import="com.web.log4j.LoggerFactory"%>
<%@page import="com.web.common.Generic"%>
<%@page import="com.web.common.CommonFactory"%>
<%@page import="com.web.common.Constant"%>
<%@page import="com.web.util.Dbmanager"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.DataInputStream"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>


<%

DataInputStream in = null ; 
FileOutputStream fout = null ;

try
{
	Generic.LogObject = this ;
	
	String saveFile = "" ;
	String contentType = request.getContentType() ;
	
	if( contentType != null && contentType.indexOf("multipart/form-data") != -1 )
	{
		String uploadPath = CommonFactory.isNull( Dbmanager.getDBPropertiesPath(Constant.PROFILE_IMAGE_PATH) ) ;
		String userPath   = CommonFactory.isNull(request.getSession().getAttribute("user_id")) +Constant.seperator  ;

		if(uploadPath.endsWith(Constant.seperator))
			uploadPath			 += userPath ;
		else
			uploadPath			 += Constant.seperator + userPath ;
		
		in = new DataInputStream(request.getInputStream());
		int formDataLength = request.getContentLength() ;
		byte dataBytes[]   = new byte[formDataLength] ;
		int byteRead = 0 , totalBytesRead = 0 , lastIndex = contentType.lastIndexOf("=") ,pos;
		
 		Generic.logContent("DataInputStream > "+in.toString(), LoggerFactory.DEBUG);
 		Generic.logContent("uploadPath "+ uploadPath, LoggerFactory.DEBUG);

		while( totalBytesRead < formDataLength )
		{
			byteRead = in.read(dataBytes, totalBytesRead ,formDataLength ) ;
			/* Generic.logContent("byteRead > "+byteRead , LoggerFactory.DEBUG);
			Generic.logContent("totalBytesRead > "+totalBytesRead , LoggerFactory.DEBUG); */
			totalBytesRead += byteRead ;	
		}
		
 		Generic.logContent("totalBytesRead > "+totalBytesRead , LoggerFactory.DEBUG);
 		Generic.logContent("formDataLength > "+formDataLength , LoggerFactory.DEBUG);
 		
		String file = new String(dataBytes);
 		Generic.logContent("file > "+new String(dataBytes) , LoggerFactory.DEBUG);
		
		saveFile	= file.substring(file.indexOf("filename=\"") + 10 );
 		Generic.logContent("saveFile1 > "+saveFile , LoggerFactory.DEBUG);
		
		saveFile	= saveFile.substring(0 , saveFile.indexOf("\n") );
 		Generic.logContent("saveFile2 > "+saveFile , LoggerFactory.DEBUG);
		
		saveFile	= saveFile.substring( saveFile.indexOf("\\") + 1 , saveFile.indexOf("\"") );
 		Generic.logContent("saveFile3 > "+saveFile , LoggerFactory.DEBUG);
		
		String boundary = contentType.substring(lastIndex + 1 , contentType.length()) ;
		
 		Generic.logContent("boundary > "+boundary, LoggerFactory.DEBUG);
		
		pos = file.indexOf("filename=\"") ;
		pos = file.indexOf("\n",pos) + 1 ;
		pos = file.indexOf("\n",pos) + 1 ;
		pos = file.indexOf("\n",pos) + 1 ;
		
 		Generic.logContent("Position of String >"+pos, LoggerFactory.DEBUG);
		
		int boundaryLocation = file.indexOf(boundary,pos) - 4 ;
		int startPos 	=   ( file.substring(0, pos)).getBytes().length ; 
		int endPos 		=   ( file.substring(0, boundaryLocation)).getBytes().length ;
		
 		Generic.logContent(" endPos ["+endPos+"] startPos["+startPos+"] boundaryLocation["+boundaryLocation+"]", LoggerFactory.DEBUG);
		
		if( Generic.createFolders(uploadPath) )
		{
			File f = new File( uploadPath + saveFile );
			
 			Generic.logContent("File > "+f, LoggerFactory.DEBUG);
			
			fout = new FileOutputStream(f);
 			Generic.logContent("FileOutputStream > "+fout, LoggerFactory.DEBUG);
			
			fout.write(dataBytes, startPos, endPos - startPos );
			
			out.println(" Success .... ") ;
		}
		else
		{
			out.println(" Error in File Path Creation ... ");
		}
		
	}
}
catch(Throwable e)
{
	out.print(e.toString()) ;
	e.printStackTrace();
}
finally
{
	if( fout != null)
	{
		fout.flush();
		fout.close();
	}
	
	if ( in != null)
	{
		in.close();
	}
	
}

%>