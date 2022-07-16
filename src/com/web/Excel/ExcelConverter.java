package com.web.Excel;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.List;

import com.web.modal.Bookingdao;
import com.web.objects.Booking;

public class ExcelConverter 
{
	public static void main(String[] args) throws Exception
	{
		FileWriter fw = new FileWriter("M:\\Projects\\Pass\\Travel\\Bookings\\Booking"+LocalDate.now()+".txt");
		List<Booking> lst = null;
		Bookingdao dao = new Bookingdao();
		lst = dao.getAllBooking();
		fw.flush();
		for ( Booking b : lst)
		{
			fw.write(b+"\n");
		}
		System.out.println("Excel Generated ..");
		fw.close();
	}
}
