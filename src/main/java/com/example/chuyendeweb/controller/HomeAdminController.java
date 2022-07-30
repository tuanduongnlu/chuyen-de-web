package com.example.chuyendeweb.controller;

import com.example.chuyendeweb.DTO.user.UserReadDTO;
import com.example.chuyendeweb.entities.FindPost;
import com.example.chuyendeweb.entities.RentPost;
import com.example.chuyendeweb.entities.User;
import com.example.chuyendeweb.service.RentPostService;
import com.example.chuyendeweb.service.RoomTypeService;
import com.example.chuyendeweb.service.SearcherPostService;
import com.example.chuyendeweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class HomeAdminController {

    @Autowired
    UserService userService;
    @Autowired
    RentPostService rentPostService;
    @Autowired
    SearcherPostService searcherPostService;

    @GetMapping("/")
    public String getAll(Model model){
        List<FindPost> findPostList = searcherPostService.getAllFindPost();
        int sumComment=0;
        for (int i = 0; i < findPostList.size(); i++) {
            sumComment = sumComment + findPostList.get(i).getComments().size();
        }
        model.addAttribute("sumUser", userService.findAll().size());
        model.addAttribute("sumPost", rentPostService.rentPosts().size());
        model.addAttribute("sumFindPost", findPostList.size());
        model.addAttribute("sumComment", sumComment);


        return "admin/homeAdmin";
    }
    @GetMapping("/users")
    public String getAllUser(Model model){
        List<User> list = userService.findAll();
        model.addAttribute("listUser", list);
        return "admin/userManager";

    }

    @GetMapping("/users/state/{id}")
    public String updateState(@PathVariable("id")int id){
        User user = userService.getUserById(id);
         if (user.isState()){
             user.setState(false);
             userService.save(user);
             return "redirect:/admin/users";
         }
         user.setState(true);
         userService.save(user);
         return "redirect:/admin/users";
    }

    @GetMapping("/posts")
    public String getAllPost(Model model){
        List<RentPost> list = rentPostService.rentPosts();
        model.addAttribute("list", list);
        return "admin/postManager";

    }

    @GetMapping("/findPost")
    public String getAllFindPost(Model model){
        List<FindPost> list = searcherPostService.getAllFindPost();
        model.addAttribute("listPost", list);
        return "admin/searcherPostManager";

    }



}
