package org.sujal.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sujal.common.Dbutils;
import org.sujal.dto.Product;
import org.sujal.dto.ProductRequest;
import org.sujal.dto.ProductResponse;

@Component
public class ProductService {

    @Autowired
    ProductResponse response;

    @Autowired
    Product products;

    // Save product
    public ProductResponse addProduct(ProductRequest request) {
        try {
            Dbutils.executeQuery(
                "INSERT INTO products (productId, productName, productQuantity, productPrice) " +
                "VALUES ('" + request.getProductId() + "', '" + request.getProductName() + "', '" +
                request.getProductQuantity() + "', '" + request.getProductPrice() + "');"
            );
            
            products.setProductId(request.getProductId());
            products.setProductName(request.getProductName());
            products.setProductQuantity(request.getProductQuantity());
            products.setProductPrice(request.getProductPrice());

            response.setResponseCode("000");
            response.setResponseMessage("Product added successfully");
            
            List<Product> productList = new ArrayList<>();
            productList.add(products);
            response.setProducts(productList);
            
        } catch (SQLException e) {
            response.setResponseCode("911");
            response.setResponseMessage("Product not added...");
            e.printStackTrace();
        }
        return response;
    }

    // Search product by name
    public ProductResponse getProduct(ProductRequest request) throws SQLException {
        List<Product> productList = new ArrayList<>();

        try {
            ResultSet rs = Dbutils.executeSelectQuery(
                "SELECT * FROM products WHERE productName = '" + request.getProductName() + "'"
            );

            while (rs.next()) {
            	
               Product products = new Product();
               
               products.setProductId(rs.getString(1));
               products.setProductName(rs.getString(2));
               products.setProductQuantity(rs.getString(3));
               products.setProductPrice(rs.getString(4));
               
               productList.add(products);
            }
            
            response.setResponseCode("0000");
            response.setResponseMessage("Product found Successfully!");
            response.setProducts(productList);

        } catch (Exception e) {
            response.setResponseCode("911");
            response.setResponseMessage("Product not found");
            e.printStackTrace();
        }

        return response;
    }

    // Display all products
    public ProductResponse displayProduct() throws SQLException {
        List<Product> displayProductList = new ArrayList<>();

        try {
            ResultSet rs = Dbutils.executeSelectQuery("SELECT * FROM products");

            while (rs.next()) {
            	
            	Product product = new Product();
            	
                product.setProductId(rs.getString(1));
                product.setProductName(rs.getString(2));
                product.setProductQuantity(rs.getString(3));
                product.setProductPrice(rs.getString(4));
                
                displayProductList.add(product);
            }

            response.setResponseCode("0000");
            response.setResponseMessage("Products fetched successfully");
            response.setProducts(displayProductList);

        } catch (Exception e) {
            response.setResponseCode("0001");
            response.setResponseMessage("Failed to fetch products");
            e.printStackTrace();
        }

        return response;
    }
}