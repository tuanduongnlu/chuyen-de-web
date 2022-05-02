package com.example.chuyendeweb;

import com.example.chuyendeweb.entities.User;
import com.example.chuyendeweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChuyenDeWebApplication implements CommandLineRunner {
@Autowired
    UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(ChuyenDeWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User(Long.parseLong("1"),"admin","12345678","0349651548",
                "letuanduong682000@gmail.com",1,"ROLE_ADMIN");
        userRepository.save(user);
    }
}
