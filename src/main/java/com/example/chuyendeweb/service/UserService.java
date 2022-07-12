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

    public void save(User user){
        user.setPassword(BCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public void update(User user){
        userRepository.save(user);
    }
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public void delete(long id){
        userRepository.deleteById(id);
    }

    public User getUserById(long id){
        return userRepository.findById(id);
    }

    public User getByName(String name) {
        return userRepository.findByName(name);
    }


    public void lockorUnlock(long id){
        User user = userRepository.findById(id);
        if(user.isState())
            user.setState(false);
        else user.setState(true);
        update(user);
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
