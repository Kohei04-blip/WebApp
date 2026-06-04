package com.example.reviewapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "nodes")
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    private LocalDate learnedAt;
    private LocalDate reviewDate;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private Category category;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getLearnedAt() {
        return learnedAt;
    }

    public LocalDate getReviewDate(){
        return reviewDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }

    public Category getCategory(){
        return category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setReviewDate(LocalDate reviewDate){
        this.reviewDate = reviewDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategory(Category category){
        this.category = category;
    }

}