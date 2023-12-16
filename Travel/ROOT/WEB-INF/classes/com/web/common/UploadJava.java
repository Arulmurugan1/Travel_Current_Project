package com.web.common;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.web.util.Dbmanager;


public class UploadJava extends Generic{
	
	private String filePath ,fileName ; 
	 private boolean isUpdated  ;
	
	public UploadJava()
	{
		filePath = fileName = "";
	}
	
	public boolean uploadImage(String Path)throws Exception
	{
		isUpdated = false ;
		
		String uploadPath = isBlankCheck(Path) ? isNull( Dbmanager.getDBPropertiesPath(PROFILE_IMAGE_PATH) ):  Path  ;

		if(uploadPath.endsWith(seperator))
			uploadPath			 += isNull(session.getAttribute("user_id")) +seperator  ;
		else
			uploadPath			 += seperator + isNull(session.getAttribute("user_id")) +seperator  ;
		
		logContent( " Image Upload Path [" +uploadPath+"] ", DEBUG, null);

		if( createFolders(uploadPath) );
		{
			
			Part filePart = request.getPart("imageFile");
			fileName = getSubmittedFileName(filePart);
			filePath = uploadPath + File.separator + fileName;
			
			logContent( " filePart ["+filePart+"] fileName [" + fileName+"] filePath [" + filePath+"] ", DEBUG, null);

			if(filePart == null || fileName == null )
			{
				return isUpdated ;
			}
			
			InputStream fileContent = filePart.getInputStream();
			
			OutputStream fileOutputStream = new FileOutputStream(filePath);
			
			try 
			{
				
				logContent( " fileContent ["+fileContent+"]\t fileOutputStream [" + fileOutputStream+"]", DEBUG, null);
				
				int read;
				final byte[] bytes = new byte[request.getContentLength()];
				
				while ( (read = fileContent.read(bytes) ) != -1 ) 
				{
					fileOutputStream.write(bytes, 0, read);
				}
				
				isUpdated = true ;
				
				logContent( "Upload Successful", DEBUG, null);
				
			}
			finally
			{
				fileContent.close();
				fileOutputStream.close();
			}
		}
		
		return isUpdated ;
	}

    private String getSubmittedFileName(Part part) {
    	
    	if(part == null)
    	{
    		return null ;
    	}
        String header = part.getHeader("content-disposition");
        
        logContent( "Upload header ["+header+"]", DEBUG, null);
        
        for (String elem : header.split(";")) 
        {
        	logContent( "Upload header elem ["+elem+"]", DEBUG, null); 
        	
            if (elem.trim().startsWith("filename")) 
            {
            	String name = elem.substring(elem.indexOf('=') + 1).trim().replace("\"", "") ;
            	
            	logContent( "Upload header FileName ["+name+"]", DEBUG, null); 
            	
                return name ;
            }
        }
        return null;
    }
    
    public boolean uploadImage(HttpServletRequest request)throws Exception
    {

    	DataInputStream in = null ; 
    	FileOutputStream fout = null ;
    	
    	try
    	{
    		String saveFile = "" ;
    		String contentType = request.getContentType() ;

    		if( contentType != null && contentType.indexOf("multipart/form-data") != -1 )
    		{
    			String uploadPath =  isNull( Dbmanager.getDBPropertiesPath(PROFILE_IMAGE_PATH) ) ;

    			if(uploadPath.endsWith(seperator))
    				uploadPath			 += isNull(request.getSession().getAttribute("user_id")) +seperator  ;
    			else
    				uploadPath			 += seperator + isNull(request.getSession().getAttribute("user_id")) +seperator  ;

    			logContent( " Image Upload Path [" +uploadPath+"] ", DEBUG, null);

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

    			logContent( " FileName [" +saveFile+"] file ["+file+"]", DEBUG);

    			String boundary = contentType.substring(lastIndex + 1 , contentType.length()) ;

    			pos = file.indexOf("filename=\"") ;
    			pos = file.indexOf("\n",pos) + 1 ;
    			pos = file.indexOf("\n",pos) + 1 ;
    			pos = file.indexOf("\n",pos) + 1 ;

    			int boundaryLocation = file.indexOf(boundary,pos) - 4 ;
    			int startPos 	=   ( file.substring(0, pos)).getBytes().length ; 
    			int endPos 		=   ( file.substring(0, boundaryLocation)).getBytes().length ;

    			logContent( " boundary [" +boundary+"] pos ["+pos+"] boundaryLocation ["+boundaryLocation+"] startPos ["+startPos+"]  endPos ["+endPos+"] ", DEBUG);

    			File f = new File( uploadPath + saveFile );

    			fout = new FileOutputStream(f);
    			fout.write(dataBytes, startPos, endPos - startPos );
    			
    			isUpdated = true ;
    		}
    		else
    		{
    			out.print("Failed") ;
    		}
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
    	
    	return isUpdated;
    }

	public String getFilePath() {
		return filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public boolean isUpdated() {
		return isUpdated;
	}
	
	

}
