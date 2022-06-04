package com.example.chuyendeweb.service;

import com.example.chuyendeweb.entities.User;
import com.example.chuyendeweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder BCryptPasswordEncoder;

    public List<User> findAll() {
       return userRepository.findAll();
    }

    public void saveOrUpdate(User user){
        user.setPassword(BCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public void delete(long id){
        userRepository.deleteById(id);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }

    public void lockorUnlock(Long id){
        User user = userRepository.findById(id).get();
        if(user.isState())
            user.setState(false);
        else user.setState(true);
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
