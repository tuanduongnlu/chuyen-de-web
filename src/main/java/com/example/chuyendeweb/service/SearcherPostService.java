package com.example.chuyendeweb.service;


import com.example.chuyendeweb.DTO.findPost.FindPostReađTO;
import com.example.chuyendeweb.entities.FindPost;
import com.example.chuyendeweb.repository.FindPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearcherPostService {

    private final FindPostRepository findPostRepository;


    public FindPost saveOrUpdate(FindPostReađTO findPostReađTO){
        FindPost findPost = FindPostReađTO.trantToFindPost(findPostReađTO);
        return findPostRepository.save(findPost);

    }


}
