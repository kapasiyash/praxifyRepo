package com;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.chiron.vd.entity.User;
import com.chiron.vd.utility.HibernateUtil;

public class Demo {

	public static void main(String[] args) {
		
		
		User userEntity = new User();
		
		
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	Transaction tx = session.beginTransaction();
    	
    	List<User> users = session.createQuery("SELECT a FROM User a where a.emailAddress='"+userEntity.getEmailAddress()+"'").list();
    	
    	if(users!=null && !users.isEmpty()) {
    		//Error
    	}
    	
    	
		session.save(userEntity);
		
		System.out.println(userEntity);
		
		
		System.out.println(session);
		session.getTransaction().commit();
		
	}
	
}
