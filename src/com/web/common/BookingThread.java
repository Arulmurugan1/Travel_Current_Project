package com.web.common;

import java.sql.Statement;

import com.web.util.Dbmanager;

public class BookingThread 
{
	public int count = 1;
	public int count1 = 0;
	
	public BookingThread() {
		super();
		System.out.println("Booking Thread Ready To Update \n");
		// TODO Auto-generated constructor stub
	}

	public void updateBooking() throws Exception
	{
		String query = " Update booking set status ='Confirmed' , booking_time =sysdate() where round(booking_time) = 0 and booking_no ="+count;
//		String query2 = " Update booking set status ='WIP', booking_time = sysdate() where round(booking_time) = 0 and booking_no ="+count+1;
		
		Statement ps = Dbmanager.getConnection().createStatement();
		int rs = ps.executeUpdate(query);
		if ( rs > 0 ) {
			System.err.println("Booking Confirmed for booking no "+count+"\n");
		}
		else
		{
			if (count >=20 )
					count1++;
				System.err.println("No Booking Found for booking no "+count+"\n");
		}
//		rs = ps.executeUpdate(query2);
//		if ( rs > 0 ) {
//			System.out.println("Booking Wait In Progress for Booking No"+count+"\n");
//		}
//		else
//		{
//			if (count >=20 )
//				count1++;
//			System.out.println("No Booking Found "+"\n");
//		}
			count++;
	}
}
