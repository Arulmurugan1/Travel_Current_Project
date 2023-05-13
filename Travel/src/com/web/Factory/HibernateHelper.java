package com.web.Factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.web.common.Generic;
import com.web.common.LoggerFactory;
import com.web.objects.Route;

public class HibernateHelper extends Generic
{
    private static Session session;
    private static SessionFactory factory ;
    private static Transaction t ;
    private static boolean result ;
    public static Session getSession()
    { 
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        session = factory.openSession();     
        return session;
    }
    public static boolean Operation(Route d,String crud)
    {
        try
        {
            logContent("Crud =>"+crud, LoggerFactory.INFO, null);
            session = getSession();
            t = session.beginTransaction();
            if ( crud.equalsIgnoreCase("I") || crud.equalsIgnoreCase("U") )
            {
                System.out.println("Inside Ins_Upd");
                session.saveOrUpdate(d);
                result = true ;
            }
            else
            {
               session.delete(d);
               result = true;
            }
            t.commit();
            System.out.println("Transaction commited ");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            if ( t != null)
            {
                t.rollback();
                System.out.println("Transaction rollbacked");
            }
            result= false;
        }
        finally
        {
            factory.close();  
            if ( session.isOpen() )
            {
                session.flush();
                session.close();
            }
        }
        System.out.println("Object saved ..");
        return result;
    }
    
    public static boolean delete(Route d,String crud) // Query method to delete routes
    {
        try
        {   
            System.out.println(d);   
            Query query=session.createQuery("delete from route where vehicle_no=:no");  
            query.setParameter("no",d.getVehicle_no());  
            int status= query.executeUpdate();
            
            if ( status > 0 )
            {
                result = true;
            }
            else
            {
                result = false;
            }
            t.commit();
            System.out.println("Transaction commited ");
            System.out.println("Object deleted ..");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            if ( t != null )
            {
                t.rollback();
                System.out.println("Transaction rollbacked");
            }
            result = false;
        }
        finally
        {
            factory.close();  
            if ( session.isOpen() )
            {
                session.flush();
                session.close();
            }
        }
        return result ;
    }
}
