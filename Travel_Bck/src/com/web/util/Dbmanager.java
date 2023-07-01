package com.web.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Vector;

public class Dbmanager extends com.web.common.StringChecker {
	
	private static final String USER       = "db.user";
	private static final String PASSWORD   = "db.password";
	private static final String DRIVER     = "db.driver";
	private static final String URL        = "db.url";
	
	public static Connection con = null;
	public static String error   = null ;

    private static InputStream in = null ; 
    private static Properties prop = null ;
    
    private static final String CONNECTION_PROPERTIES = "ConnectionFile.properties";
    private static final String LOG_PROPERTIES = "log4j.properties";
    
	
	public static Connection getConnection() 
	{
	    try 
	    {
	        
	        Properties prop = getProperties(CONNECTION_PROPERTIES);
	        
	        if ( prop == null)
	        {
	            throw new RuntimeException("Sorry ! Unable to Load the DB Properties");
	        }
	        else
	        {
	        	setProperties();
	        }
	        
	        final String driver    = prop.getProperty(DRIVER);
	        final String Url       = prop.getProperty(URL);
	        final String User      = prop.getProperty(USER);
	        final String Password  = prop.getProperty(PASSWORD);

	        Class.forName(driver);
	        
	        con = DriverManager.getConnection(Url,User,Password);
	        
	        if( con == null)
	        {
	            throw new Exception(" Connection is null");
	        }

	    }catch (Exception e1) 
	    {
	        System.out.println(e1);
	        error = e1.getMessage();
	        return null;
	    }
	    return con;
	}

    public static Properties getProperties(String name) throws Exception
	{   
	    try
	    {
	        
	        in = currentThread().getContextClassLoader().getResource(name).openStream();
	        
	        prop = new Properties();
	        
	        prop.load(in);
	        
	        return prop;
	    }
	    finally
	    {
	        if( in != null)
	            in.close();
	    }
	    
	}
    
    public static void setProperties(String key , String value) throws Exception
    {
    	prop = getProperties(LOG_PROPERTIES);
    	
    	URL fileURL = currentThread().getContextClassLoader().getResource(LOG_PROPERTIES) ;
    	
    	if( prop != null)
        {
            prop.setProperty(key, value);
            
            prop.store(new FileOutputStream(new File(fileURL.toURI())),"Log Path Updated");
            
            System.out.println("Log Properties Updated for Key ["+key+"] value ["+value+"]");
        }
    }
    
    private static void setProperties() throws Exception 
    {
        URL fileURL = currentThread().getContextClassLoader().getResource(LOG_PROPERTIES) ;
        
        String url = URLDecoder.decode(isNull(fileURL), "UTF-8");
        
        if( !url.isEmpty())
        {
        	int endString = url.lastIndexOf("Travel") ;
        	
            url = url.substring(url.lastIndexOf(":")+1, endString != -1 ? endString : url.length() ) +"Logs/".concat(LocalDate.now().toString().replaceAll("-", "/"))  ;
            
            System.out.println("Log will be redirected to "+url);
            
            setProperties("log",url);
            
        }
        else
        {
            throw new RuntimeException("Sorry ! Unable to Set the Log Properties"); 
        }
    }
    
    public static String getKeyProperties(String key) throws Exception
    {
        prop = getProperties(LOG_PROPERTIES);
        
        if(prop !=null)
        {
        	System.out.println("Properties key["+key+"] value["+isNull(prop.get(key))+"]");
        	
            return isNull(prop.get(key));
        }
        else
        {
            return "";
        }
        
    }

