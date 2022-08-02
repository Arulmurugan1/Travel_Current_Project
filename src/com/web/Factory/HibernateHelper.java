package com.web.Factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateHelper
{
	private static Session session;
	private static SessionFactory factory ;
	private static Transaction t ;

	public static Session getSession()
	{ 
		factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		session = factory.openSession();     
		return session;
	}

	public static boolean Operation(Object d,String crud)
	{
		try
		{
			System.out.println("crud "+crud);
			session = HibernateHelper.getSession();
			t = session.beginTransaction();
			if ( crud.equalsIgnoreCase("I") || crud.equalsIgnoreCase("U") )
			{
				System.out.println("Inside Ins_Upd");
				session.saveOrUpdate(d);
			}
			else
			{
				System.out.println("Inside 	Delete");
				session.delete(d);
			}
			t.commit();
			System.out.println("Transaction commited ");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if ( t != null )
			{
				t.rollback();
				System.out.println("Transaction rollbacked");
			}
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
		return true;
	}
}
