package com.web.Factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryGenerator 
{
	private static Session session;
	private static SessionFactory factory ;
	
	public static Session getSessionFactory()
	{
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
	    Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
	  
	    factory = meta.getSessionFactoryBuilder().build();  
	    Session session = factory.openSession();  
	    
	    return session;
	}
	
	public static boolean insertObjects(Object d)
	{
			session = SessionFactoryGenerator.getSessionFactory();
			Transaction t = session.beginTransaction();
			session.save(d);
			t.commit();
			System.out.println("Object saved ..");
			return true;
	}
	
	public static void shutDown()
	{
		factory.close();  
	    session.close();
	}
}
