package com.packt.webstore.service;

import com.packt.webstore.domain.Order;
import com.packt.webstore.domain.Product;

public interface OrderService {
	
	void processOrder(Product product, long quantity);
	
	Integer saveOrder(Order order);
}
