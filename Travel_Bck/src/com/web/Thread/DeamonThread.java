package com.web.Thread;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DeamonThread extends Thread
{
    private static boolean isDeamon = false ;
    private static int count = 0 ,time = 0 ,exitLoop = 0 ;
    
    
    public void run()
    {
        FileWriter fw =  null ;
        FileReader fr =  null;
        
        System.out.println("$$$$$$$$$$$$$$$ Deamon Thread Operation Starts $$$$$$$$$$$\n");
        
        if(DeamonThread.currentThread().isDaemon())
            System.out.println("$$$$$$$$$$$$$$$ Deamon Thread Starts $$$$$$$$$$$\n");
        
        System.out.println("Started At "
                    +LocalDateTime.now().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm:ss a"))+"\n" );
        
        try
        {
            
            try {
                fr = new FileReader("/DeamonThread.txt");
            }catch(FileNotFoundException e) {
                System.out.println("DeamonThread.txt File Not Found");
            }
            
            int fri;
            String content = "";
            
            if( fr != null)
            {
                while ( ( fri=fr.read() )!=-1)
                    content += (char)fri ;

                fr.close();
            }
            fw = new FileWriter("/DeamonThread.txt");
            
            fw.write(content);
            
            fw.append("\n\n\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Deamon Thread Operation Starts $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
            fw.append("Started At "
                    +LocalDateTime.now().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm:ss a"))+"\n" );
            while(true)
            {
                System.out.println("Deamon Thread Loop count "+ ++count +"\n");
                fw.append("Deamon Thread Loop count "+ count +" Active Thread "+DeamonThread.activeCount()+"\n");
                DeamonThread.sleep(time*1000);
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try 
            {
                fw.append("Ended At "
                        +LocalDateTime.now().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm:ss a"))+"\n" );
                fw.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Deamon Thread Operation Ends $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                
                System.out.println("Ended At "
                        +LocalDateTime.now().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm:ss a"))+"\n" );
                System.out.println("$$$$$$$$$$$$$$$ Deamon Thread Operation ends $$$$$$$$$$$\n");
                
                fw.flush();
                fw.close();
                
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            
        }
        
        
    }
    
    public static void main(String[] args) throws Exception 
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the gap duaration in sec: ");
        
        time = sc.nextInt();
        
        System.out.println("Enter the when to Stop this in sec: ");
        
        exitLoop = sc.nextInt();
        
        if(time > 0 )
            isDeamon = true ; 
        
        DeamonThread t1 = new DeamonThread();
        
        t1.setDaemon(isDeamon);
        
        t1.setPriority(MAX_PRIORITY);
        
        System.out.println("DeamonThread is a deamon thread => "+t1.isDaemon());
        
        t1.start();
        
        sc.close();
        
//        DeamonThread.sleep(exitLoop*1000);
        
//        t1.interrupt();
        
//        t1.join();
        
//        System.out.println("Deam Thread Ends Now");
        
//        System.gc();
    }
}
