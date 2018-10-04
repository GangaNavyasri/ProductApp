package com.capgemini.productapp.service;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exceptions.ProductNotFoundException;

public interface ProductService {

	
	public Product addProduct(Product product);

	public Product updateProduct(Product product);

	public Product findProductById(int productId) throws ProductNotFoundException;

	public void deleteProduct(int productId);

}
