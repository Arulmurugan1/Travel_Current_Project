package com.web.common;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class TextFileToPDF {

  public static void main (String [] args){
    BufferedReader input = null;
    Document output = null;
    System.out.println("Convert text file to pdf");
    System.out.println("input  : M:\\BookingList_2022-07-05.txt");
    System.out.println("output : M:\\BookingList_2022-07-05.pdf");
    try {
      input = 
        new BufferedReader (new FileReader("M:\\BookingList_2022-07-05.txt"));
      // letter 8.5x11
      //    see com.lowagie.text.PageSize for a complete list of page-size constants.
      output = new Document(PageSize.LETTER_LANDSCAPE, 20, 20, 20, 20);
      PdfWriter.getInstance(output, new FileOutputStream ("M:\\BookingList_2022-07-05.pdf"));

      output.open();
      output.addAuthor("RealHowTo");
      output.addSubject("M:\\BookingList_2022-07-05.txt");
      output.addTitle("M:\\BookingList_2022-07-05.txt");

      String line = "";
      while(null != (line = input.readLine())) {
        System.out.println(line);
        Paragraph p = new Paragraph(line);
        p.setAlignment(Element.ALIGN_JUSTIFIED);
        output.add(p);
      }
      System.out.println("Done.");
      output.close();
      input.close();
      System.exit(0);
    }
    catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}