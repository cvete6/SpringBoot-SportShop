package com.example.demo.service;

import com.example.demo.models.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();
    Category getCategoryById(Long categoryId);

}
