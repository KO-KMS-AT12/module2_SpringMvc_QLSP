package com.module.service;

import com.module.model.Product;

import java.util.List;

public interface ProductService {
  List<Product> listAllProducts();

  void addProduct(Product product);

  void updateProduct(Product product);

  void deleteProduct(int id);

  Product findProductById(int id);
}

