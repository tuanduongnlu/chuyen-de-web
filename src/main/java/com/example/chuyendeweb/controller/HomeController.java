package com.example.chuyendeweb.controller;

import com.example.chuyendeweb.DTO.rentPost.RentPostWriteDTO;
import com.example.chuyendeweb.DTO.user.UserDTO;
import com.example.chuyendeweb.entities.*;
import com.example.chuyendeweb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    RentPostService rentPostService;
    @Autowired
    UserService userService;
    @Autowired
    WardService wardService;
    @Autowired
    DistricService districService;
    @Autowired
    RoomTypeService roomTypeService;
    @Autowired
    FilesStorageService  storageService;


    @GetMapping(value="api/roomtypes",produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RoomType> roomTypes (){
        return roomTypeService.roomTypes();
    }

    @GetMapping("api/wards/{id}")
    @ResponseBody
    public List<Ward> wards(@PathVariable int id)
    {
        return wardService.getWardsByDictric_id(id);
    }

    @GetMapping("api/districs")
    @ResponseBody
    public List<Distric> districs() {
        return districService.districs();
    }

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
        try {
            request.login(phone, password);
        } catch (ServletException e) {
        }
        return "redirect:home";
    }
    @GetMapping(value = "/rentPosts/{id}",produces={"text/css"})
    public String listTypeRoom(@PathVariable("id") int id,Model model) {
        RentPost rentPost = rentPostService.getById(id);
        model.addAttribute("room",rentPost);
        return "RoomDetail";
    }
    @GetMapping(value = {"/post/{x}"} )
    public String postDetail(){
        return "rentPost";
    }

    @GetMapping(value = "/post")
    public String getPostPage(Model model) {
        RentPostWriteDTO rentPost = new RentPostWriteDTO();
        model.addAttribute("rentPost",rentPost);
        return "rentPost";
    }
    @PostMapping(value = "/post")
    public String post(@ModelAttribute RentPostWriteDTO writeDTO ) {
        RentPost rentPost = RentPostWriteDTO.trantToRentpost(writeDTO);
        rentPost.setRoomType(roomTypeService.getById(writeDTO.getRoomType()));
        rentPost.setUser(userService.getByName(writeDTO.getUsername()));
        List<Image> images = new ArrayList<>();
        for(MultipartFile file:writeDTO.getImages()) {
            try {
                storageService.save(file);
                Image image = new Image(0,"/upload/"+file.getOriginalFilename(),rentPost);
                images.add(image);
            } catch (Exception e) {
            }
        }
        rentPost.setImages(images);
        rentPostService.saveOrUpdate(rentPost);
        return "/";
    }

}
