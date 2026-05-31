package com.example.reviewapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.reviewapp.repository.NodeRepository;
import com.example.reviewapp.entity.Category;
import com.example.reviewapp.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final NodeRepository nodeRepository;

    public CategoryService(CategoryRepository categoryRepository ,NodeRepository nodeRepository){
        this.categoryRepository = categoryRepository;
        this.nodeRepository = nodeRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public void create(String name){
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
    }

    public Category findById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    public void update(Long id, String name){
        Category category =categoryRepository.findById(id).orElse(null);
        category.setName(name);
        categoryRepository.save(category);
    }

    public boolean delete(Long id){
        long count = nodeRepository.countByCategory_Id(id);
        if (count > 0){
            return false;
        }
        categoryRepository.deleteById(id);
        return true;
    }
}
