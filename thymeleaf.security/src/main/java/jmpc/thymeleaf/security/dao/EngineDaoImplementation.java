package jmpc.thymeleaf.security.dao;

import java.util.Collection;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jmpc.thymeleaf.security.entity.Engine;
import jmpc.thymeleaf.security.entity.User;

@Repository
public class EngineDaoImplementation implements EngineDao{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Engine findById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Engine> query = currentSession.createQuery("from Engine where id=:theId", Engine.class);
		query.setParameter("theId", theId);
		
		Engine engine = null;
		
		try {
			engine = query.getSingleResult();
		} catch (Exception e) {
			engine = null;
		}

		return engine;
		
	}

	@Override
	public Engine findByLayout(String theLayout) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Engine> query = currentSession.createQuery("from Engine where layout=:theLayout", Engine.class);
		query.setParameter("theLayout", theLayout);
		
		Engine engine = null;
		
		try {
			engine = query.getSingleResult();
		} catch (Exception e) {
			engine = null;
		}

		return engine;
	}

	@Override
	public Collection<Engine> findEngines() {
		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		return currentSession.createQuery("SELECT a FROM Engine a", Engine.class).getResultList();
	}

}
