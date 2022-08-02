package com.web.Excel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.web.util.Dbmanager;

public class ExcelConverter 
{
	
	 static void insert() throws Exception
	{
		 int id =0;
		
		String data ="Willys: MB";
		
		String[] brand = data.split(":");
		String[] models  = brand[1].split(",");
		
		System.out.println(models.length);
		System.out.print(brand[0]);
		System.out.println();
		
		String[] code = brand[0].split(" ");
		
		String splitCode = "";
		
		if ( code.length == 2)
		{
			splitCode = code[0].substring(0,1) +""+code[1].substring(0,1);
		}
		else
		{
			splitCode = code[0].substring(0,2);
		}
		
		System.out.println(splitCode);
			
		Connection con = Dbmanager.getConnection();
		
		try {
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO CAR_BRAND VALUES (null,?,?,?)");
			int cntl = 0;
			ps.setString(++cntl, splitCode.toUpperCase() );
			ps.setString(++cntl, brand[0] );
			ps.setString(++cntl,"India" );
			
			System.out.println(ps);
			
			int result = ps.executeUpdate() ;
			
			if ( result > 0)
			{
				System.out.println("Brand Insertion Success");
				PreparedStatement ps1 = con.prepareStatement("Select ID from car_brand where code = ?");
				int c = 0;
				ps1.setString(++c, splitCode.toUpperCase() );
				
				ResultSet rs = ps1.executeQuery() ;
				
				if ( rs.next() )
				{
					System.out.println(rs.getInt(1));
					id = rs.getInt(1);
				}
			}
			
			for ( String s : models)
			{
				PreparedStatement ps2 = con.prepareStatement("INSERT INTO MODEL VALUES (null,?,?,?)");
				int c1 = 0;
				ps2.setInt(++c1,id);
				ps2.setString(++c1, brand[0] );
				ps2.setString(++c1, s );
				
				System.out.println(ps2);
				
				int result2 = ps2.executeUpdate() ;
				
				if ( result2 > 0 )
				{
					System.out.println("Model Insertion Success");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		insert();
	}
}
