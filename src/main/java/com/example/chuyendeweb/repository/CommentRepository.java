package com.example.chuyendeweb.repository;
import com.example.chuyendeweb.entities.Comment;
import com.example.chuyendeweb.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
     Comment findById(int id);
}
