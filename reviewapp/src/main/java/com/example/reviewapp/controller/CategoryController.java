package com.example.reviewapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.reviewapp.entity.Category;
import com.example.reviewapp.service.CategoryService;

import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping
    public String findAll(Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);
        return "categories/list";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id ,Model model){
        Category category = categoryService.findById(id);
        model.addAttribute("category",category);
        return "categories/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id ,String name){
        categoryService.update(id ,name);
        return "redirect:/categories";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id ,Model model){
        boolean result = categoryService.delete(id);
        if(!result){
            List<Category> categories = categoryService.findAll();
            model.addAttribute("categories",categories);
            model.addAttribute("errorMessage","このカテゴリは使用中のため削除できません");

            return "categories/list";
        }
        
        return "redirect:/categories";
    }

}
