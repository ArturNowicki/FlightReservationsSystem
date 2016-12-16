package com.arturnowicki.flights.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import com.arturnowicki.flights.model.HibernateUtils;

public class CommonDaoImpl implements CommonDao {

	@Override
	public <T> T add(T t) {
		Session session = startTransaction();
		try {
			session.save(t);
			Hibernate.initialize(t);
			session.getTransaction().commit();
			return t;
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public <T> void update(T t) {
		Session session = startTransaction();
		try {
			session.update(t);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public <T> void delete(T t) {
		Session session = startTransaction();
		try {
			session.delete(t);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public <T> Optional<T> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Class<T> type) {
		Session session = startTransaction();
		List<T> tList = session.createCriteria(type).list();
		Hibernate.initialize(tList);
		session.getTransaction().commit();
		return tList;
	}

	private static Session startTransaction() {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		return session;
	}
}
