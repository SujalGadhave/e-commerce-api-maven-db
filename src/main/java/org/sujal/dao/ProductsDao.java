package org.sujal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sujal.entity.Product;

public interface ProductsDao extends JpaRepository<Product, Long> {
	
	Product findByProductName(String productName);
	
	int deleteByProductName(String productName);
	
}