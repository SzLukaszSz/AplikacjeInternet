package com.packt.webstore.domain.repository.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;

@Repository
public class ProductDao extends AbstractDaoImpl implements ProductRepository {

	@Override
	public List<Product> getAllProducts() {
		Query query = getEntityManager().createQuery("select p from Product p");
		return query.getResultList();
	}

	@Override
	public Product getProductById(String productID) {
		Query query = getEntityManager().createQuery("select p from Product p where productId = :id ");
		query.setParameter("id", productID);
		List<Product> resultList = query.getResultList();
		Optional<Product> findFirst = resultList.stream().findFirst();
		if(!findFirst.isPresent()) {
			return null;
		}
		return findFirst.get();
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		Query query = getEntityManager().createQuery("select p from Product p where category = :category ");
		query.setParameter("category", category);
		return query.getResultList();
	}

	@Override
	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		StringBuilder queryStd = new StringBuilder("select p from Product p where 1=1 ");
		Map <String, Object> params = new HashMap<String,Object>();
		Set<String> criterias = filterParams.keySet();
		
		if(criterias.contains("brand")) {
			List<String> brands = filterParams.get("brand");
			queryStd.append("and manufacturer in ( :brands )");
			params.put("brands", brands);
		}
		
		if(criterias.contains("category")) {
			List<String> categories = filterParams.get("category");
			queryStd.append("and category in ( :categories )");
			params.put("categories", categories);
		}
		
		Query query = getEntityManager().createQuery(queryStd.toString());
		for (Entry<String, Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
		Set<Product> result = new HashSet<Product>(query.getResultList());
		return result;
	}

	@Override
	@Transactional
	public void addProduct(Product product) {
		getEntityManager().persist(product);
		getEntityManager().flush();
	}
	
	@Override
	@Transactional
	public void updateProduct(Product product) {
		getEntityManager().merge(product);
		getEntityManager().flush();
	}
	

}
