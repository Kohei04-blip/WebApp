package com.example.reviewapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.reviewapp.entity.Category;
import com.example.reviewapp.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public void create(String name){
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
    }
}
