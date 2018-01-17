package com.packt.webstore.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.packt.webstore.domain.Order;
import com.packt.webstore.domain.repository.OrderRepository;

public class InMemoryOrderRepositoryImpl implements OrderRepository{

	private Map<Integer, Order> listOfOrders;
	private Integer nextOrderId;
	
	public InMemoryOrderRepositoryImpl() {
		listOfOrders = new HashMap<Integer, Order>();
		nextOrderId = 1000;
	}

	public Integer saveOrder(Order order) {
		order.setOrderId(getNextOrderId());
		listOfOrders.put(order.getOrderId(), order);
		return order.getOrderId();
	}

	private synchronized Integer getNextOrderId() {
		return nextOrderId++;
	}
}
