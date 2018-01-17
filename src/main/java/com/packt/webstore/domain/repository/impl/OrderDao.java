package com.packt.webstore.domain.repository.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Order;
import com.packt.webstore.domain.repository.OrderRepository;

@Repository
public class OrderDao extends AbstractDaoImpl implements OrderRepository {

	@Override
	@Transactional
	public Integer saveOrder(Order order) {
		getEntityManager().persist(order);
		getEntityManager().flush();
		return order.getOrderId();
	}

}
