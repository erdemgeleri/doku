package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.doku.model.Users.Kid;
import com.doku.model.Users.Parent;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	
	
	static {
		try {
			sessionFactory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(com.doku.model.Users.User.class)
					.addAnnotatedClass(com.doku.model.Users.Parent.class)
					.addAnnotatedClass(com.doku.model.Users.Kid.class)
					.buildSessionFactory();
		}
		catch (Throwable ex){
            System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static void shutdown() {
		getSessionFactory().close();
	}
}



