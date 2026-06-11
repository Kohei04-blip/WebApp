package com.example.reviewapp.dto;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class NodeForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    public Long getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }

    @NotBlank(message = "タイトルを入力してください")
    private String title;

    private String content;

    private LocalDate reviewDate;

    private Integer understandingLevel;

    public LocalDate getReviewDate(){
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate){
        this.reviewDate = reviewDate;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public Integer getUnderstandingLevel(){
        return understandingLevel;
    }

    public void setUnderstandingLevel(Integer understandingLevel){
        this.understandingLevel = understandingLevel;
    }
}