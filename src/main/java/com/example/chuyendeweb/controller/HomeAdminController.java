package com.example.chuyendeweb.controller;

import com.example.chuyendeweb.DTO.user.UserReadDTO;
import com.example.chuyendeweb.entities.User;
import com.example.chuyendeweb.service.RentPostService;
import com.example.chuyendeweb.service.RoomTypeService;
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
    RoomTypeService roomTypeService;

    @GetMapping("/")
    public String getAll(Model model){
        return "admin/homeAdmin";
    }
    @GetMapping("/users")
    public String getAllUser(Model model){
        List<User> list = userService.findAll();
        model.addAttribute("listUser", list);
        return "admin/userManager";

    }

    @PostMapping("/users/state/{id}")
    public String updateState(@PathVariable("id")int id){
        final String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByPhone(phone);
         if (user.isState()){
             user.setState(false);
             userService.save(user);
             return "admin/userManager";
         }
         user.setState(true);
        userService.save(user);
         return "admin/userManager";
    }



}
