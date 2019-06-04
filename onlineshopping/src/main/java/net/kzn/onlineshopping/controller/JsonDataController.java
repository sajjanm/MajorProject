package net.kzn.onlineshopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.kzn.onlineshopping.service.InvoiceService;
import net.kzn.onlineshopping.service.ProductService;
import net.kzn.onlineshopping.service.UserService;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.OrderDetail;
import net.kzn.shoppingbackend.dto.Product;
import net.kzn.shoppingbackend.dto.User;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private InvoiceService invoiceService;

	@RequestMapping("/admin/all/products")
	@ResponseBody
	public List<Product> getAllProductsList() {		
		return productDAO.list();				
	}
	
	@RequestMapping("/admin/all/expireProducts")
	@ResponseBody
	public List<Product> getAllProductsExipringSoonList() {	
		return productService.getProductByExpireAndQty();
				
	}
	
	
	@RequestMapping("/all/products")
	@ResponseBody
	public List<Product> getAllProducts() {
		
		return productDAO.listActiveProducts();
				
	}
	
	@RequestMapping("/all/customers")
	@ResponseBody
	public List<User> getAllCustomers() {
		
		return userService.getAllCustomer();
				
	}
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getProductsByCategory(@PathVariable int id) {
		
		return productDAO.listActiveProductsByCategory(id);
				
	}
	
	@RequestMapping("/invoices")
	@ResponseBody
	public List<OrderDetail> getCustomerInvoices(){
		return invoiceService.getCustomerInvoices();
	}
	
	
	@RequestMapping("/mv/products")
	@ResponseBody
	public List<Product> getMostViewedProducts() {		
		return productDAO.getProductsByParam("views", 5);				
	}
		
	@RequestMapping("/mp/products")
	@ResponseBody
	public List<Product> getMostPurchasedProducts() {		
		return productDAO.getProductsByParam("purchases", 5);				
	}
	
	
	
	
}
