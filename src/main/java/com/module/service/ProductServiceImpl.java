package com.module.service;

import com.module.model.Product;
import com.module.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  public void setProductRepository(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> listAllProducts() {
    return productRepository.listAllProducts();
  }

  @Override
  public void addProduct(Product product) {
    productRepository.addProduct(product);
  }

  @Override
  public void updateProduct(Product product) {
    productRepository.updateProduct(product);
  }

  @Override
  public void deleteProduct(int id) {
    productRepository.deleteProduct(id);
  }

  @Override
  public Product findProductById(int id) {
    return productRepository.findProductById(id);
  }
}
