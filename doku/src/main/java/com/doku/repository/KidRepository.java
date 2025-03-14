package com.doku.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.doku.model.Users.Kid;

import java.util.List;

public class KidRepository {

    public void addKid(Kid kid) {
        Session session = com.util.HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(kid);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Kid getKidById(int id) {
        Session session = com.util.HibernateUtil.getSessionFactory().getCurrentSession();
        Kid kid = null;
        try {
            session.beginTransaction();
            kid = session.get(Kid.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return kid;
    }

    public List<Kid> getAllKids() {
        Session session = com.util.HibernateUtil.getSessionFactory().getCurrentSession();
        List<Kid> kids = null;
        try {
            session.beginTransaction();
            kids = session.createQuery("from Kid", Kid.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return kids;
    }

    public void updateKid(Kid kid) {
        Session session = com.util.HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(kid);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteKid(int id) {
        Session session = com.util.HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Kid kid = session.get(Kid.class, id);
            if (kid != null) {
                session.delete(kid);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
