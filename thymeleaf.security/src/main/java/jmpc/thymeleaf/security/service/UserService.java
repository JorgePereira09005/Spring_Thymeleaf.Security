package jmpc.thymeleaf.security.service;

import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetailsService;

import jmpc.thymeleaf.security.entity.NewUser;
import jmpc.thymeleaf.security.entity.User;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(NewUser newUser);
	
	public void deleteUser(int id);
	
	public Collection<User> findUsers();
	
}

