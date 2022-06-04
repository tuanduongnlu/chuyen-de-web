package com.example.chuyendeweb;

import com.example.chuyendeweb.entities.User;
import com.example.chuyendeweb.repository.UserRepository;
import com.example.chuyendeweb.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class ChuyenDeWebApplication implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Resource
    FilesStorageService filesStorageService;

    public static void main(String[] args) {
        SpringApplication.run(ChuyenDeWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


    }
}
