package com.packt.webstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CART")
public class Cart implements Serializable{

	private static final long serialVersionUID = -4045729241960416615L;
	@Id
	@Column(name = "CART_ID")
	private String cartId;
	@OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CartItem> cartItems;
	@Column(name = "GRAND_TOTAL")
	private BigDecimal grandTotal;
	
	public Cart() {
		cartItems = new HashSet<CartItem>();
		grandTotal = new BigDecimal(0);
	}
	
	public Cart(String cartId) {
		this();
		this.cartId = cartId;
	}
	
	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}
	
	public void addCartItem(CartItem item) {
		item.setCart(this);
		if(cartItems.contains(item)) {
			CartItem existingCartItem = cartItems.stream().filter(i -> i.equals(item)).findFirst().get();
			existingCartItem.setQuantity(existingCartItem.getQuantity()+ item.getQuantity());
		} else {
			cartItems.add(item);
		}
		updateGrandTotal();
	}
	
	public void removeCartItem(CartItem item) {
		cartItems.remove(item);
		updateGrandTotal();
	}
	
	public void updateGrandTotal() {
		grandTotal= new BigDecimal(0);
		for(CartItem item : cartItems){
			grandTotal = grandTotal.add(item.getTotalPrice());
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 71;
		int result = 1;
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
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
		Cart other = (Cart) obj;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		return true;
	}
}
