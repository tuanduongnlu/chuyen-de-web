package com.example.chuyendeweb.repository;

import com.example.chuyendeweb.entities.FindPost;
import com.example.chuyendeweb.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FindPostRepository extends JpaRepository<FindPost,Integer> {
    FindPost findById(int id);
    List<FindPost> findAllByUser (User user);
}
