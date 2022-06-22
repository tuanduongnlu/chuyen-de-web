package com.example.chuyendeweb.controller;


import com.example.chuyendeweb.DTO.rentPost.RentPostReadDTO;
import com.example.chuyendeweb.DTO.rentPost.RentPostWriteDTO;
import com.example.chuyendeweb.entities.Image;
import com.example.chuyendeweb.entities.RentPost;
import com.example.chuyendeweb.entities.location;
import com.example.chuyendeweb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RentPostController {
    @Autowired
    WardService wardService;
    @Autowired
    DistricService districService;
    @Autowired
    RentPostService RentPostService;
    @Autowired
    RoomTypeService roomTypeService;
    @Autowired
    FilesStorageService storageService;
    @Autowired
    UserService userService;

    @GetMapping(value = "/rentPosts")
    @ResponseBody
    public List<RentPost> getAll() {
        return RentPostService.rentPosts();
    }

    @PostMapping(value = "/rentPosts")
    @ResponseBody
    public void create(@RequestBody RentPost rentPost) {
        RentPostService.saveOrUpdate(rentPost);
    }

    @DeleteMapping(value = "/rentPosts/{id}")
    @ResponseBody
    public void delete(@PathVariable("id") int id) {
        RentPostService.delete(id);
    }

    @RequestMapping(value = "/rentPosts/{id}", method = {RequestMethod.GET, RequestMethod.POST}
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public RentPost getRentPost(@PathVariable("id") long id) {
        return RentPostService.searchByUserId(id);
    }

    @GetMapping(value = "/posts/listPricesDesc", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPostReadDTO> listPriceDesc() {
        return RentPostService.sortByPriceDesc();
    }

    @GetMapping(value = "/posts/listPricesAsc", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPostReadDTO> listPriceAsc() {
        return RentPostService.sortByPriceAsc();
    }

    @GetMapping(value = "/posts/listTimePostDesc", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPost> listTimePostDesc() {
        return RentPostService.sortByTimePostDesc();
    }

    @GetMapping(value = "/posts/listTimePostAsc", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPostReadDTO> listTimePostAsc() {
        return RentPostService.sortByTimePostAsc();
    }

    @RequestMapping(value = "/posts/listStatus", method = {RequestMethod.GET, RequestMethod.POST}
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPostReadDTO> listStatus() {
        return RentPostService.searchByStatus("còn");
    }


    @PostMapping(value = "/search")
    public String search(@RequestParam("distric") int distric, @RequestParam("ward") int ward,
                         @RequestParam("startPrice") int startPrice, @RequestParam("endPrice") int endPrice,
                         @RequestParam("startArea") int startArea, @RequestParam("endArea") int endArea, @RequestParam("type") int type,
                         Model model) {
        List<RentPostReadDTO> list = new ArrayList<>();
        if (endArea == 0) endArea = 100000;
        if (endPrice == 0) endPrice = 100000000;
        if (type == 0 && ward == 0) {
            list = RentPostService.searchNotWardAndRoomType(distric, startPrice, endPrice, startArea, endArea);
            model.addAttribute("listRoom", list);
            return "trangchu";
        }
        if (type == 0 && ward != 0) {
            list = RentPostService.searchNotRoomtype(distric, ward, startPrice, endPrice, startArea, endArea);
            model.addAttribute("listRoom", list);
            return "trangchu";
        }
        if (type > 0 && distric == 0) {
            list = RentPostService.searchNotLocation(type, startPrice, endPrice, startArea, endArea);
            model.addAttribute("listRoom", list);
            return "trangchu";
        }
        if (type > 0 && ward == 0 && distric>0) {
            list = RentPostService.searchNotWard(distric, type, startPrice, endPrice, startArea, endArea);
            model.addAttribute("listRoom", list);
            return "trangchu";
        }

        list = RentPostService.search(distric, ward, type, startPrice, endPrice, startArea, endArea);
        model.addAttribute("listRoom", list);
        return "trangchu";
    }

    @GetMapping(value = "/posts/{id}", produces = {"text/css"})
    public String listTypeRoom(@PathVariable("id") int id, Model model) {
        RentPostReadDTO rentPost = RentPostService.getById(id);
        model.addAttribute("room", rentPost);
        return "RoomDetail";
    }

    @GetMapping(value = {"/post/{x}"})
    public String postDetail() {
        return "rentPost";
    }

    @GetMapping(value = "/post")
    public String getPostPage(Model model) {
        RentPostWriteDTO rentPost = new RentPostWriteDTO();
        model.addAttribute("rentPost", rentPost);
        return "rentPost";
    }

    @PostMapping(value = "/post")
    public String post(@RequestParam("roomType") int roomType,@RequestParam("images") MultipartFile[] images,
                       @RequestParam("title") String title,@RequestParam("detail") String detail,
                       @RequestParam("price") int price,@RequestParam("acreage") double acreage,
                       @RequestParam("distric_id") int distric_id,@RequestParam("ward_id") int ward_id,
                       @RequestParam("street") String street,@RequestParam("sex") String sex,Model model) {
        RentPostWriteDTO writeDTO = new RentPostWriteDTO(roomType,images,title,detail,price,acreage,distric_id,ward_id,street,sex);
        RentPost rentPost = RentPostWriteDTO.trantToRentpost(writeDTO);
        rentPost.setRoomType(roomTypeService.getById(writeDTO.getRoomType()));
        location location = new location(0,districService.findById(distric_id),wardService.findById(ward_id),street,rentPost);
        rentPost.setLocation(location);
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        rentPost.setUser(userService.getByName(currentUserName));
        List<Image> imagesRentPost = new ArrayList<>();
        for (MultipartFile file : writeDTO.getImages()) {
            try {
                storageService.save(file);
                Image image = new Image(0, "/images/" + file.getOriginalFilename(), rentPost);
                imagesRentPost.add(image);
            } catch (Exception e) {
            }
        }
        rentPost.setImages(imagesRentPost);
        RentPostService.saveOrUpdate(rentPost);

        return "redirect:home";
    }
}
