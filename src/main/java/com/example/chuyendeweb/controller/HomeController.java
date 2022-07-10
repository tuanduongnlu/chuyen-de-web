package com.example.chuyendeweb.controller;

import com.example.chuyendeweb.DTO.rentPost.ListRentPost;
import com.example.chuyendeweb.DTO.rentPost.RentPostReadDTO;
import com.example.chuyendeweb.DTO.rentPost.RentPostWriteDTO;
import com.example.chuyendeweb.DTO.user.UserDTO;
import com.example.chuyendeweb.entities.*;
import com.example.chuyendeweb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
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
    UserService userService;
    @Autowired
    WardService wardService;
    @Autowired
    DistricService districService;
    @Autowired
    RoomTypeService roomTypeService;
    @Autowired
    RentPostService rentPostService;
    @Value("${numberInPage}")
    int numberInPage;

    @GetMapping(value = "/roomtypes", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RoomType> roomTypes() {
        return roomTypeService.roomTypes();
    }

    @GetMapping("/wards/{id}")
    @ResponseBody
    public List<Ward> wards(@PathVariable int id) {
        return wardService.getWardsByDictric_id(id);
    }

    @GetMapping("/districs")
    @ResponseBody
    public List<Distric> districs() {
        return districService.districs();
    }

    @GetMapping(value = {"/", "home", ""})
    public String home(Model model) {
        List<RentPostReadDTO> list = rentPostService.sortByTimePostDesc(PageRequest.of(0, numberInPage));
        int totalPage = 0;
        if (rentPostService.count() % numberInPage != 0)
            totalPage = rentPostService.count() / numberInPage + 1;
        else totalPage = rentPostService.count() / numberInPage;
        ListRentPost listRentPost = new ListRentPost(list, totalPage, 0);
        model.addAttribute("listRoom", listRentPost);
        return "trangchu";
    }

    @GetMapping("/login")
    public String getPageLogin(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "tài khoảng hoặc mật khẩu không đúng");
        return "login";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String phone, @RequestParam String email, @RequestParam String password, HttpServletRequest request) {
        UserDTO userDTO = new UserDTO(name, phone, email, password);
        User user = userDTO.transUser();
        userService.saveOrUpdate(user);
        try {
            request.login(phone, password);
        } catch (ServletException e) {
        }
        return "redirect:home";
    }

    @PostMapping("posts/{page}/{filter}/{type}")
    @ResponseBody
    public List<RentPostReadDTO> redictPage(@PathVariable("page") int page, @PathVariable("filter") int fitter,
                                            @PathVariable("type") int type) {
        List<RentPostReadDTO> list = new ArrayList<>();
        if (type == 0) {
            switch (fitter) {
                case 1:
                    list = rentPostService.sortByPriceAsc(PageRequest.of(page - 1, numberInPage));
                    break;
                case 2:
                    list = rentPostService.sortByPriceDesc(PageRequest.of(page - 1, numberInPage));
                    break;
                case 3:
                    list = rentPostService.searchByStatus("còn", PageRequest.of(page - 1, numberInPage));
                    break;
                case 0:
                    list = rentPostService.sortByTimePostDesc(PageRequest.of(page - 1, numberInPage));
                    break;
            }
        } else {
            switch (fitter) {
                case 1:
                    list = rentPostService.listWithTypeRoomSortByPriceAsc(type,PageRequest.of(page - 1, numberInPage));
                    break;
                case 2:
                    list = rentPostService.listWithTypeRoomSortByPriceDesc(type,PageRequest.of(page - 1, numberInPage));
                    break;
                case 3:
                    list = rentPostService.listWithTypeRoomAndStatus(type,"còn", PageRequest.of(page - 1, numberInPage));
                    break;
                case 0:
                    list = rentPostService.searchByTypeRoom(type,PageRequest.of(page - 1, numberInPage));
                    break;
            }
        }
        return list;
    }

    @GetMapping("/management")
    public String management(Model model) {
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        List<RentPostReadDTO> list = rentPostService.findByUser(userService.findByPhone(phone));
        model.addAttribute("list", list);
        return "postManagement";
    }

}
