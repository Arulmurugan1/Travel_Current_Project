package com.web.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.objects.Customer;
import com.web.util.Dbmanager;

public class Customerdao {


    private static final String INSERT_CUSTOMER 		= "INSERT INTO CUSTOMER VALUES(?,?,?,?,?,?,?,null);" ;
    private static final String SELECT_CUSTOMER_BY_ID 	= "select * from CUSTOMER where CUSTOMER_ID =?";
    private static final String SELECT_CUSTOMER_BY_NAME = "select * from CUSTOMER where CUSTOMER_NAME =?";
    private static final String SELECT_ALL_CUSTOMERS	= "select * from CUSTOMER";
    private static final String DELETE_CUSTOMER 		= "delete from CUSTOMER where CUSTOMER_ID = ?;";
    private static final String UPDATE_CUSTOMER 		= "update Customer set start= ?, end =?,email=?,phone=? where CUSTOMER_ID = ?;";

    public Customerdao() {}

    public boolean insertCustomer(Customer user){
        System.out.println(INSERT_CUSTOMER);
        Connection connection =null;
        
        boolean rows =false ;

        try {
        	connection = Dbmanager.getConnection();
        	
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER);
        	
            preparedStatement.setString(1,user.getCustomer_name() );
            preparedStatement.setString(2,user.getStart() );
            preparedStatement.setString(3,user.getEnd() );
            preparedStatement.setString(4,user.getAge() );
            preparedStatement.setString(5,user.getGender() );
            preparedStatement.setString(6,user.getEmail() );
            preparedStatement.setString(7,user.getPhone() );
            
            System.out.println(preparedStatement);
            rows = preparedStatement.executeUpdate()>0;
        } catch(SQLException e) {
        	e.printStackTrace();
        }
        return rows;
    }

    public List< Customer > selectCustomerById(String id)  {
        
        System.out.println(SELECT_CUSTOMER_BY_ID);
        Connection connection = null;
        
        List < Customer > customer = new ArrayList<>();
        
        try{
        	
        	connection = Dbmanager.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);
        		        	
            preparedStatement.setInt(1, Integer.parseInt(id));
            System.out.println(preparedStatement);
            
            
            ResultSet rs = preparedStatement.executeQuery();

            
            while (rs.next()) {
                String name = rs.getString("customer_name");
                String start = rs.getString("start");
                String end = rs.getString("end");
                String age = rs.getString("age");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                String phone = rs.getString("phone_whatsapp");
                int id1 = rs.getInt("customer_id");
                
                customer.add(new Customer(name, start, end, age, gender, email, phone, id1));
            }
        } catch(SQLException e) {
        	e.printStackTrace();;
        }
        return customer ;
    }
    
    public Customer selectCustomerByName(String name1)  {
        Customer user = null;
        System.out.println(SELECT_CUSTOMER_BY_NAME);
        Connection connection = null;
        
        try{
        	
        	 connection = Dbmanager.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_NAME);
        		        	
            preparedStatement.setString(1, name1);
            System.out.println(preparedStatement);
            
            
            ResultSet rs = preparedStatement.executeQuery();

            
            while (rs.next()) {
                String name = rs.getString("customer_name");
                String start = rs.getString("start");
                String end = rs.getString("end");
                String age = rs.getString("age");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                String phone = rs.getString("phone_whatsapp");
                int id1 = rs.getInt("customer_id");
                
                user = new Customer(name, start, end, age, gender, email, phone, id1);
            }
        } catch(SQLException e) {
        	e.printStackTrace();;
        }
        return user;
    }
    
    public List < Customer > getAllCustomer() throws SQLException{
    	
    	System.out.println(SELECT_ALL_CUSTOMERS);

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Customer > users = new ArrayList < > ();
        
        
        Connection connection = null;

        
        
        try 
        {
        	connection = Dbmanager.getConnection();
        	
        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);
            System.out.println(preparedStatement);
            
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	
            	
            	String name = rs.getString("customer_name");
                String start = rs.getString("start");
                String end = rs.getString("end");
                String age = rs.getString("age");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                String phone = rs.getString("phone_whatsapp");
                int id1 = rs.getInt("customer_id");
                 
                 users.add( new Customer(name, start, end, age, gender, email, phone, id1));
                
            }
        }catch(SQLException e) {
        	throw e;
        }
        return users;
    }

    public boolean deleteCustomer(int id){
        boolean rowDeleted=false;
        System.out.println(DELETE_CUSTOMER);
        Connection connection =null;
        try  
        {
        	 connection = Dbmanager.getConnection(); 
        	PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER);
            
        	statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }catch(SQLException e) {
        	e.printStackTrace();;
        }
        return rowDeleted;
    }

    public boolean updateCustomer(Customer user)  {
        boolean rowUpdated =false;
        Connection connection =null;
        try
        {
        	connection = Dbmanager.getConnection(); 
        	PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER);
            
        	statement.setString(1, user.getStart());
        	statement.setString(2, user.getEnd());
        	statement.setString(3, user.getEmail());
        	statement.setString(4, user.getPhone());
            statement.setInt(5, user.getCustomer_id());
            

            rowUpdated = statement.executeUpdate() > 0;
        }catch(SQLException e) {
        	 e.printStackTrace();;
        }
        return rowUpdated;
    }

}
