package com.arturnowicki.flights;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.arturnowicki.flights.model.HibernateUtils;

public class TmpMainApp {

	public static void main(String[] args) {
		runSession();
	}

	private static void runSession() {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		System.out.println("Run");
		sessionFactory.close();
	}
	
}
