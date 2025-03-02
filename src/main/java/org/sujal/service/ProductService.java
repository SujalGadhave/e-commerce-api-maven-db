package org.sujal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sujal.dao.ProductsDao;
import org.sujal.dto.DisplayProductResponse;
import org.sujal.dto.ProductRequest;
import org.sujal.dto.ProductResponse;
import org.sujal.dto.UpdateProductResponse;
import org.sujal.entity.Product;

import jakarta.transaction.Transactional;

@Component
public class ProductService {

    @Autowired
    private ProductResponse response;
    
    @Autowired
    private ProductsDao productsDao;
    
    @Autowired
    private Product productTable;

    // Save product
    public ProductResponse addProduct(ProductRequest request) {
       
    	productTable.setId(request.getProductId());
    	productTable.setProductName(request.getProductName());
    	productTable.setProductPrice(request.getProductPrice());
    	productTable.setProductQuantity(request.getProductQuantity());
    	
    	productTable = productsDao.save(productTable);
    	
    	if(productTable != null) {
    		
    		response.setResponseCode("0000");
    		response.setResponseMessage("Product added successfully");
    
    		return response;
    		
    	} else {
    		response.setResponseCode("0911");
    		response.setResponseMessage("Something went wrong");
    		return response;
    	}
    }
    
    public UpdateProductResponse updateProduct(String productName, ProductRequest request) {
        UpdateProductResponse response = new UpdateProductResponse();

        Product product = productsDao.findByProductName(productName);

        if (product == null) {
            response.setResponseCode("0911");
            response.setResponseMessage("Product not found!");
            return response;
        }

        // Update product fields
        product.setProductPrice(request.getProductPrice());
        product.setProductQuantity(request.getProductQuantity());

        // Save the updated product
        product = productsDao.save(product);

        // Set response values
        response.setProductId(product.getId());
        response.setProductName(product.getProductName());
        response.setProductPrice(product.getProductPrice());
        response.setProductQuantity(product.getProductQuantity());

        response.setResponseCode("0000");
        response.setResponseMessage("Product updated successfully!");

        return response;
    }


    	
    // Search product by name
    public ProductResponse getByProductName(String productName) {
        Product products = productsDao.findByProductName(productName);
        ProductResponse response = new ProductResponse();

        if (products != null) {
            response.setResponseCode("0000");
            response.setResponseMessage("Product found successfully");

            // Add product details to response
            response.setProductId(products.getId());
            response.setProductName(products.getProductName());
            response.setProductPrice(products.getProductPrice());
            response.setProductQuantity(products.getProductQuantity());
        } else {
            response.setResponseCode("0911");
            response.setResponseMessage("Product not found");
        }
        return response;
    }


    
    // Display all products
    public DisplayProductResponse displayProduct() {
    	
    	DisplayProductResponse response = new DisplayProductResponse();
    	
    	response.setResponseCode("0000");
		response.setResponseMessage("Product updated successfully");
		response.setProducts(productsDao.findAll());
		return response;
    }
    
    @Transactional
    public ProductResponse deleteProduct(String productName) {
    	
    	int deleteProduct = productsDao.deleteByProductName(productName);
    	
    	if(deleteProduct > 0) {
    		response.setResponseCode("0000");
    		response.setResponseMessage("Product deleted");
    		return response;
    	} else {
    		response.setResponseCode("0911");
    		response.setResponseMessage("Something went wrong");
    		return response;
    	}
    }
}