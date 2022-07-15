package com.example.chuyendeweb.service;


import com.example.chuyendeweb.DTO.findPost.FindPostReadDTO;
import com.example.chuyendeweb.entities.FindPost;
import com.example.chuyendeweb.repository.FindPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearcherPostService {

    private final FindPostRepository findPostRepository;


    public void saveOrUpdate(FindPost findPost){
        findPostRepository.save(findPost);
    }


}
