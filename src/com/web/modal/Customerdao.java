package com.web.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.web.objects.Customer;
import com.web.util.Dbmanager;

public class Customerdao {

    private static final String INSERT_CUSTOMER 		= "INSERT INTO CUSTOMER VALUES(null,?,?,?,?,?,?,?)" ;
    private static final String SELECT_CUSTOMER_BY_ID 	= "select * from CUSTOMER where CUSTOMER_ID =?";
    private static final String SELECT_ALL_CUSTOMERS	= "select * from CUSTOMER";
    private static final String DELETE_CUSTOMER 		= "delete from CUSTOMER where CUSTOMER_ID = ?;";
    private static final String UPDATE_CUSTOMER 		= "update Customer set start= ?, end =?,email=?,phone=? where CUSTOMER_ID = ?";

    Connection con ; ResultSet rs ; PreparedStatement ps		 ;

    public Customerdao() 
    {
        con = Dbmanager.getConnection();
    }

    public int insertCustomer(Customer user) throws SQLException
    {
        int result = 0;
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


        return result;
    }

    public List<Customer> selectCustomerById(String id)  throws SQLException
    {

        System.out.println(SELECT_CUSTOMER_BY_ID);

        List<Customer> ls = new ArrayList<>() ;
        Customer c = null ;


        ps = con.prepareStatement(SELECT_CUSTOMER_BY_ID);

        ps.setInt(1, Integer.parseInt(id));
        System.out.println(ps);


        ResultSet rs = ps.executeQuery();


        while (rs.next()) 
        {
            c = new Customer();
            c.setAge(rs.getString("age"));
            c.setCustomer_id(rs.getInt("customer_id"));
            c.setCustomer_name(rs.getString("customer_name"));
            c.setEmail(rs.getString("email"));
            c.setEnd(rs.getString("end"));
            c.setGender(rs.getString("gender"));
            c.setPhone(rs.getString("phone"));
            c.setStart(rs.getString("start"));	
            ls.add(c);
        }

        return ls ;
    }

    public List < Customer > getAllCustomer() throws SQLException
    {

        System.out.println(SELECT_ALL_CUSTOMERS);

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Customer > users = new ArrayList < > ();


        ps = con.prepareStatement(SELECT_ALL_CUSTOMERS);
        System.out.println(ps);

        // Step 3: Execute the query or update query
        ResultSet rs = ps.executeQuery();

        // Step 4: Process the ResultSet object.
        while ( rs.next() ) 
        {
            Customer c = new Customer();
            c.setAge(rs.getString("age"));
            c.setCustomer_id(rs.getInt("customer_id"));
            c.setCustomer_name(rs.getString("customer_name"));
            c.setEmail(rs.getString("email"));
            c.setEnd(rs.getString("end"));
            c.setGender(rs.getString("gender"));
            c.setPhone(rs.getString("phone"));
            c.setStart(rs.getString("start"));	
            users.add(c);
        }

        return users;
    }

    public boolean deleteCustomer(int id) throws SQLException
    {
        boolean result=false;
        System.out.println(DELETE_CUSTOMER);

        ps = con.prepareStatement(DELETE_CUSTOMER);

        ps.setInt(1, id);
        result = ps.executeUpdate() > 0;

        return result;
    }

    public boolean updateCustomer(Customer user) throws SQLException
    {
        boolean result = false;

        ps = con.prepareStatement(UPDATE_CUSTOMER);   
        ps.setString(1, user.getStart());
        ps.setString(2, user.getEnd());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPhone());
        ps.setInt(5, user.getCustomer_id());
        result =  ps.executeUpdate() > 0 ;

        return result;
    }

    public void closeAll() throws Exception
    {
        if ( con !=null && !con.isClosed())
        {
            con.close();
        }
        if ( ps !=null && !ps.isClosed())
        {
            ps.close();
        }
        if ( rs !=null  && !rs.isClosed())
        {
            rs.close();
        }
        System.out.println("Connection ["+con+"] Statement ["+ps+"] Resultset ["+rs+"]");
    }

}
