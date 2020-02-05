package com.example.demo.service.impl;

import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.models.Category;
import com.example.demo.repository.PersistentCategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersistentCategoryServiceImpl implements CategoryService {

    private PersistentCategoryRepository repo;

    public PersistentCategoryServiceImpl(PersistentCategoryRepository repo){
        this.repo=repo;
    }

    @Override
    public List<Category> getAll() {
        return repo.getAll();
    }

    @Override
    public Category getCategoryById(Long categoryId) {

        Optional<Category> c =repo.getById(categoryId);
        if(c.isPresent())
            return c.get();
        else throw new CategoryNotFoundException();
    }

    public void save(Category c){
        repo.save(c);
    }
}
