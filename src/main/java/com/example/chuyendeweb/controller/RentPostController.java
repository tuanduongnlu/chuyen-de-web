package com.example.chuyendeweb.controller;


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
        return RentPostService.rentPosts() ;
    }

    @PostMapping(value = "/rentPosts")
    public void create(@RequestBody RentPost rentPost) {
        RentPostService.saveOrUpdate(rentPost);
    }

    @DeleteMapping(value = "/rentPosts/{id}")
    public void delete(@PathVariable("id") Long id){
        RentPostService.delete(id);
    }

    @RequestMapping(value = "/rentPosts/{id}", method = { RequestMethod.GET, RequestMethod.POST }
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public RentPost getRentPost(@PathVariable("id") long id){
        return  RentPostService.searchByUserId(id);
    }

   @PutMapping(value = "/rentPosts")
    public void update(@RequestBody RentPost rentPost){
        RentPostService.saveOrUpdate(rentPost);
    }

    @RequestMapping(value = "/rentPosts/listPrices/{startPrice}_{endPrice}", method = { RequestMethod.GET, RequestMethod.POST }
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPost> listPrice(@PathVariable("startPrice") int startPrice ,@PathVariable("endPrice") int endPrice){
        return RentPostService.searchByPrice(startPrice,endPrice) ;
    }

    @RequestMapping(value = "/rentPosts/listTypeRooms/{id}", method = { RequestMethod.GET, RequestMethod.POST }
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPost> listTypeRoom(@PathVariable("id") long id){
        return RentPostService.searchByTypeRoom(id) ;
    }

    @GetMapping(value = "/rentPosts/listPricesDesc")
    public List<RentPost> listPriceDesc(){
        return RentPostService.sortByPriceDesc() ;
    }


    @GetMapping(value = "/rentPosts/listPricesAsc")
    public List<RentPost> listPriceAsc(){
        return RentPostService.sortByPriceAsc() ;
    }

    @GetMapping(value = "/rentPosts/listTimePostDesc")
    public List<RentPost> listTimePostDesc(){
        return RentPostService.sortByTimePostDesc() ;
    }

    @GetMapping(value = "/rentPosts/listTimePostAsc")
    public List<RentPost> listTimePostAsc(){
        return RentPostService.sortByTimePostAsc() ;
    }

    @RequestMapping(value = "/rentPosts/listStatus/{status}", method = { RequestMethod.GET, RequestMethod.POST }
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPost> listStatus(@PathVariable("status") String status){
        return RentPostService.searchByStatus(status) ;
    }

    @RequestMapping(value = "/rentPosts/lists/{distric}_{ward}_{detail}", method = { RequestMethod.GET, RequestMethod.POST }
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPost> searchByLoaction(@PathVariable("distric") long distric ,@PathVariable("ward") long ward,@PathVariable("detail") String detail){
        return RentPostService.searchByLocation(distric,ward,detail) ;
    }
}
