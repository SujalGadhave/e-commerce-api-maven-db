package org.sujal.dto;

import java.util.List;

import org.sujal.entity.Product;

public class DisplayProductResponse {
	
	private String responseCode;
	private String responseMessage;
	
	private List<Product> products;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
