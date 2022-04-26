package com.example.chuyendeweb;

import com.example.chuyendeweb.entities.User;
import com.example.chuyendeweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ChuyenDeWebApplication implements CommandLineRunner {
@Autowired
    UserRepository userRepository;
@Autowired
    PasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(ChuyenDeWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User(1,"duong",passwordEncoder.encode("12345678"),"0349651548",
                "letuanduong682000@gmail.com","unlock","ROLE_ADMIN");
        userRepository.save(user);
    }
}
