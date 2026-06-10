package com.example.reviewapp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.reviewapp.entity.Node;
import com.example.reviewapp.entity.Category;
import com.example.reviewapp.repository.CategoryRepository;
import com.example.reviewapp.repository.NodeRepository; 

@Service
public class NodeService {

    private final NodeRepository nodeRepository;
    private final CategoryRepository categoryRepository;

    public NodeService(NodeRepository nodeRepository ,CategoryRepository categoryRepository) {
        this.nodeRepository = nodeRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Node> findAll(String keyword, Long categoryId) {

        boolean hasKeyword = keyword != null && !keyword.isBlank();
        boolean hasCategory = categoryId != null;

        if (hasKeyword && hasCategory) {
            return nodeRepository.findByTitleContainingOrContentContainingAndCategory_Id(
                keyword,
                keyword,
                categoryId
            );
        } 
        
        if (hasKeyword) {
            return nodeRepository.findByTitleContainingOrContentContaining(
                keyword,
                keyword
            );
        }

        if (hasCategory) {
            return nodeRepository.findByCategory_Id(categoryId);
        }

        return nodeRepository.findAll();

    }

    public void create(String title, String content ,Long categoryId ,LocalDate reviewDate) {
        Node node = new Node();
        node.setTitle(title);
        node.setContent(content);
        node.setCreatedAt(LocalDateTime.now());
        node.setUpdatedAt(LocalDateTime.now());
        node.setReviewDate(reviewDate);
        
        Category category = categoryRepository.findById(categoryId).orElse(null);
        node.setCategory(category);
        
        nodeRepository.save(node);
    }

    public Node findById(Long id) {
        return nodeRepository.findById(id).orElse(null);
    }

    public void update(Long id, String title, String content, Long categoryId ,LocalDate reviewDate){
        Node node = nodeRepository.findById(id).orElse(null);
        node.setTitle(title);
        node.setContent(content);
        node.setUpdatedAt(LocalDateTime.now());
        node.setReviewDate(reviewDate);

        Category category = categoryRepository.findById(categoryId).orElse(null);
        node.setCategory(category);
        
        nodeRepository.save(node);
    }

    public void delete(Long id) {
        nodeRepository.deleteById(id);
    }

    public List<Node> findReviewNodes(){
        return nodeRepository.findByReviewDateLessThanEqual(LocalDate.now());
    }

    public void reviewed(Long id){
        Node node = nodeRepository.findById(id).orElse(null);
        node.setReviewDate(LocalDate.now().plusDays(7));
        nodeRepository.save(node);
    }

}