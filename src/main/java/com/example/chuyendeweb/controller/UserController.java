package com.example.chuyendeweb.controller;

import com.example.chuyendeweb.entities.User;
import com.example.chuyendeweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/users", method = RequestMethod.POST
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public void create(@RequestBody User user) {
        service.saveOrUpdate(user);
    }
    @RequestMapping(value = "/users", method = RequestMethod.PUT
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public void update(@RequestBody User user){
        service.saveOrUpdate(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public void delete(@PathVariable long id){
        service.delete(id);
    }
    @RequestMapping(value = "/users/states/{id}", method = RequestMethod.GET
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public void setState(@PathVariable long id) {
    service.lockorUnlock(id);
    }

    @RequestMapping(value = "/users/passwords/{phone}", method = RequestMethod.POST
            , produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public void fogotPassword(@PathVariable String phone) {

    }

}
