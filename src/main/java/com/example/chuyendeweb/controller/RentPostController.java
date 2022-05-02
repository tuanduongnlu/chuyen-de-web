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
    @RequestMapping(value = "/rentPosts", method = RequestMethod.GET
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPost> getAll() {
        return RentPostService.rentPosts() ;
    }

    @RequestMapping(value = "/rentPosts", method = RequestMethod.POST
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public void create(@RequestBody RentPost rentPost) {
        RentPostService.saveOrUpdate(rentPost);
    }

    @RequestMapping(value = "/rentPosts/{id}", method = RequestMethod.DELETE
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
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

    @RequestMapping(value = "/rentPosts", method = RequestMethod.PUT
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
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

    @RequestMapping(value = "/rentPosts/listPricesDesc", method = RequestMethod.GET
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPost> listPriceDesc(){
        return RentPostService.sortByPriceDesc() ;
    }

    @RequestMapping(value = "/rentPosts/listPricesAsc", method = RequestMethod.GET
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPost> listPriceAsc(){
        return RentPostService.sortByPriceAsc() ;
    }

    @RequestMapping(value = "/rentPosts/listTimePostDesc", method = RequestMethod.GET
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RentPost> listTimePostDesc(){
        return RentPostService.sortByTimePostDesc() ;
    }

    @RequestMapping(value = "/rentPosts/listTimePostAsc", method = RequestMethod.GET
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
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
