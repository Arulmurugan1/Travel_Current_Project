package com.web.modal;

import java.sql.*;
import java.util.*;

import com.web.objects.Customer;
import com.web.util.Dbmanager;

public class Customerdao {


    private static final String INSERT_CUSTOMER 		= "INSERT INTO CUSTOMER VALUES(?,?,?,?,?,?,?,null);" ;
    private static final String SELECT_CUSTOMER_BY_ID 	= "select * from CUSTOMER where CUSTOMER_ID =?";
    private static final String SELECT_CUSTOMER_BY_NAME = "select * from CUSTOMER where CUSTOMER_NAME =?";
    private static final String SELECT_ALL_CUSTOMERS	= "select * from CUSTOMER";
    private static final String DELETE_CUSTOMER 		= "delete from CUSTOMER where CUSTOMER_ID = ?;";
    private static final String UPDATE_CUSTOMER 		= "update Customer set start= ?, end =?,email=?,phone=? where CUSTOMER_ID = ?;";

    Connection con 		 ;
    ResultSet rs 		 ;
    PreparedStatement ps ;
    
    public Customerdao() 
    {
    	con = Dbmanager.getConnection();
    }

    public int insertCustomer(Customer user){
        System.out.println(INSERT_CUSTOMER);
        int result = 0;
        try {
        	
        	ps = con.prepareStatement(INSERT_CUSTOMER,Statement.RETURN_GENERATED_KEYS);
        	
            ps.setString(1,user.getCustomer_name() );
            ps.setString(2,user.getStart() );
            ps.setString(3,user.getEnd() );
            ps.setString(4,user.getAge() );
            ps.setString(5,user.getGender() );
            ps.setString(6,user.getEmail() );
            ps.setString(7,user.getPhone() );
            
            System.out.println(ps);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            
            while ( rs.next() )
            {
            	result = rs.getInt(1);
            	System.out.println("Generated Customer ::"+result);
            }
            
        } catch(SQLException e) {
        	e.printStackTrace();
        }
        return result;
    }

    public List< Customer > selectCustomerById(String id)  {
        
        System.out.println(SELECT_CUSTOMER_BY_ID);
       
        List < Customer > customer = new ArrayList<>();
        
        try{
        	
        	
           ps = con.prepareStatement(SELECT_CUSTOMER_BY_ID);
        		        	
            ps.setInt(1, Integer.parseInt(id));
            System.out.println(ps);
            
            
            ResultSet rs = ps.executeQuery();

            
            while (rs.next()) {
                customer.add( new Customer( rs.getString(1),rs.getString(2), rs.getString(3),  rs.getString(4)
                			, rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8) ) );
            }
        } catch(SQLException e) {
        	e.printStackTrace();
        }
        return customer ;
    }
    
    public Customer selectCustomerByName(String name1)  {
        Customer user = null;
        System.out.println(SELECT_CUSTOMER_BY_NAME);
       
        try{
        	
        	
        	 ps = con.prepareStatement(SELECT_CUSTOMER_BY_NAME);
        		        	
            ps.setString(1, name1);
            System.out.println(ps);
            
            
            ResultSet rs = ps.executeQuery();

            
            while (rs.next()) {
            	int c=1;      
                user = new Customer(rs.getString(c++), rs.getString(c++), rs.getString(c++),  rs.getString(c++), 
                		rs.getString(c++), rs.getString(c++),rs.getString(c++), rs.getInt(c++));
            }
        } catch(SQLException e) {
        	e.printStackTrace();;
        }
        return user;
    }
    
    public List < Customer > getAllCustomer(){
    	
    	System.out.println(SELECT_ALL_CUSTOMERS);

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Customer > users = new ArrayList < > ();
        
       
        try 
        {
        
        	
        	 ps = con.prepareStatement(SELECT_ALL_CUSTOMERS);
            System.out.println(ps);
            
            // Step 3: Execute the query or update query
            ResultSet rs = ps.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                 users.add( new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
                		 rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
                
            }
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        return users;
    }

    public boolean deleteCustomer(int id){
        boolean result=false;
        System.out.println(DELETE_CUSTOMER);
        
        try  
        {
        	 
        	ps = con.prepareStatement(DELETE_CUSTOMER);
            
        	ps.setInt(1, id);
        	result = ps.executeUpdate() > 0;
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        return result;
    }

    public boolean updateCustomer(Customer user)  {
    	boolean result = false;
        try
        {     	
        	ps = con.prepareStatement(UPDATE_CUSTOMER);   
        	ps.setString(1, user.getStart());
        	ps.setString(2, user.getEnd());
        	ps.setString(3, user.getEmail());
        	ps.setString(4, user.getPhone());
            ps.setInt(5, user.getCustomer_id());
            result =  ps.executeUpdate() > 0 ;
        }catch(SQLException e) {
        	 e.printStackTrace();;
        }
        return result;
    }
    
    public void closeAll() throws Throwable
	{
		if ( con !=null && !con.isClosed())
		{
			con.close();
			con = null;
			System.out.println("Connection Closed ::"+con);
		}
		if ( ps !=null )
		{
			ps.close();
			ps = null;
			System.out.println("Statement Closed ::"+ps);
		}
		if ( rs !=null )
		{
			rs.close();
			rs = null;
			System.out.println("Resultset Closed ::"+rs);
		}
	}

}
