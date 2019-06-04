package net.kzn.shoppingbackend.config;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import net.kzn.shoppingbackend.dao.ProductDAO;

@Configuration
@EnableScheduling
public class ExpiredProductScheduler {

	@Autowired
	private ProductDAO productDAO;	
	
	@Scheduled(fixedRate = 300000)
	public void scheduleFixedRateTask() {
	    
	    productDAO.deactivateExpiredProduct(Calendar.getInstance().getTime());
	    
	}
}
