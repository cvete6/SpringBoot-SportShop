package com.example.demo.service.impl;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.ManufacturerNotFoundException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.Category;
import com.example.demo.models.Manufacturer;
import com.example.demo.models.Product;
import com.example.demo.repository.PersistentProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersistentProductServiceImpl implements ProductService {

    private PersistentProductRepository repo;

    public PersistentProductServiceImpl(PersistentProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public Product addNewProduct(Product product) {
        repo.save(product);
        return product;
    }

    @Override
    public Product addNewProduct(String name, String description, String imgUrl, Long manufacturerId, Long categoryId) throws ManufacturerNotFoundException, CategoryNotFoundException {
        return null;
    }

    @Override
    public Product addNewProduct(Product product, Long manufacturerId, Long categoryId) throws ManufacturerNotFoundException, CategoryNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return repo.getAll();
    }

    @Override
    public Product getById(Long productId) {
        Optional<Product> p=repo.getById(productId);
        if(p.isPresent())
            return p.get();
        else throw new ProductNotFoundException();
    }

    public List<Product> getByCategory(Category category){
        return repo.getByCategory(category);
    }

    public List<Product> getByCategoryAndManufacturer(Category category, Manufacturer manufacturer){
        return repo.getByCategoryAndManufacturer(category,manufacturer);
    }

    public double calculatePrice(Category category){
        return repo.calculatePrice(category);
    }
}
