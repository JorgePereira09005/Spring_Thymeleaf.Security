package jmpc.thymeleaf.security.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jmpc.thymeleaf.security.dao.RoleDao;
import jmpc.thymeleaf.security.dao.UserDao;
import jmpc.thymeleaf.security.entity.NewUser;
import jmpc.thymeleaf.security.entity.Role;
import jmpc.thymeleaf.security.entity.User;

@Service
public class UserServiceImplementation implements UserService {

	// inject user and role DAO
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	//encrypt password using BCrypt
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(NewUser newUser) {
		
		//process newUser to transform it into an User object
		
		User user = new User();
		
		 // assign user details to the user object
		user.setUserName(newUser.getUserName());
		user.setPassword(passwordEncoder.encode(newUser.getPassword()));
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setEmail(newUser.getEmail());

		// set default role of "employee"
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));

		 // persist user in the database
		userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void deleteUser(int id) {
		this.userDao.deleteUser(id);
		
	}

	@Override
	@Transactional
	public Collection<User> findUsers() {
		return this.userDao.findUsers();
	}
}
