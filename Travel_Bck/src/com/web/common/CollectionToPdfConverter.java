package com.web.common;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.web.modal.Bookingdao;
import com.web.objects.Booking;

public class CollectionToPdfConverter 
{
	public static void main(String[] args) throws Exception 
	{
		generatePdf();
	}

	private static void generatePdf() throws Exception 
	{
		String file = "M:\\Pdf\\PdfGenerator.pdf"; 
		
		File f = new File(file);
		
		if(!f.exists())
		{
			if(f.getParentFile().mkdir())
			{
				f.createNewFile();
			}
		}
		
		Document document = new Document();
		
		
		PdfWriter.getInstance(document,new FileOutputStream(file));
		
		document.open();
		
		document.add(new Paragraph("Pdf generated on : "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm:ss a"))+"\r\n\r\n"));
		
		document.addAuthor("SK Travels");
		
		document.addTitle("Welcome To SK Travels");
		
		document.setMarginMirroring(true);
		
		ArrayList<Object> getList = getBooking();
		
		ArrayList<String> getColumns = (ArrayList) getList.get(0);
		
		ArrayList<Booking> getData	= (ArrayList) getList.get(1);
		
		PdfPTable table = new PdfPTable(6);
		
		int i = 0 ;
		
		for(String columns : getColumns )
		{
			if( i++ == 6)
			{
				break;
			}
			table.addCell(new PdfPCell(new Phrase(columns)));
		}
		
		table.setHeaderRows(1);
		
		for(Booking data : getData)
		{
			table.addCell(data.getBooking_no()+"");
			table.addCell(data.getPickup_from());
			table.addCell(data.getDrop_at());
			table.addCell(data.getCustomer_id()+"");
			table.addCell(data.getVehicle_no());
			table.addCell(data.getDriver_id());
//			table.addCell(data.getFare()+"");
//			table.addCell(data.getBooked_by());
//			table.addCell(data.getStatus());
//			table.addCell(data.getBooking_time()+"");
//			table.addCell(data.getCustomer_name());
//			table.addCell(data.getAge());
//			table.addCell(data.getGender());
//			table.addCell(data.getStart());
//			table.addCell(data.getEmail());
//			table.addCell(data.getEnd());
//			table.addCell(data.getPhone());
//			table.addCell(data.getStatus());
			
		}
		
		document.add(table);
		
		document.add(new Paragraph("\r\r\n Thank you Visit Again .. "));
		
		document.close();
		
		
		
	}
	
	
	private static ArrayList<Object> getBooking() throws Exception
	{
		Bookingdao bd = Bookingdao.getInstance();
		
		bd.getAllBooking();
		
		ArrayList<Object> book = new ArrayList<>();
		ArrayList<String> book1 = new ArrayList<>();
		
		ResultSet rs = bd.getResultSet() ;
		
		for ( int index = 1 ; index <=  rs.getMetaData().getColumnCount() ; index++ )
        {
			book1.add(StringChecker.Init(rs.getMetaData().getColumnName(index).toLowerCase()));
        }
		
		book.add(book1.clone());
		book.add(bd.getAllBooking());
		
		bd.closeAll();
		
		return book ;
	}
	
}
