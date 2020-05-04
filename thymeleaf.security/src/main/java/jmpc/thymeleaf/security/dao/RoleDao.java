package jmpc.thymeleaf.security.dao;

import jmpc.thymeleaf.security.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String roleName);
}
