package com.example.chuyendeweb.DTO.rentPost;

import com.example.chuyendeweb.DTO.ImageDTO;
import com.example.chuyendeweb.DTO.LocationDTO;
import com.example.chuyendeweb.DTO.RoomTypeDTO;
import com.example.chuyendeweb.DTO.user.UserDTO;
import com.example.chuyendeweb.entities.*;
import com.example.chuyendeweb.service.RoomTypeService;
import com.example.chuyendeweb.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentPostWriteDTO {
    int roomType;
    MultipartFile[] images;
    String title;
    String detail;
    int price;
    double acreage ;
    int distric_id;
    int ward_id;
    String street;
    String sex ;
    String username;
   public static RentPost trantToRentpost(RentPostWriteDTO writeDTO) {
       RentPost rentPost = new RentPost();
       rentPost.setTimePost(new Date());
       rentPost.setTitle(writeDTO.getTitle());
       rentPost.setDetail(writeDTO.getDetail());
       rentPost.setPrice(writeDTO.getPrice());
       rentPost.setAcreage(writeDTO.getAcreage());
       rentPost.setSex(writeDTO.getSex());
       rentPost.setStatus("còn");
       location location = new location(0, writeDTO.getDistric_id(), writeDTO.getWard_id(), writeDTO.getDetail(), rentPost);
       rentPost.setLocation(location);
       return rentPost;
   }
}
