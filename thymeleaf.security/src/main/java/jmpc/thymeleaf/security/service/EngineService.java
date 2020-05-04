package jmpc.thymeleaf.security.service;

import java.util.Collection;

import jmpc.thymeleaf.security.entity.Engine;

public interface EngineService {

	public Engine findById(int id);
	
	public Engine findByLayout(String layout);
	
	public Collection<Engine> findEngines(); 
	
}
