package com.example.chuyendeweb.service;

import com.example.chuyendeweb.entities.User;
import com.example.chuyendeweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
       return userRepository.findAll();
    }

    public void saveOrUpdate(User user){
        userRepository.saveAndFlush(user);
    }

    public void delete(long id){
        userRepository.deleteById(id);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }
//    public boolean checkLogin (String phone ,String password) {
//        User user = userRepository.findByPhoneAndPassword(phone, password);
//        if(user==null) return false ;
//        return true;
//    }
    public void lockorUnlock(Long id){
        User user = userRepository.findById(id).get();
        int state = user.getState();
        if(state==0)
            user.setState(1);
        else user.setState(0);
        saveOrUpdate(user);
    }
    public String fogotPassword(String phone) {
        User user = userRepository.findByPhone(phone);
        return user.getPassword();
    }

    public boolean checkPhoneExist(String phone) {
        return userRepository.existsByPhone(phone);
    }

    public User checkLogin (String userName ,String password){
        User user = userRepository.findByPhoneAndPassword(userName,password);
        return user;
    }

}
