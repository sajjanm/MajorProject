package net.kzn.onlineshopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.User;

@Controller
public class UserService {
	
	@Autowired
	private UserDAO userDAO;

	public List<User> getAllCustomer(){
		return userDAO.getUserByRole("USER");
	}
}
