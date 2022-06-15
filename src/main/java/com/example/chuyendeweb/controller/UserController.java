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

    @GetMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public List<User> getAll() {
        return service.findAll();
    }

    @GetMapping(value = "/users/exists/{phone}", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Boolean> checkPhoneExist(@PathVariable String phone){
        return ResponseEntity.ok().body(service.checkPhoneExist(phone));
    }

    @PostMapping(value = "/users",produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public String create(@RequestBody User user) throws CustomException {
        if(service.checkPhoneExist(user.getPhone()))
            throw  new CustomException("so dien thoai da ton tai", HttpStatus.BAD_REQUEST);
        service.saveOrUpdate(user);
        return "tao thanh cong";
    }

    @PutMapping(value = "/users",produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public String update(@RequestBody User user) throws CustomException{
        if(service.checkPhoneExist(user.getPhone()))
            throw  new CustomException("so dien thoai da ton tai", HttpStatus.BAD_REQUEST);
        service.saveOrUpdate(user);
        return "update thanh cong";
    }

    @DeleteMapping(value = "/users/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public String delete(@PathVariable("id") int id){
        service.delete(id);
        return "xoa thanh cong" ;
    }

    @RequestMapping(value = "/users/{id}", method = { RequestMethod.GET, RequestMethod.POST }
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public User getUser(@PathVariable("id") int id){
        return service.getUserById(id);
    }


    @GetMapping(value = "/users/states/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public String setState(@PathVariable("id") int id) {
    service.lockorUnlock(id);
    return "thanh cong" ;
    }


    @PostMapping(value = "/users/passwords/{phone}", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public void fogotPassword(@PathVariable String phone) {

    }

}
