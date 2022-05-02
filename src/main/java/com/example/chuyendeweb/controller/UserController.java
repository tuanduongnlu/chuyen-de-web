package com.example.chuyendeweb.controller;

import com.example.chuyendeweb.entities.User;
import com.example.chuyendeweb.service.UserService;
import com.example.chuyendeweb.untils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping(value = "/users", method = RequestMethod.GET
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<User> getAll() {
        return service.findAll();
    }
    @RequestMapping(value = "/users/exists/{phone}", method = RequestMethod.GET
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<Boolean> checkPhoneExist(@PathVariable String phone){
        return ResponseEntity.ok().body(service.checkPhoneExist(phone));
    }
    @RequestMapping(value = "/users", method = RequestMethod.POST
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String create(@RequestBody User user) throws CustomException {
        if(service.checkPhoneExist(user.getPhone()))
            throw  new CustomException("so dien thoai da ton tai", HttpStatus.BAD_REQUEST);
        service.saveOrUpdate(user);
        return "tao thanh cong";
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String update(@RequestBody User user) throws CustomException{
        if(service.checkPhoneExist(user.getPhone()))
            throw  new CustomException("so dien thoai da ton tai", HttpStatus.BAD_REQUEST);
        service.saveOrUpdate(user);
        return "update thanh cong";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String delete(@PathVariable("id") long id){
        service.delete(id);
        return "xoa thanh cong" ;
    }

    @RequestMapping(value = "/users/{id}", method = { RequestMethod.GET, RequestMethod.POST }
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public User getUser(@PathVariable("id") long id){
        return service.getUserById(id);
    }

    @RequestMapping(value = "/users/states/{id}", method = RequestMethod.GET
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String setState(@PathVariable("id") long id) {
    service.lockorUnlock(id);
    return "thanh cong" ;
    }

    @RequestMapping(value = "/users/passwords/{phone}", method = RequestMethod.POST
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public void fogotPassword(@PathVariable String phone) {

    }

}
