package com.doku.repository;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.doku.model.Users.Kid;
import com.doku.model.Users.Parent;
import com.doku.model.Users.PasswordUtils;
import com.util.HibernateUtil;

public class ParentRepository {
	
	private SessionFactory sessionFactory;
	public void addParent(Parent parent) {
		Session session = com.util.HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(parent);
			transaction.commit();
		}catch(Exception ex) {
			if(transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}finally{
			session.close();
		}
	}
	public Parent authenticateParent(String email, String password) {
	    if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
	        System.out.println("Email veya şifre boş!");
	        return null;
	    }

	    email = email.trim(); 
	    System.out.println("Sorgulanan email: " + email);

	    Session session = null;
	    Parent parent = null;

	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        session.beginTransaction();

	        Query<Parent> query = session.createQuery("FROM Parent WHERE e_mail = :email", Parent.class);
	        query.setParameter("email", email);
	        List<Parent> parents = query.getResultList();

	        System.out.println("Bulunan kayıt sayısı: " + parents.size() );
	        
	        if (parents.size() != 1) {
	            System.out.println("Email bulunamadı veya birden fazla kayıt var: " + email);
	            if (session.getTransaction() != null && session.getTransaction().isActive()) {
	                session.getTransaction().rollback();
	            }
	            return null;
	        }

	        parent = parents.get(0);
	        if(parent == null) {
	        	System.out.println("Null geliyo hala");
	        }
	        
	        
	        if (parent == null || !PasswordUtils.checkPassword(password, parent.getPassword())) {
	        	System.out.println(PasswordUtils.checkPassword(password, parent.getPassword()));
	        	System.out.println("Frame'den gelen şifre: "+ password);
	            System.out.println("Geçersiz şifre!");
	            if (session.getTransaction() != null && session.getTransaction().isActive()) {
	                session.getTransaction().rollback();
	            }
	            return null;
	        }

	        session.getTransaction().commit();
	        System.out.println("Giriş başarılı: " + parent.getEmail());
	        return parent;
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        if (session != null && session.getTransaction() != null && session.getTransaction().isActive()) {
	            session.getTransaction().rollback();
	        }
	        return null;
	    } finally {
	        // Session'ı kapat
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	}
    public void addKidToParent(Long parentId, Kid kid) {
        try (Session session = com.util.HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                Parent parent = session.get(Parent.class, parentId); 
                if (parent != null) {
                    parent.getKids().add(kid); 
                    kid.setParent(parent); 
                    session.save(kid); 
                    transaction.commit(); 
                }
            } catch (Exception ex) {
                if (transaction != null) {
                    transaction.rollback();
                }
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public Parent getParentById(long id) {
		Session session = com.util.HibernateUtil.getSessionFactory().getCurrentSession();
		Parent parent = null;
		try {
			session.beginTransaction();
			parent = session.get(Parent.class, id);
			session.getTransaction().commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return parent;
	}
	
	

	
	public List<Parent> getAllParent(){
		Session session = com.util.HibernateUtil.getSessionFactory().getCurrentSession();
		List<Parent> parents = null;
		try {
			session.beginTransaction();
			parents = session.createQuery("from Parent", Parent.class).getResultList();
			session.getTransaction().commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return parents;
	}
	
	public void updateParent(Parent parent) {
		Session session = com.util.HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.update(parent);
			transaction.commit();
		}catch(Exception ex) {
			if(transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public void deleteParent(int id) {
	    Session session = com.util.HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction transaction = null;

	    try {
	        transaction = session.beginTransaction();
	        Parent parent = session.get(Parent.class, id);
	        if (parent != null) {
	            session.delete(parent);  
	        }
	        transaction.commit();
	    } catch (Exception ex) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        ex.printStackTrace();
	    } finally {
	        session.close();
	    }
	}
}
