package com.example.chuyendeweb.controller;

import com.example.chuyendeweb.DTO.findPost.FindPostReađTO;
import com.example.chuyendeweb.DTO.rentPost.RentPostWriteDTO;
import com.example.chuyendeweb.service.SearcherPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SearcherPostController {
    @Autowired
    SearcherPostService searcherPostSẻvice;



    @PostMapping("/findPost")
    public  String saveFindPost(@RequestBody FindPostReađTO findPostReađTO){
        searcherPostSẻvice.saveOrUpdate(findPostReađTO);
        return "postFindRoom";
    }

    @GetMapping(value = "/findPost")
    public String getPostPage(Model model) {
        RentPostWriteDTO rentPost = new RentPostWriteDTO();
        model.addAttribute("rentPost", rentPost);
        return "postFindRoom";
    }



}
