package com.packt.webstore.domain.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.domain.repository.CartRepository;

@Repository
public class CartDao extends AbstractDaoImpl implements CartRepository {

	@Override
	@Transactional
	public Cart create(Cart cart) {
		getEntityManager().persist(cart);
		getEntityManager().flush();
		return cart;
	}

	@Override
	public Cart read(String cartId) {
		Query query = getEntityManager().createQuery("select c from Cart c where cartId = :id ");
		query.setParameter("id", cartId);
		List<Cart> resultList = query.getResultList();
		Optional<Cart> findFirst = resultList.stream().findFirst();
		if(!findFirst.isPresent()) {
			return null;
		}
		return findFirst.get();
	}

	@Override
	@Transactional
	public void update(String cartId, Cart cart) {
		getEntityManager().merge(cart);
		getEntityManager().flush();
	}

	@Override
	@Transactional
	public void delete(String cartId) {
		Cart deleteCart = read(cartId);
		getEntityManager().remove(deleteCart);
		getEntityManager().flush();
		
	}

}
