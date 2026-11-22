package com.example.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecommerce.models.Product;
import com.example.ecommerce.repository.ProductRepository;

@Service 
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

}
