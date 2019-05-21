package net.kzn.onlineshopping.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Product;

@Component
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	public List<Product> getProductByExpireAndQty(){
		Calendar calendar = Calendar.getInstance();
		Date thisDate = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 14);
		Date futureDate = calendar.getTime();
		
		return productDAO.getNearExpireAndRunnigLowProducts(thisDate, futureDate);
		
	}

}
