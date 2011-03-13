package com.inpranet.profile.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.inpranet.profile.model.Profile;

class HibernateProfileDao implements IProfileDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveEmploye(Profile profile) {
		
		/* Gérer vous même l'infrastructure technique 
		 * nécessaire (Session / transaction hibernate)
		 * OPTIMISATIONS POSSIBLE : Session locale au thread + Aspect Transaction
		*/
		// Ouverture Session
		Session session = sessionFactory.openSession();

		// Ouverture Transaction
		Transaction tx = session.beginTransaction();
			
		session.saveOrUpdate(profile);
		
		// Fermeture de la transaction
		tx.commit();
		
		// Fermeture de la session
		session.close();
		
	}
	public void saveProfile(Profile employe) {
		// TODO Auto-generated method stub
		
	}
	public Profile getEmployeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Profile getEmployeByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getEmployesCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	public List<Profile> getAllEmployes() {
		// TODO Auto-generated method stub
		return null;
	}
	

}

