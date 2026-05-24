package com.example.reviewapp.repository;

import com.example.reviewapp.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NodeRepository extends JpaRepository<Node, Long> {
    
    List<Node> findByTitleContainingOrContentContaining(
        String title,
        String content
    );

    List<Node> findByCategory_Id(Long categoryId);

    List<Node> findByTitleContainingOrContentContainingAndCategory_Id(
        String title,
        String content,
        Long categoryId
    );

}
