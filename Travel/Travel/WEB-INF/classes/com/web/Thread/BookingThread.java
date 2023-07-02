package com.web.Thread;

import java.sql.*;
import java.util.*;

import com.web.util.Dbmanager;

public class BookingThread extends Thread
{	
    static Connection con;
    static ResultSet rs;
    static PreparedStatement ps ;
    String updateQuery = " Update booking set status ='Confirmed' where booking_no =? ";
    
	public BookingThread() {
		super();
		System.out.println("Booking Thread Ready To Update \n");
		
	}

	synchronized public void run()
	{
	    try
	    {
	        try
	        {
	            con = Dbmanager.getConnection();
	            con.setAutoCommit(false);
	            ArrayList<Integer> Booking = new ArrayList<>();

	            String getRACBooking = "Select booking_no from booking where status ='RAC' order by booking_time ";
	            String getWIPBooking = "Select booking_no from booking where status ='WIP' order by booking_time ";

	            ps = con.prepareStatement(getRACBooking);

	            rs = ps.executeQuery();

	            while ( rs.next() ) 
	            {
	                Booking.add(rs.getInt("booking_no"));
	            }

	            getBookingUpdate(Booking, "RAC");
	            
	            System.out.println("Updation Completed");
	           

	            //	        rs = ps.executeQuery(getWIPBooking);
	            //
	            //	        Booking.clear();
	            //
	            //	        while ( rs.next() ) 
	            //	        {
	            //	            Booking.add(rs.getInt("booking_no"));
	            //	        }
	            //
	            //
	            //	        if (Booking.size() > 0 )
	            //	        {
	            //	            for ( int i = 0 ; i < Booking.size() ; i++)
	            //	            {
	            //	                ps.clearParameters();
	            //	                ps.setInt(1, Booking.get(i));
	            //	                int n = ps.executeUpdate(updateQuery);
	            //
	            //	                if ( n>0)
	            //	                    System.err.println("Booking Updated for WIP =>"+Booking.get(i));
	            //	            }
	            //	        }

	        }
	        catch(Exception e)
	        {
	            con.rollback();
	            e.printStackTrace();
	        }
	        finally {
	            ps.close();
	            rs.close();
	        }
	    }
	    catch(Exception e1 ) {
	        e1.printStackTrace();
	    }
	}
	
	public static void main(String[] args) 
	{
	    BookingThread BT = new BookingThread();
	    
	    Thread t1 = new Thread(BT);
	    
	    t1.start();
	    
    }
	
	
	private void getBookingUpdate(ArrayList<Integer> Booking , String status) throws Exception
	{
	    int count = 0;
	    if (Booking.size() > 0 )
        {
            for ( int i = 0 ; i < Booking.size() ; i++)
            {
                ps.clearParameters();

                String updateQuery = this.updateQuery.replaceFirst("\\?", Booking.get(i)+"");
                
                System.out.println(updateQuery);

                int n = ps.executeUpdate(updateQuery);

                if ( n>0)
                    System.err.println("Booking Updated for "+status+"  => "+Booking.get(i) +"  => "+ ++count);

                con.commit();

                sleep(5000);
            }
        }
	}
}
