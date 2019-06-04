package net.kzn.shoppingbackend.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.shoppingbackend.dao.InvoiceDAO;
import net.kzn.shoppingbackend.dto.OrderDetail;
import net.kzn.shoppingbackend.dto.OrderItem;
import net.kzn.shoppingbackend.dto.User;

@Repository("invoiceDAO")
@Transactional
public class InvoiceDAOImpl implements InvoiceDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("deprecation")
	@Override
	public List<OrderDetail> getUserInvoices(User user) {
		
		String queryString = "from OrderDetail where user.id =:userId";
		try {
			Criteria cr = sessionFactory.getCurrentSession()
					.createCriteria(OrderDetail.class)
				    .setProjection(Projections.projectionList()
				      .add(Projections.property("id"), "id")
				      .add(Projections.property("orderTotal"), "orderTotal")
				      .add(Projections.property("orderCount"), "orderCount")
				      .add(Projections.property("orderDate"), "orderDate"))
				      .add(Restrictions.eq("user",user))
				    .setResultTransformer(Transformers.aliasToBean(OrderDetail.class));

				  List<OrderDetail> list = cr.list();
			
			
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<OrderItem> getInvoiceDetails(int orderDetailId) {
		String query = "FROM OrderItem AS o where o.orderDetail.id =:orderDetailId";
		
		return (List<OrderItem>) sessionFactory.getCurrentSession()
				.createQuery(query)
				.setParameter("orderDetailId", orderDetailId)
				.getResultList();
	}


}
