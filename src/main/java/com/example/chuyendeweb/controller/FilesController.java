package com.example.chuyendeweb.controller;

import com.example.chuyendeweb.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FilesController {
    @Autowired
    FilesStorageService  storageService;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile [] files) {
        String message = "Uploaded the file successfully: ";
    for(MultipartFile file:files) {
        try {
            storageService.save(file);
            message +=  file.getOriginalFilename() +", ";
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message + " " + e.getMessage());
        }
    }
        return ResponseEntity.status(HttpStatus.OK).body(message);
}
}
