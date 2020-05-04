package jmpc.thymeleaf.security.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import jmpc.thymeleaf.security.entity.Engine;
import jmpc.thymeleaf.security.entity.User;
import jmpc.thymeleaf.security.service.EngineService;
import jmpc.thymeleaf.security.service.UserService;

@Controller
public class MainController {

	@Autowired
	UserService userService;
	
	@Autowired
	EngineService engineService;
	
	@GetMapping("/")
	public String showHome() {
		
		return "home";
	}
	
	// add request mapping for /managers
	@GetMapping("/managers")
	public String showManagers(Model model) {
		
		Collection<Engine> listEngines = this.engineService.findEngines();
		
		model.addAttribute("listEngines", listEngines);
		
		return "managers";
	}
	
	//request mapping for /admins
	@GetMapping("/admins")
	public String showAdmins(Model model) {
		
		Collection<User> listUsers = this.userService.findUsers();
		
		model.addAttribute("listUsers", listUsers);
		
		return "admins";
	}
	
	
	//delete a user based on id
	@GetMapping("/admins/{id}")
	public String deleteUser(@PathVariable(value="id") int id, Model model) {
		
		System.out.println("deleting user with id: " + id);
		this.userService.deleteUser(id);
		
		Collection<User> listUsers = this.userService.findUsers();
		model.addAttribute("listUsers", listUsers);
		
		return "admins";
	}
	

}
