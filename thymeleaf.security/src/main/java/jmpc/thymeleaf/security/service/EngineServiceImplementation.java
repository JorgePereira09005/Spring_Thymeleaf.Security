package jmpc.thymeleaf.security.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jmpc.thymeleaf.security.dao.EngineDao;
import jmpc.thymeleaf.security.entity.Engine;

@Service
public class EngineServiceImplementation implements EngineService{

	@Autowired
	private EngineDao engineDao;
	
	@Override
	@Transactional
	public Engine findById(int id) {
		return engineDao.findById(id);
	}

	@Override
	@Transactional
	public Engine findByLayout(String layout) {
		return engineDao.findByLayout(layout);
	}

	@Override
	@Transactional
	public Collection<Engine> findEngines() {
		return this.engineDao.findEngines();
	}

}
