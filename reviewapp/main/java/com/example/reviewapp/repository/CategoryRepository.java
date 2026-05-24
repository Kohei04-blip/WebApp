package com.example.reviewapp.repository;
import com.example.reviewapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{  
} 
