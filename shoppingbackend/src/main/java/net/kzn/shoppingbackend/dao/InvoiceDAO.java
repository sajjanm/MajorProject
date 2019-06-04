package net.kzn.shoppingbackend.dao;

import java.util.List;

import net.kzn.shoppingbackend.dto.OrderDetail;
import net.kzn.shoppingbackend.dto.OrderItem;
import net.kzn.shoppingbackend.dto.User;

public interface InvoiceDAO {

	public List<OrderDetail> getUserInvoices(User user);
	public List<OrderItem> getInvoiceDetails(int invoiceId);

}
