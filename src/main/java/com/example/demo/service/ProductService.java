package com.example.demo.service;

import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.ManufacturerNotFoundException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.Product;

import java.util.List;

public interface ProductService {

    Product addNewProduct(Product product, Long manufacturerId, Long categoryId) throws ManufacturerNotFoundException,
            CategoryNotFoundException;
    Product addNewProduct(String name, String description, String imgUrl, Long manufacturerId, Long categoryId) throws
            ManufacturerNotFoundException, CategoryNotFoundException;
    Product addNewProduct (Product product);
    List<Product> getAllProducts();
    Product getById(Long productId);
}
