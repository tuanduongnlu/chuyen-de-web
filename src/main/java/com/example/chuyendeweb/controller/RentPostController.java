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

import javax.servlet.http.HttpServletRequest;
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
    UserService userService;
    @Autowired
    FileService fileService;
    @Value("${numberInPage}")
    int numberInPage;

    @GetMapping(value = {"/post/{x}"})
    public String postDetail() {
        return "rentPost";
    }

    @GetMapping("/postFindRoom")
    public String getPostSeachRoomPage(Model model) {
        return "postFindRoom";
    }

    @GetMapping("/posts/motelRoom")
    public String getPostMotelRoomPage(Model model) {
        List<RentPostReadDTO> list = RentPostService.searchByTypeRoom(1,PageRequest.of(0,numberInPage));
        int totalPage = 0 ;
        int count = RentPostService.totalListSearhByTypeRoom(1);
        if(count%numberInPage!=0)
            totalPage = count/numberInPage +1;
        else totalPage = count/numberInPage ;
        ListRentPost listRentPost = new ListRentPost(list,totalPage,1);
        model.addAttribute("listRoom",listRentPost);
        return "motelRoom";
    }

    @GetMapping("/posts/fullHouse")
    public String getPostFullHousePage(Model model) {
        List<RentPostReadDTO> list = RentPostService.searchByTypeRoom(2,PageRequest.of(0,numberInPage));
        int totalPage = 0 ;
        int count = RentPostService.totalListSearhByTypeRoom(2);
        if(count%numberInPage!=0)
            totalPage = count/numberInPage +1;
        else totalPage = count/numberInPage ;
        ListRentPost listRentPost = new ListRentPost(list,totalPage,2);
        model.addAttribute("listRoom",listRentPost);
        return "fullHouse";
    }

    @GetMapping("/posts/apartment")
    public String getPostApartmentPage(Model model) {
        List<RentPostReadDTO> list = RentPostService.searchByTypeRoom(3,PageRequest.of(0,numberInPage));
        int totalPage = 0 ;
        int count = RentPostService.totalListSearhByTypeRoom(3);
        if(count%numberInPage!=0)
            totalPage = count/numberInPage +1;
        else totalPage = count/numberInPage ;
        ListRentPost listRentPost = new ListRentPost(list,totalPage,3);
        model.addAttribute("listRoom",listRentPost);
        return "apartment";
    }

    @GetMapping(value = "/post")
    public String getPostPage(Model model) {
        RentPostWriteDTO rentPost = new RentPostWriteDTO();
        model.addAttribute("rentPost", rentPost);
        return "rentPost";
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        RentPostService.delete(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @GetMapping(value = "/status/{id}")
    public String updateStatus(@PathVariable("id") int id) {
        RentPostReadDTO readDTO = RentPostService.getById(id);
        String status = readDTO.getStatus();
        RentPost rent = RentPostService.findById(id);
        if (status.equals("còn")) {
            rent.setStatus("hết phòng");
            RentPostService.saveOrUpdate(rent);
            return "redirect:/management";
        } else {
            rent.setStatus("còn");
            RentPostService.saveOrUpdate(rent);
            return "redirect:/management";
        }
    }


    @GetMapping(value = "/posts/listPricesDesc", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPostReadDTO> listPriceDesc() {
        return RentPostService.sortByPriceDesc(PageRequest.of(0, numberInPage));
    }

    @GetMapping(value = "/posts/listPricesAsc", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPostReadDTO> listPriceAsc() {
        return RentPostService.sortByPriceAsc(PageRequest.of(0, numberInPage));
    }

    @GetMapping(value = "/posts/listTimePostDesc", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPostReadDTO> listTimePostDesc() {
        return RentPostService.sortByTimePostDesc(PageRequest.of(0, numberInPage));
    }

    @GetMapping(value = "/posts/listTimePostAsc", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPostReadDTO> listTimePostAsc() {
        return RentPostService.sortByTimePostAsc(PageRequest.of(0, numberInPage));
    }

    @RequestMapping(value = "/posts/listStatus", method = {RequestMethod.GET, RequestMethod.POST}
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPostReadDTO> listStatus() {
        return RentPostService.searchByStatus("còn", PageRequest.of(0, numberInPage));
    }


    @PostMapping(value = "/search")
    public String search(@RequestParam("distric") int distric, @RequestParam("ward") int ward,
                         @RequestParam("startPrice") int startPrice, @RequestParam("endPrice") int endPrice,
                         @RequestParam("startArea") int startArea, @RequestParam("endArea") int endArea, @RequestParam("type") int type,
                         Model model) {
        List<RentPostReadDTO> list = new ArrayList<>();
        if (endArea == 0) endArea = 100000;
        if (endPrice == 0) endPrice = 100000000;
        if(type==0 && distric==0){
            return "redirect:/home";
        }
        if (type == 0 && ward == 0) {
            list = RentPostService.searchNotWardAndRoomType(distric, startPrice, endPrice, startArea, endArea);
            int totalPage = 0;
            if (list.size() % numberInPage != 0)
                totalPage = list.size() / numberInPage + 1;
            else totalPage = list.size() / numberInPage;
            ListRentPost listRentPost = new ListRentPost(list, totalPage, 1);
            model.addAttribute("listRoom", listRentPost);
            return "trangchu";
        }
        if (type == 0 && ward != 0) {
            list = RentPostService.searchNotRoomtype(distric, ward, startPrice, endPrice, startArea, endArea);
            int totalPage = 0;
            if (list.size() % numberInPage != 0)
                totalPage = list.size() / numberInPage + 1;
            else totalPage = list.size() / numberInPage;
            ListRentPost listRentPost = new ListRentPost(list, totalPage, 1);
            model.addAttribute("listRoom", listRentPost);
            return "trangchu";
        }
        if (type > 0 && distric == 0) {
            list = RentPostService.searchNotLocation(type, startPrice, endPrice, startArea, endArea);
            int totalPage = 0;
            if (list.size() % numberInPage != 0)
                totalPage = list.size() / numberInPage + 1;
            else totalPage = list.size() / numberInPage;
            ListRentPost listRentPost = new ListRentPost(list, totalPage, 1);
            model.addAttribute("listRoom", listRentPost);
            return "trangchu";
        }
        if (type > 0 && ward == 0 && distric > 0) {
            list = RentPostService.searchNotWard(distric, type, startPrice, endPrice, startArea, endArea);
            int totalPage = 0;
            if (list.size() % numberInPage != 0)
                totalPage = list.size() / numberInPage + 1;
            else totalPage = list.size() / numberInPage;
            ListRentPost listRentPost = new ListRentPost(list, totalPage, 1);
            model.addAttribute("listRoom", listRentPost);
            return "trangchu";
        }

        list = RentPostService.search(distric, ward, type, startPrice, endPrice, startArea, endArea);
        int totalPage = 0;
        if (list.size() % numberInPage != 0)
            totalPage = list.size() / numberInPage + 1;
        else totalPage = list.size() / numberInPage;
        ListRentPost listRentPost = new ListRentPost(list, totalPage, 0);
        model.addAttribute("listRoom", listRentPost);
        return "trangchu";
    }

    @GetMapping(value = "/posts/{id}", produces = {"text/css"})
    public String listTypeRoom(@PathVariable("id") int id, Model model) {
        RentPostReadDTO rentPost = RentPostService.getById(id);
        RentPost rent = RentPostService.findById(id);
        List<RentPostReadDTO> list = RentPostService.roomTuongTu(rent.getLocation().getDistric().getId(), rent.getRoomType().getId());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id)
                list.remove(i);
        }
        if (list.size() > 3) {
            List<RentPostReadDTO> list1 = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                list1.add(list.get(i));
            }
            model.addAttribute("list1", list);
            model.addAttribute("room", rentPost);
            return "RoomDetail";
        }
        model.addAttribute("list", list);
        model.addAttribute("room", rentPost);
        return "RoomDetail";
    }

    @PostMapping(value = "/post")
    public String post(@RequestParam("roomType") int roomType, @RequestParam("images") MultipartFile[] images,
                       @RequestParam("title") String title, @RequestParam("detail") String detail,
                       @RequestParam("price") int price, @RequestParam("acreage") double acreage,
                       @RequestParam("distric_id") int distric_id, @RequestParam("ward_id") int ward_id,
                       @RequestParam("street") String street, @RequestParam("sex") String sex, HttpServletRequest req) throws Exception {
        RentPostWriteDTO writeDTO = new RentPostWriteDTO(roomType, images, title, detail, price, acreage, distric_id, ward_id, street, sex);
        RentPost rentPost = RentPostWriteDTO.trantToRentpost(writeDTO);
        rentPost.setRoomType(roomTypeService.getById(writeDTO.getRoomType()));
        Location location = new Location(0, districService.findById(distric_id), wardService.findById(ward_id), street, rentPost);
        rentPost.setLocation(location);
        final String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        rentPost.setUser(userService.findByPhone(phone));
        List<Image> imagesRentPost = new ArrayList<>();
        List<String> nameImage = fileService.upload(images,req);
        for (String name : nameImage) {
                Image image = new Image(0, "/templates/images/" + name, rentPost);
                imagesRentPost.add(image);
        }
        rentPost.setImages(imagesRentPost);
        RentPostService.saveOrUpdate(rentPost);
        return "redirect:home";
    }



}
