package com.packt.webstore.domain.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.packt.webstore.domain.repository.AbstractDao;

public class AbstractDaoImpl implements AbstractDao {
	
	@PersistenceContext(unitName = "webstorePU")
	private EntityManager entityManager;
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