    public static boolean insertObjects(String Query,Vector values) 
	{
		boolean result = false ;
		Connection con = null;
		PreparedStatement ps = null ;		
		con = getConnection();
		
		if (Query.length() > 0 && values.size() > 0)
		{
			try 
			{
				System.out.println(" Param Vector size : " + values.size());
				
				ps =con.prepareStatement(Query);
				
				int j = 0 ;
				
				int k =1;
				
				for (int i = 0 ;i < values.size();i++)
				{
					
					
					String type = ((Object) values.get(j)).getClass().getSimpleName() +"";
					
					System.out.println(" Vector element " +values.get(j) +" type " +type);
					
					
					if ( type.equals("String"))
					{
						ps.setString(k, (String)values.get(j) );
					}
					else if ( type.equals("Integer"))
					{
						ps.setInt(k, Integer.parseInt(values.get(j)+""));
					}
					else if ( type.equals("Double"))
					{
						ps.setDouble(k, Double.parseDouble(values.get(j)+"") );
					}
					else if ( type.equals("Float"))
					{
						ps.setFloat(k, Float.parseFloat(values.get(j)+"") );
					}					
					j++;k++;
				}
				
				System.out.println(" Booking Query with filter : " + ps);
				
				result = ps.executeUpdate() > 0;
				
				
				
				System.out.println(" Result : " + result);
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try {
					if ( !con.isClosed() && con != null )
					{
						con.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
		
		return result ;
	}
	
	public static boolean updateObjects(String Query,Vector values)
	{
		boolean result = false ;
		Connection con = null;
		PreparedStatement ps = null ;		
		ResultSet rs = null ;
		
		con = getConnection();
		
		if (Query.length() > 0 && values.size() > 0)
		{
			try 
			{
				System.out.println(" Param Vector size : " + values.size());
				
				ps =con.prepareStatement(Query);
				
				int j = 0 ;
				
				int k =1;
				
				for (int i = 0 ;i < values.size();i++)
				{
					
					
					String type = ((Object) values.get(j)).getClass().getSimpleName() +"";
					
					System.out.println(" Vector element " +values.get(j) +" type " +type);
					
					
					if ( type.equals("String"))
					{
						ps.setString(k, (String)values.get(j) );
					}
					else if ( type.equals("Integer"))
					{
						ps.setInt(k, Integer.parseInt(values.get(j)+""));
					}
					else if ( type.equals("Double"))
					{
						ps.setDouble(k, Double.parseDouble(values.get(j)+"") );
					}
					else if ( type.equals("Float"))
					{
						ps.setFloat(k, Float.parseFloat(values.get(j)+"") );
					}					
					j++;k++;
				}
				
				System.out.println(" Booking Query with filter : " + ps);
				
				result = ps.executeUpdate() > 0;
				
				
				
				System.out.println(" Result : " + result);
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try {
					if ( !con.isClosed() && con != null )
					{
						con.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
		
		return result ;
	}
	public static boolean deleteObjects(String Query,Vector values)
	{
		boolean result = false ;
		Connection con = null;
		PreparedStatement ps = null ;		
		ResultSet rs = null ;
		
		con = getConnection();
		
		if (Query.length() > 0 && values.size() > 0)
		{
			try 
			{
				System.out.println(" Param Vector size : " + values.size());
				
				ps =con.prepareStatement(Query);
				
				int j = 0 ;
				
				int k =1;
				
				for (int i = 0 ;i < values.size();i++)
				{
					
					
					String type = ((Object) values.get(j)).getClass().getSimpleName() +"";
					
					System.out.println(" Vector element " +values.get(j) +" type " +type);
					
					
					if ( type.equals("String"))
					{
						ps.setString(k, (String)values.get(j) );
					}
					else if ( type.equals("Integer"))
					{
						ps.setInt(k, Integer.parseInt(values.get(j)+""));
					}
					else if ( type.equals("Double"))
					{
						ps.setDouble(k, Double.parseDouble(values.get(j)+"") );
					}
					else if ( type.equals("Float"))
					{
						ps.setFloat(k, Float.parseFloat(values.get(j)+"") );
					}					
					j++;k++;
				}
				
				System.out.println(" Booking Query with filter : " + ps);
				
				result = ps.executeUpdate() > 0;
				
				
				
				System.out.println(" Result : " + result);
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try {
					if ( !con.isClosed() && con != null )
					{
						con.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
		
		return result ;
	}
	
	public static String buildQuery(Object d,String Operation) throws IllegalArgumentException, IllegalAccessException 
    {
        Class obj = d.getClass();
              
        String columns = "" , values ="", finalQuery  ="";
        
        Field[] fields = obj.getDeclaredFields();
        
        switch(Operation.toUpperCase()) 
        {
            case "INSERT":
            {
                for(Field s : fields)
                {
                    columns +="\n"+s.getName()+",";
                    
                    if ( s.get(d) == null || s.get(d).toString().trim().length() == 0 )
                        values    +=" \n' ',";
                    else
                        values    +="\n'"+s.get(d)+"',";
                }
                
                columns = columns.substring(0,columns.length()-1);
                values = values.substring(0,values.length()-1);
                
                 finalQuery = " INSERT INTO "+obj.getSimpleName()+" \n( "+columns+" )\n VALUES \n ( "+values+" )\n ";
                 break;
            }
            case "UPDATE" :
            {
                for( Field s : fields)
                {
                    if ( s.get(d) == null || s.get(d).toString().trim().length() == 0 )
                    {
                        values += "\n"+s.getName()+" = ";
                        values    +=" ' ',";
                    }
                    else
                    {
                        values += "\n"+s.getName()+" = ";
                        values    +="'"+s.get(d)+"',";
                    }   
                }

                values = values.substring(0,values.length()-1);
                
                finalQuery = " update "+obj.getSimpleName()+" set "+values+" where " + values.replaceAll(",", " and ");
                break;
            }
            case "DELETE" :
            {
                for( Field s : fields)
                {
                    values += "\n"+s.getName()+" = ";
                    
                    if ( s.get(d) == null || s.get(d).toString().trim().length() == 0 )
                        values    +=" ' ',\n";
                    else
                        values    +="'"+s.get(d)+"',\n";
                    
                }

                values = values.substring(0,values.length()-1);
                
                finalQuery = " delete from  "+obj.getSimpleName()+" where " + values.replaceAll(",", " and ");
                break;
            }
            case "SELECT" :
            {
                for( Field s : fields)
                    columns += "\n"+s.getName()+",";
                    
                columns = columns.substring(0,columns.length()-1);
                
                finalQuery = " select "+columns+  " from "+obj.getSimpleName() ;
                break;
            }
            default : 
            {
                System.out.println("Finished");
            }
        }
        return finalQuery;
        
    }
	
	public static String getPropertiesPath(String key)
	{
	    Connection con = null ;

	    try {

	        con = getConnection();

	        String sql =" select value from properties_path where key ='"+isNull(key)+"'";

	        Statement stat = con.createStatement();

	        ResultSet rs = stat.executeQuery(sql);

	        while(rs.next())
	        {
	            System.out.println("KEY => ["+key+" => "+isNull( rs.getString("value") )+"]");
	            
	            return isNull( rs.getString("value") );
	        }

	    }
	    catch(Exception e) 
	    {
	        e.printStackTrace();
	    }
	    finally 
	    {
	        try 
	        {
	            if( con == null && !con.isClosed())
	                con.close();
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
	    }

	    return null; 
	}
}









//--------Put it in classpath------
//ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//InputStream input = classLoader.getResourceAsStream("foo.properties");

//---- Put it in webcontent----
//InputStream input = getServletContext().getResourceAsStream("/WEB-INF/foo.properties");

//---Put it in local disk file system--
//InputStream input = new FileInputStream("/absolute/path/to/foo.properties");

//System.out.println(new File(".").getAbsolutePath());---to get absolute local file path

//InputStream f = new FileInputStream("M:\\eclipse\\DB Connection\\dbdivers\\dbtaxi.properties");
//Properties db = new Properties();
//db.load(f);
