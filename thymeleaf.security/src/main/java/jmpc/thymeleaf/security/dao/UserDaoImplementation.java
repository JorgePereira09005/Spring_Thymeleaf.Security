package jmpc.thymeleaf.security.dao;

import java.util.Collection;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jmpc.thymeleaf.security.entity.User;

@Repository
public class UserDaoImplementation implements UserDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public User findByUserName(String username) {
		
		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//read from the database using userName
		Query<User> query = currentSession.createQuery("from User where userName=:uName", User.class);
		query.setParameter("uName", username);
		
		User user = null;
		
		try {
			user = query.getSingleResult();
		}
		catch(Exception e) {
			user = null;
		}
		
		return user;
	}
	
	@Override
	public Collection<User> findUsers() {
		
		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		return currentSession.createQuery("SELECT a FROM User a", User.class).getResultList();      
	}

	@Override
	public void save(User user) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		//persist user into the database
		currentSession.saveOrUpdate(user);

	}

	@Override
	public void deleteUser(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<User> query = currentSession.createQuery("delete from User e where e.id =:id");
		query = query.setParameter("id", id);
		query.executeUpdate();
	}

}
