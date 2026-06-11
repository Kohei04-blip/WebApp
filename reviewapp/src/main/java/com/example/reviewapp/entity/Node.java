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

    private Integer understandingLevel;

    @ManyToOne
    private Category category;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

     public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getLearnedAt() {
        return learnedAt;
    }

    public LocalDate getReviewDate(){
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate){
        this.reviewDate = reviewDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Category getCategory(){
        return category;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public Integer getUnderstandingLevel(){
        return understandingLevel;
    }

    public void setUnderstandingLevel(Integer understandingLevel){
        this.understandingLevel = understandingLevel;
    }

}