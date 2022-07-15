package com.example.chuyendeweb.controller;

import com.example.chuyendeweb.DTO.findPost.FindPostWriteDTO;
import com.example.chuyendeweb.DTO.rentPost.RentPostWriteDTO;
import com.example.chuyendeweb.entities.FindPost;
import com.example.chuyendeweb.service.RoomTypeService;
import com.example.chuyendeweb.service.SearcherPostService;
import com.example.chuyendeweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearcherPostController {
    @Autowired
    SearcherPostService searcherPostService;

    @Autowired
    RoomTypeService roomTypeService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/findRoom")
    public String getPostPage(Model model) {
//        RentPostWriteDTO rentPost = new RentPostWriteDTO();
//        model.addAttribute("rentPost", rentPost);
        return "find-room";

    }

    @PostMapping("/postFindRoom")
    public String saveFindPost(@RequestParam("roomType") int roomType, @RequestParam("title") String title,
                               @RequestParam("detail") String detail) {
        FindPostWriteDTO findPostWriteDTO = new FindPostWriteDTO(roomType, title, detail);
        FindPost findPost = FindPostWriteDTO.trantToPiFindPost(findPostWriteDTO);
        findPost.setRoomType(roomTypeService.getById(findPostWriteDTO.getRoomType()));


        final String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        findPost.setUser(userService.findByPhone(phone));
        searcherPostService.saveOrUpdate(findPost);
        return "redirect:/home";
    }


}
