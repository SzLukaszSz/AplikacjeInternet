package com.packt.webstore.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_CONFIRMATION")
public class Order  implements Serializable{

	private static final long serialVersionUID = -3560539622417210365L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ORDER_ID")
	private Integer orderId;
	@OneToOne
	@JoinColumn(name = "CART_ID")
	private Cart cart;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SHIPPING_DETAIL_ID")
	private ShippingDetail shippingDetail;
	
	public Order() {
		this.customer = new Customer();
		this.shippingDetail = new ShippingDetail();
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ShippingDetail getShippingDetail() {
		return shippingDetail;
	}

	public void setShippingDetail(ShippingDetail shippingDetail) {
		this.shippingDetail = shippingDetail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 829;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}


}
