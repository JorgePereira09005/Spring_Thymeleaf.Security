package jmpc.thymeleaf.security.dao;

import java.util.Collection;

import jmpc.thymeleaf.security.entity.User;

public interface UserDao {

    public User findByUserName(String userName);
    
    public Collection<User> findUsers();
    
    public void save(User user);
    
    public void deleteUser(int id);
    
}
