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
    public String getPageLogin (Model model) {
        model.addAttribute("UserDTO",new UserDTO());
        return "login";
    }

    @PostMapping("/login")
    public String login (@RequestParam(name = "phone") String phone , @RequestParam(name = "password") String password, Model model , HttpServletRequest request) {
        User user = userService.checkLogin(phone,password) ;
        if(user==null) {
            model.addAttribute("error", " tài khoảng không chính xác ");
            model.addAttribute("phone", phone);
            return "login";
        }
         if(user.getState()==0){
            model.addAttribute("error", " tài khoảng đã bị khóa");
             model.addAttribute("phone", phone);
             return "login";
        }
        HttpSession session = request.getSession();
         session.setAttribute("user",user);
        return "trangchu" ;

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
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.invalidate();
        return "trangchu";
    }
}
