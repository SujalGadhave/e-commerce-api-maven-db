package org.sujal.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sujal.dto.ProductRequest;
import org.sujal.dto.ProductResponse;
import org.sujal.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
//	Save product
	@PostMapping(path = "/product", produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
	public ProductResponse addProduct(@RequestBody ProductRequest product) {
		return productService.addProduct(product);
	}
	
//	Search product by name
	@GetMapping(path = "/product/{productName}", produces = {"application/json", "application/xml"})
	public ProductResponse getProduct(@PathVariable String productName) throws SQLException{
		ProductRequest request = new ProductRequest();
		request.setProductName(productName);
		return productService.getProduct(request);
	}
	
//	Display product
	@GetMapping(path = "/product", produces = {"application/json", "application/xml"})
	public ProductResponse displayProduct() throws SQLException{
		return productService.displayProduct();
	}
}