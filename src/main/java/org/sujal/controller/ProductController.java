package org.sujal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sujal.dto.DisplayProductResponse;
import org.sujal.dto.ProductRequest;
import org.sujal.dto.ProductResponse;
import org.sujal.dto.UpdateProductResponse;
import org.sujal.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
//	Save product
	@PostMapping(path = "/product", produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
	public ProductResponse addProduct(@RequestBody ProductRequest request) {
		return productService.addProduct(request);
	}
	
	@PutMapping(path = "/product/{productName}", produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
	public UpdateProductResponse updateProduct(@PathVariable String productName, @RequestBody ProductRequest request) {
	    return productService.updateProduct(productName, request);
	}

	
	@DeleteMapping(path = "/product/{productName}", produces = {"application/json", "application/xml"})
	public ProductResponse deleteProduct(@PathVariable String productName) {
		return productService.deleteProduct(productName);
	}
	
//	Search product by name
	@GetMapping(path = "/product/{productName}", produces = {"application/json", "application/xml"})
	public ProductResponse getProduct(@PathVariable String productName) {
		return productService.getByProductName(productName);
	}
	
//	Display product
	@GetMapping(path = "/product", produces = {"application/json", "application/xml"})
	public DisplayProductResponse displayProduct() {
		return productService.displayProduct();
	}
}