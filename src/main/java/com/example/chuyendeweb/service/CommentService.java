package com.example.chuyendeweb.service;

import com.example.chuyendeweb.entities.Comment;
import com.example.chuyendeweb.entities.User;
import com.example.chuyendeweb.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void saveOrUpdate(Comment comment) {
        commentRepository.save(comment);
    }

    public void delete(int id) {
        commentRepository.deleteById(id);
    }

    public Comment findById(int id) {
        return commentRepository.findById(id);
    }


}
