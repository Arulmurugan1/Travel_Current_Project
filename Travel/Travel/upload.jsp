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
	String saveFile = "" ;
	String contentType = request.getContentType() ;
	
	if( contentType != null && contentType.indexOf("multipart/form-data") != -1 )
	{
		String uploadPath =  CommonFactory.isNull( Dbmanager.getDBPropertiesPath(Constant.PROFILE_IMAGE_PATH) ) ;

		if(uploadPath.endsWith(Constant.seperator))
			uploadPath			 += CommonFactory.isNull(request.getSession().getAttribute("user_id")) +Constant.seperator  ;
		else
			uploadPath			 += Constant.seperator + CommonFactory.isNull(request.getSession().getAttribute("user_id")) + Constant.seperator  ;
		
		in = new DataInputStream(request.getInputStream());
		int formDataLength = request.getContentLength() ;
		byte dataBytes[]   = new byte[formDataLength] ;
		int byteRead = 0 , totalBytesRead = 0 , lastIndex = contentType.lastIndexOf("=") ,pos;
		
		while( totalBytesRead < formDataLength )
		{
			byteRead = in.read(dataBytes, totalBytesRead ,formDataLength ) ;
			totalBytesRead += byteRead ;
		}
		
		String file = new String(dataBytes);
		
		saveFile	= file.substring(file.indexOf("filename=\"") + 10 );
		saveFile	= saveFile.substring(0 , saveFile.indexOf("\n") );
		saveFile	= saveFile.substring( saveFile.indexOf("\\") + 1 , saveFile.indexOf("\"") );
		
		String boundary = contentType.substring(lastIndex + 1 , contentType.length()) ;
		
		pos = file.indexOf("filename=\"") ;
		pos = file.indexOf("\n",pos) + 1 ;
		pos = file.indexOf("\n",pos) + 1 ;
		pos = file.indexOf("\n",pos) + 1 ;
		
		int boundaryLocation = file.indexOf(boundary,pos) - 4 ;
		int startPos 	=   ( file.substring(0, pos)).getBytes().length ; 
		int endPos 		=   ( file.substring(0, boundaryLocation)).getBytes().length ;
		
		if( Generic.createFolders(uploadPath) )
		{
			File f = new File( uploadPath + saveFile );
			
			fout = new FileOutputStream(f);
			fout.write(dataBytes, startPos, endPos - startPos );
			fout.flush();
			fout.close();
			
			out.print("success") ;
			out.flush();
			out.close();	
		}
		
	}
}
catch(Throwable e)
{
	out.print(e.toString()) ;
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