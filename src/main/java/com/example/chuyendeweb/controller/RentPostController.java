package com.example.chuyendeweb.controller;


import com.example.chuyendeweb.DTO.rentPost.RentPostReadDTO;
import com.example.chuyendeweb.entities.RentPost;
import com.example.chuyendeweb.service.RentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RentPostController {
    @Autowired
    com.example.chuyendeweb.service.RentPostService RentPostService;

    @GetMapping(value = "/rentPosts")
    public List<RentPost> getAll() {
        return RentPostService.rentPosts();
    }

    @PostMapping(value = "/rentPosts")
    public void create(@RequestBody RentPost rentPost) {
        RentPostService.saveOrUpdate(rentPost);
    }

    @DeleteMapping(value = "/rentPosts/{id}")
    public void delete(@PathVariable("id") int id) {
        RentPostService.delete(id);
    }

    @RequestMapping(value = "/rentPosts/{id}", method = {RequestMethod.GET, RequestMethod.POST}
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public RentPost getRentPost(@PathVariable("id") long id) {
        return RentPostService.searchByUserId(id);
    }

    @PutMapping(value = "/rentPosts")
    public void update(@RequestBody RentPost rentPost) {
        RentPostService.saveOrUpdate(rentPost);
    }

    @GetMapping(value = "/rentPosts/listPricesDesc")
    public List<RentPostReadDTO> listPriceDesc() {
        return RentPostService.sortByPriceDesc();
    }


    @GetMapping(value = "/rentPosts/listPricesAsc")
    public List<RentPostReadDTO> listPriceAsc() {
        return RentPostService.sortByPriceAsc();
    }

    @GetMapping(value = "/rentPosts/listTimePostDesc")
    public List<RentPost> listTimePostDesc() {
        return RentPostService.sortByTimePostDesc();
    }

    @GetMapping(value = "/rentPosts/listTimePostAsc")
    public List<RentPostReadDTO> listTimePostAsc() {
        return RentPostService.sortByTimePostAsc();
    }

    @RequestMapping(value = "/rentPosts/listStatus", method = {RequestMethod.GET, RequestMethod.POST}
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public List<RentPostReadDTO> listStatus() {
        return RentPostService.searchByStatus("còn");
    }


    @PostMapping(value = "/search",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    public List<RentPost> search(@RequestParam("distric") int distric, @RequestParam("ward") int ward,
                                 @RequestParam("startPrice") int startPrice, @RequestParam("endPrice") int endPrice,
                                 @RequestParam("startArea") int startArea, @RequestParam("endArea") int endArea, @RequestParam("type") int type) {
        if (endArea == 0) endArea = 100000;
        if (endPrice == 0) endPrice = 100000000;
        if (type == 0 && ward == 0)
            return RentPostService.searchNotWardAndRoomType(distric,startPrice,endPrice,startArea,endArea);
        if(type==0 && ward!=0)
            return RentPostService.searchNotRoomtype(distric,ward,startPrice,endPrice,startArea,endArea);
        if(type!=0 && ward==0)
            return RentPostService.searchNotWard(distric,type,startPrice,endPrice,startArea,endArea);
        if(type!=0 && distric==0 )
            return RentPostService.searchNotLocation(type,startPrice,endPrice,startArea,endArea);
        return RentPostService.search(distric,ward,type,startPrice,endPrice,startArea,endArea);
    }

}
