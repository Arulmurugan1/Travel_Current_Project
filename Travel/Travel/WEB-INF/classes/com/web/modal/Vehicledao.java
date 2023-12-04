package com.web.modal;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.common.Generic;
import com.web.log4j.LoggerFactory;
import com.web.objects.Vehicle;
import com.web.util.Dbmanager;

public class Vehicledao extends Generic{


    private static final String INSERT_VEHICLE ="INSERT INTO vehicle(vehicle_no, brand, model, color) VALUES(?,?,?,?);" ;
    private static final String SELECT_BY_NO = "select * from vehicle where vehicle_no =?";
    private static final String SELECT_ALL_VEHICLE = "select * from vehicle";
    private static final String DELETE_VEHICLE = "delete from vehicle where vehicle_no = ?;";
    private static final String UPDATE_VEHICLE = "update vehicle set vehicle_model = ?,vehicle_no= ?, vehicle_type =?,vehicle_color=? where vehicle_no = ?;";

    private static Vehicle v ;

    public boolean insertVehicle(Vehicle v)throws SQLException
    {
        boolean v1 =false;
        try {


            ps = con.prepareStatement(INSERT_VEHICLE);

            ps.setString(1,v.getNo() );
            ps.setString(2,v.getModel() );
            ps.setString(3,v.getType() );
            ps.setString(4,v.getColor() );
            logContent(ps,DEBUG , null, this);
            v1 = ps.executeUpdate()>0;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return v1;
    }

    public Vehicle selectVehicle(String no) throws SQLException
    {
        logContent(SELECT_BY_NO,DEBUG , null, this);


        ps = con.prepareStatement(SELECT_BY_NO);

        ps.setString(1, no);
        logContent(ps,DEBUG , null, this);


        rs = ps.executeQuery();

        while (rs.next()) {
            String no1 =rs.getString("vehicle_no");
            String model =rs.getString("vehicle_model");
            String type =rs.getString("vehicle_type");
            String color =rs.getString("vehicle_color");
            v = new Vehicle(no1, model, type, color);

        }
        return v;
    }

    public List < Vehicle > getAllVehicle() throws SQLException
    {
        List<Vehicle> ls = new ArrayList < > ();
        ps = con.prepareStatement(SELECT_ALL_VEHICLE);
        rs = ps.executeQuery();
        while (rs.next()) 
        {
            ls.add(new Vehicle(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4))) ;
        }
        return ls ;
    }

    public boolean deleteVehicle(String no) throws SQLException
    {
        boolean rowDeleted=false;
        logContent(DELETE_VEHICLE,DEBUG , null, this);
        ps = con.prepareStatement(DELETE_VEHICLE);

        ps.setString(1, no);

        rowDeleted = ps.executeUpdate() > 0;

        logContent("Deleted Vehicle "+rowDeleted,DEBUG , null, this);
        return rowDeleted;
    }

    public boolean updateVehicle(Vehicle user)  throws SQLException{
        boolean rowUpdated =false;
        ps = con.prepareStatement(UPDATE_VEHICLE);

        ps.setString(1,v.getModel() );
        ps.setString(2,v.getNo() );
        ps.setString(3,v.getType() );
        ps.setString(4,v.getColor() );
        ps.setString(5, v.getNo());

        rowUpdated = ps.executeUpdate() > 0;

        logContent(ps,DEBUG , null, this);
        return rowUpdated;
    }
}
