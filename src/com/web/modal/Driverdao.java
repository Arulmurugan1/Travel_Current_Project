package com.web.modal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.common.Generic;
import com.web.objects.Driver;

public class Driverdao extends Generic{

    boolean rowsAffected =false ;

    private static final String INSERT_DRIVER ="INSERT INTO DRIVER VALUES(null,?,?,?,?,?,?)" ;
    private static final String SELECT_BY_ID = "select * from DRIVER where driver_id =?";
    private static final String SELECT_ALL_DRIVER = "select * from DRIVER";
    private static final String DELETE_DRIVER = "delete from DRIVER where vehicle_no = ?;";
    private static final String UPDATE_DRIVER = "update DRIVER set driver_id = ?,driver_name= ?, gender =?,age=?,city=?,phone=?,vehicle_no=? where driver_id = ?";

    Driver v = null;

    public boolean insertDriver(Driver d)throws SQLException
    {
        System.out.println(INSERT_DRIVER);

        ps = con.prepareStatement(INSERT_DRIVER);
        int cntl = 0;
        ps.setString(++cntl,d.getName() );
        ps.setInt(++cntl,d.getAge() );
        ps.setString(++cntl,d.getGender() );
        ps.setString(++cntl,d.getCity() );
        ps.setString(++cntl,d.getPhone() );
        ps.setString(++cntl,d.getNo() );

        System.out.println(ps);

        rowsAffected = ps.executeUpdate() > 0;

        return rowsAffected;
    }

    public Driver selectDriver(int id)  throws SQLException
    {
        System.out.println(SELECT_BY_ID);

        ps = con.prepareStatement(SELECT_BY_ID);
        int cntl = 0;
        ps.setInt(++cntl, id);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            v = new Driver
                    (	rs.getString("driver_name"), rs.getString("gender"), rs.getString("city"), 
                            rs.getString("phone"),rs.getString("vehicle_no"),rs.getInt("age"),rs.getInt("driver_id")	);

        }
        return v;
    }

    public List < Driver > getAllDriver() throws SQLException
    {

        List < Driver > users = new ArrayList < Driver > ();

        ps = con.prepareStatement(SELECT_ALL_DRIVER);
        System.out.println(ps);


        rs = ps.executeQuery();


        while (rs.next()) {

            users.add(new Driver
                    (	    rs.getString("driver_name"),
                            rs.getString("gender"), 
                            rs.getString("city"), 
                            rs.getString("phone"),
                            rs.getString("vehicle_no"),
                            rs.getInt("age"),
                            rs.getInt("driver_id")	)	
                    );
        }
        return users;
    }

    public boolean deleteDriver(String id)throws SQLException
    {
        ps = con.prepareStatement(DELETE_DRIVER);
        int cntl = 0;
        ps.setString(++cntl, id);
        System.out.println(ps);
        rowsAffected = ps.executeUpdate() > 0;
        System.out.println("Deleted driver"+rowsAffected);
        return rowsAffected;
    }

    public boolean updateDriver(Driver user) throws SQLException
    {
        boolean rowUpdated =false;
        ps = con.prepareStatement(UPDATE_DRIVER);
        int cntl = 0;
        ps.setInt(++cntl,v.getId());
        ps.setString(++cntl,v.getName() );
        ps.setString(++cntl,v.getGender() );
        ps.setInt(++cntl,v.getAge() );
        ps.setString(++cntl,v.getCity() );
        ps.setString(++cntl,v.getPhone() );
        ps.setString(++cntl,v.getNo() );
        ps.setInt(++cntl,v.getId());
        rowUpdated = ps.executeUpdate() > 0;
        return rowUpdated;
    }
}
