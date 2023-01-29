package com.web.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.sun.istack.internal.FinalArrayList;

public class Dbmanager {
	
	
	public static Connection con = null;
	public static String error   = null ;
	
	public static Connection getConnection() 
	{
		try {
			
		final String Driver = "com.mysql.cj.jdbc.Driver";
		final String Url = "jdbc:mysql://localhost:3306/taxi?autoReconnect=true&useSSL=false";
		final String User = "root";
		final String Password = "root";
			
//			--------Put it in classpath------
//			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//			InputStream input = classLoader.getResourceAsStream("foo.properties");
			
//			---- Put it in webcontent----
//			InputStream input = getServletContext().getResourceAsStream("/WEB-INF/foo.properties");
			
//			---Put it in local disk file system--
//			InputStream input = new FileInputStream("/absolute/path/to/foo.properties");
			
//			System.out.println(new File(".").getAbsolutePath());---to get absolute local file path
	
//			InputStream f = new FileInputStream("M:\\eclipse\\DB Connection\\dbdivers\\dbtaxi.properties");
//			Properties db = new Properties();
//			db.load(f);
			

			
			Class.forName(Driver);
			con = DriverManager.getConnection(Url,User,Password);

		}catch (Exception e1) 
		{
		    error = e1.getMessage();
			return null;
		}
		return con;
	}
	
	public static boolean insertObjects(String Query,Vector values) 
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
        
        switch(Operation.toUpperCase()) {
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
        System.out.println(finalQuery);
        return finalQuery;
        
    }
}