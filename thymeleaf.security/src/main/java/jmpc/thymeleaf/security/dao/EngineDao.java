package jmpc.thymeleaf.security.dao;

import java.util.Collection;

import jmpc.thymeleaf.security.entity.Engine;

public interface EngineDao {

	public Engine findById(int id);
	
	public Engine findByLayout(String layout);
	
	public Collection<Engine> findEngines(); 
}
