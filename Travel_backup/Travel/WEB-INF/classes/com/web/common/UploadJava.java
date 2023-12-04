package com.web.common;

import java.io.*;
import javax.servlet.http.Part;
import com.web.util.Dbmanager;


public class UploadJava extends Generic{
	
	private String filePath ,fileName ; 
	 private boolean isUpdated  ;
	
	public UploadJava()
	{
		filePath = fileName = ""; isUpdated = false ;
	}
	
	public boolean uploadImage(String Path) throws Exception
	{
		isUpdated = false ;
		
		String uploadPath = isNull(Path) == "" ? isNull( Dbmanager.getPropertiesPath(PROFILE_IMAGE_PATH) ):  isNull(Path)  ;

		uploadPath			 += isNull(session.getAttribute("user_id")) +seperator  ;
		
		logContent( " Image Upload Path [" +uploadPath+"] ", LoggerFactory.DEBUG, null);

		if( createFolders(uploadPath) );
		{
			
			Part filePart = request.getPart("imageFile");
			fileName = getSubmittedFileName(filePart);
			filePath = uploadPath + File.separator + fileName;
			
			
			if(filePart == null || fileName == null )
			{
				logContent( " filePart ["+filePart+"] fileName ["+fileName+"]", LoggerFactory.DEBUG, null);
				return isUpdated ;
			}
			
			
			logContent( " filePart ["+filePart+"] fileName [" + fileName+"] filePath [" + filePath+"] ", LoggerFactory.DEBUG, null);

			InputStream fileContent = filePart.getInputStream();
			
			OutputStream fileOutputStream = new FileOutputStream(filePath);
			
			try 
			{
				
				logContent( " fileContent ["+fileContent+"]\t fileOutputStream [" + fileOutputStream+"]", LoggerFactory.DEBUG, null);
				
				int read;
				final byte[] bytes = new byte[request.getContentLength()];
				
				while ( (read = fileContent.read(bytes) ) != -1 ) 
				{
					fileOutputStream.write(bytes, 0, read);
				}
				
				isUpdated = true ;
				
				logContent( "Upload Successful", LoggerFactory.DEBUG, null);
				
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
        
        logContent( "Upload header ["+header+"]", LoggerFactory.DEBUG, null);
        
        for (String elem : header.split(";")) 
        {
        	logContent( "Upload header elem ["+elem+"]", LoggerFactory.DEBUG, null); 
        	
            if (elem.trim().startsWith("filename")) 
            {
            	String name = elem.substring(elem.indexOf('=') + 1).trim().replace("\"", "") ;
            	
            	logContent( "Upload header FileName ["+name+"]", LoggerFactory.DEBUG, null); 
            	
                return name ;
            }
        }
        return null;
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
