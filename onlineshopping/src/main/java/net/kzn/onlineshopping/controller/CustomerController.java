package net.kzn.onlineshopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.shoppingbackend.dao.UserDAO;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private UserDAO userDAO;

	private final static Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@RequestMapping("/invoice")
	public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Past Invoice");
		mv.addObject("userClickPastInvoice", true);
		
		return mv;
	}

}
