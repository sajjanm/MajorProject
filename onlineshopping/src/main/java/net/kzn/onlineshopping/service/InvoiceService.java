package net.kzn.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import net.kzn.shoppingbackend.dao.InvoiceDAO;
import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.OrderDetail;
import net.kzn.shoppingbackend.dto.User;

@Component
public class InvoiceService {

	@Autowired
	private InvoiceDAO invoiceDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private HttpSession session;
	
	public List<OrderDetail> getCustomerInvoices(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userDAO.getByEmail(authentication.getName());
		
		return invoiceDAO.getUserInvoices(user);
	}
}
