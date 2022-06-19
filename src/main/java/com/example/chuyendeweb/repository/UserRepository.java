package com.example.chuyendeweb.repository;

import com.example.chuyendeweb.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByPhoneAndPassword (String phone,String password);
    User findByPhone (String phone);
    boolean existsByPhone(String phone);
    User findByName(String name);
    User findById (long id);
}
