package com.packt.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.CartItem;
import com.packt.webstore.domain.Order;
import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.OrderRepository;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.CartService;
import com.packt.webstore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	
	public void processOrder(Product product, long quantity) {
		
		if(product.getUnitsInStock() < quantity){
			throw new IllegalArgumentException("Out of Stock. Available Units in stock"+ product.getUnitsInStock());
		}
		
		product.setUnitsInStock(product.getUnitsInStock() - quantity);
		productRepository.updateProduct(product);
	}
	
	public Integer saveOrder(Order order) {
		Integer orderId = orderRepository.saveOrder(order);
		for (CartItem cartItem : order.getCart().getCartItems()) {
			processOrder(cartItem.getProduct(), cartItem.getQuantity());
		}
		return orderId;
	}

}
