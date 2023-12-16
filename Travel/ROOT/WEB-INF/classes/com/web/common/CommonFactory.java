package com.web.common;

import java.lang.reflect.Field;

public class CommonFactory extends Constant
{
	public static String StringInit(String in )
	{
		
		String out = isNull(in) ;
		
		if(out.length() >= 2)
		{
			return isNull (out.charAt(0)+"".toUpperCase() + out.substring(1,out.length()).toLowerCase() );  
		}
		
		return in ;
	}
    public static String Init(String in)
    {
        String output = "";
        String output1 = "";
        
        if ( in !=null && in.trim().length() > 0 )
        {
            output = in.split("_")[0];
            output = output.substring(0, 1).toUpperCase()+""+output.substring(1,output.length());
            
            if ( in.split("_").length > 1 )
            {
                output1 = in.split("_")[1];
                output1 = output1.substring(0, 1).toUpperCase()+""+output1.substring(1,output1.length());
            }
            
            return output+" "+output1;
        }
        else
            return "";
    }
    public static String isNull(Object string)
    {
        return  string == null ? "" : string.toString().trim() ;
    }
    
    public static boolean isBlankCheck(Object string)
    {
        return isNull(string).equals("");
    }
    
    public static String printClassVariables(Object classes) throws Exception
    {
    	StringBuilder sbSql  = new StringBuilder();
    	
    	if(classes !=null)
    	{
    		sbSql.append(classes.getClass().getName() +" ");
    		
    		Field[] fields = classes.getClass().getDeclaredFields();
    		
    		 for(Field s : fields)
             {
    			 s.setAccessible(true);
    			 sbSql.append(s.getName()+ASSIGN_OPERATOR);
                 
                 if ( s.get(classes) != null )
                	 sbSql.append(s.get(classes)+"\n");
             }
    	}
    	else
    	{
    		sbSql.append("Class is null");
    	}
    	return sbSql.toString() ;
    }
    
}
