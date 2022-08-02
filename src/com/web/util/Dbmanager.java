package com.web.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class Dbmanager {
	
	
	static Connection con = null;
	
	public static Connection getConnection() 
	{
		try {
			
//			--------Put it in classpath------
//			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//			InputStream input = classLoader.getResourceAsStream("foo.properties");
			
//			---- Put it in webcontent----
//			InputStream input = getServletContext().getResourceAsStream("/WEB-INF/foo.properties");
			
//			---Put it in local disk file system--
//			InputStream input = new FileInputStream("/absolute/path/to/foo.properties");
			
//			System.out.println(new File(".").getAbsolutePath());---to get absolute local file path
	
			InputStream f = new FileInputStream("M:\\eclipse\\DB Connection\\dbdivers\\dbtaxi.properties");
			Properties db = new Properties();
			db.load(f);
			

			
			Class.forName(db.getProperty("driver"));
			con = DriverManager.getConnection(db.getProperty("url"), db.getProperty("user"), db.getProperty("password"));

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
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
}