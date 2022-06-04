package com.example.chuyendeweb.service.impl;
import com.example.chuyendeweb.entities.Role;
import com.example.chuyendeweb.entities.User;
import com.example.chuyendeweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;;
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepository.findByPhone(phone);
        if (user == null ) throw new UsernameNotFoundException(phone);
        Collection<SimpleGrantedAuthority> authrities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authrities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getPhone(),user.getPassword(),authrities);
    }
}