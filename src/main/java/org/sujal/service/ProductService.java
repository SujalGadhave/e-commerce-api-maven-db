package org.sujal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sujal.dto.ProductRequest;
import org.sujal.dto.ProductResponse;

@Component
public class ProductService {

    @Autowired
    ProductResponse response;


    // Save product
    public ProductResponse addProduct(ProductRequest request) {
       return null;
    }

    // Search product by name
    public ProductResponse getProduct(ProductRequest request) {
       return null;
    }

    // Display all products
    public ProductResponse displayProduct() {
    	return null;
    }
}