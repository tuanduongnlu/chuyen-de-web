package com.example.chuyendeweb.service;


import com.example.chuyendeweb.DTO.findPost.FindPostReadDTO;
import com.example.chuyendeweb.entities.FindPost;
import com.example.chuyendeweb.entities.User;
import com.example.chuyendeweb.repository.FindPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearcherPostService {

    private final FindPostRepository findPostRepository;

    public void deleteById(int id) {
        findPostRepository.deleteById(id);
    }

    public List<FindPost> getAllFindPost() {
        return findPostRepository.findAll();
    }

    public FindPost findById(int id){
        return findPostRepository.findById(id);
    }

    public void saveOrUpdate(FindPost findPost) {
        findPostRepository.save(findPost);
    }

    public List<FindPostReadDTO> findByUser(User user) {
        List<FindPostReadDTO> findPostReadDTOS = new ArrayList<>();
        for (FindPost e : findPostRepository.findAllByUser(user)) {
            findPostReadDTOS.add(FindPostReadDTO.trantoDTO(e));
        }
        return findPostReadDTOS;
    }


}
