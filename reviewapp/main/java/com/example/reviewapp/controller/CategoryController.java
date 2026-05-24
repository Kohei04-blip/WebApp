package com.example.reviewapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.reviewapp.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    

    @GetMapping("/new")
    public String newForm(){
        return "categories/new";
    }

    @PostMapping
    public String create(String name){
        categoryService.create(name);
        return "redirect:/categories/new";
    } 

}
