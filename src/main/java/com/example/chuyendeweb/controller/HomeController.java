package com.example.chuyendeweb.controller;

import com.example.chuyendeweb.DTO.user.UserDTO;
import com.example.chuyendeweb.entities.User;
import com.example.chuyendeweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @GetMapping(value = {"/","home",""} )
    public String home(){
        return "trangchu";
    }
    @GetMapping("/login")
    public String getPageLogin (Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "tài khoảng hoặc mật khẩu không đúng");

        return "login";
    }
    @PostMapping("/register")
    public String register(@RequestParam String name,@RequestParam String phone,@RequestParam String email,@RequestParam String password, HttpServletRequest request) {
        UserDTO userDTO= new UserDTO(name,phone,email,password);
        User user = userDTO.transUser();
        userService.saveOrUpdate(user);
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        return "trangchu";
    }
}
