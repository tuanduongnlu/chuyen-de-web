package com.example.chuyendeweb.controller;


import com.example.chuyendeweb.DTO.rentPost.ListRentPost;
import com.example.chuyendeweb.DTO.rentPost.RentPostReadDTO;
import com.example.chuyendeweb.DTO.rentPost.RentPostWriteDTO;
import com.example.chuyendeweb.entities.Image;
import com.example.chuyendeweb.entities.RentPost;
import com.example.chuyendeweb.entities.Location;
import com.example.chuyendeweb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @Value("${numberInPage}")
            int numberInPage ;
    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        RentPostService.delete(id);
        return new ResponseEntity(id, HttpStatus.OK) ;
    }
    @GetMapping (value = "/status/{id}")
    @ResponseBody
    public ResponseEntity<String> updateStatus(@PathVariable("id") int id) {
        RentPostReadDTO rentPost = RentPostService.getById(id);
        if(rentPost.getStatus()=="còn") {
            RentPost rent = RentPostService.findById(id);
            rent.setStatus("hết phòng");
            RentPostService.saveOrUpdate(rent);
            return new ResponseEntity(id,HttpStatus.OK) ;
        }
        else {
            RentPost rent = RentPostService.findById(id);
            rent.setStatus("còn");
            RentPostService.saveOrUpdate(rent);
            return new ResponseEntity(id,HttpStatus.OK) ;
        }
    }



    @GetMapping(value = "/posts/listPricesDesc", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPostReadDTO> listPriceDesc() {
        return RentPostService.sortByPriceDesc(PageRequest.of(0,numberInPage));
    }

    @GetMapping(value = "/posts/listPricesAsc", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPostReadDTO> listPriceAsc() {
        return RentPostService.sortByPriceAsc(PageRequest.of(0,numberInPage));
    }

    @GetMapping(value = "/posts/listTimePostDesc", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPostReadDTO> listTimePostDesc() {
        return RentPostService.sortByTimePostDesc(PageRequest.of(0,numberInPage));
    }

    @GetMapping(value = "/posts/listTimePostAsc", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPostReadDTO> listTimePostAsc() {
        return RentPostService.sortByTimePostAsc(PageRequest.of(0,numberInPage));
    }

    @RequestMapping(value = "/posts/listStatus", method = {RequestMethod.GET, RequestMethod.POST}
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPostReadDTO> listStatus() {
        return RentPostService.searchByStatus("còn",PageRequest.of(0,numberInPage));
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
            int totalPage = 0 ;
            if(list.size()%numberInPage!=0)
                totalPage = list.size()/numberInPage +1;
            else totalPage = list.size()/numberInPage ;
            ListRentPost listRentPost = new ListRentPost(list,totalPage,1);
            model.addAttribute("listRoom",listRentPost);
            return "trangchu";
        }
        if (type == 0 && ward != 0) {
            list = RentPostService.searchNotRoomtype(distric, ward, startPrice, endPrice, startArea, endArea);
            int totalPage = 0 ;
            if(list.size()%numberInPage!=0)
                totalPage = list.size()/numberInPage +1;
            else totalPage = list.size()/numberInPage ;
            ListRentPost listRentPost = new ListRentPost(list,totalPage,1);
            model.addAttribute("listRoom",listRentPost);
            return "trangchu";
        }
        if (type > 0 && distric == 0) {
            list = RentPostService.searchNotLocation(type, startPrice, endPrice, startArea, endArea);
            int totalPage = 0 ;
            if(list.size()%numberInPage!=0)
                totalPage = list.size()/numberInPage +1;
            else totalPage = list.size()/numberInPage ;
            ListRentPost listRentPost = new ListRentPost(list,totalPage,1);
            model.addAttribute("listRoom",listRentPost);
            return "trangchu";
        }
        if (type > 0 && ward == 0 && distric>0) {
            list = RentPostService.searchNotWard(distric, type, startPrice, endPrice, startArea, endArea);
            int totalPage = 0 ;
            if(list.size()%numberInPage!=0)
                totalPage = list.size()/numberInPage +1;
            else totalPage = list.size()/numberInPage ;
            ListRentPost listRentPost = new ListRentPost(list,totalPage,1);
            model.addAttribute("listRoom",listRentPost);
            return "trangchu";
        }

        list = RentPostService.search(distric, ward, type, startPrice, endPrice, startArea, endArea);
        int totalPage = 0 ;
        if(list.size()%numberInPage!=0)
            totalPage = list.size()/numberInPage +1;
        else totalPage = list.size()/numberInPage ;
        ListRentPost listRentPost = new ListRentPost(list,totalPage,1);
        model.addAttribute("listRoom",listRentPost);
        return "trangchu";
    }

    @GetMapping(value = "/posts/{id}", produces = {"text/css"})
    public String listTypeRoom(@PathVariable("id") int id, Model model) {
        RentPostReadDTO rentPost = RentPostService.getById(id);
        RentPost rent = RentPostService.findById(id);
        List<RentPostReadDTO> list = RentPostService.roomTuongTu(rent.getLocation().getDistric().getId(),rent.getRoomType().getId());
        for (int i= 0;i<list.size();i++) {
           if(list.get(i).getId()==id)
               list.remove(i);
        }
        if(list.size()>3) {
            List<RentPostReadDTO> list1= new ArrayList<>();
            for (int i= 0;i<3;i++) {
               list1.add(list.get(i));
            }
            model.addAttribute("list1",list);
            model.addAttribute("room", rentPost);
            return "RoomDetail";
        }
        model.addAttribute("list",list);
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
        Location location = new Location(0,districService.findById(distric_id),wardService.findById(ward_id),street,rentPost);
        rentPost.setLocation(location);
        final String phone= SecurityContextHolder.getContext().getAuthentication().getName();
        rentPost.setUser(userService.findByPhone(phone));
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
