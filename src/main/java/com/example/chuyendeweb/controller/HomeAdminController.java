package com.example.chuyendeweb.controller;

import com.example.chuyendeweb.entities.User;
import com.example.chuyendeweb.service.RentPostService;
import com.example.chuyendeweb.service.RoomTypeService;
import com.example.chuyendeweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/user")
    public String getAllUser(Model model){
        List<User> list = userService.findAll();
        model.addAttribute("listUser", list);
        return "admin/userManager";

    }

}
