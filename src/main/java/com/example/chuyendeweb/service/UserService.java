package com.example.chuyendeweb.service;

import com.example.chuyendeweb.entities.CustomUserDetails;
import com.example.chuyendeweb.entities.User;
import com.example.chuyendeweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
       return userRepository.findAll();
    }

    public void saveOrUpdate(User user){
        userRepository.save(user);
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
    public void lockorUnlock(long id){
        User user = userRepository.findById(id).get();
        if(user.getState()=="lock")
        user.setState("unlock");
        else user.setState("lock");
        userRepository.save(user);
    }
    public String fogotPassword(String phone) {
        User user = userRepository.findByPhone(phone);
        return user.getPassword();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPhone(username);
        if(user==null )
throw  new UsernameNotFoundException("user not found in the database");
        if (user.getState()=="lock")
            throw  new UsernameNotFoundException("user  locked");
        return new CustomUserDetails(user);
    }
}
