package com.example.chuyendeweb.DTO.rentPost;

import com.example.chuyendeweb.DTO.ImageDTO;
import com.example.chuyendeweb.DTO.LocationDTO;
import com.example.chuyendeweb.DTO.RoomTypeDTO;
import com.example.chuyendeweb.DTO.user.UserWriteDTO;
import com.example.chuyendeweb.entities.RentPost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentPostReadDTO {
    int id ;
    String timePost;
    RoomTypeDTO roomType;
    List<ImageDTO> images;
    String title;
    String detail;
    int price;
    double acreage ;
    LocationDTO location;
    String sex ;
    String status;
    UserWriteDTO user ;
public static RentPostReadDTO transtoDTO(RentPost rentPost) {
  RentPostReadDTO readDTO = new RentPostReadDTO();
  readDTO.setId(rentPost.getId());
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String strDate = formatter.format(rentPost.getTimePost());
  readDTO.setTimePost(strDate);
  RoomTypeDTO roomTypeDTO = RoomTypeDTO.trantoDTO(rentPost.getRoomType());
  readDTO.setRoomType(roomTypeDTO);
  readDTO.setDetail(rentPost.getDetail());
  ArrayList<ImageDTO> imageDTOS = new ArrayList<>() ;
  for(int i =0 ;i< rentPost.getImages().size();i++) {
      imageDTOS.add(ImageDTO.transtoDTO(rentPost.getImages().get(i)));
  }
  readDTO.setImages(imageDTOS);
  readDTO.setTitle(rentPost.getTitle());
  readDTO.setPrice(rentPost.getPrice());
  readDTO.setAcreage(rentPost.getAcreage());
  readDTO.setLocation(LocationDTO.trantoDTO(rentPost.getLocation()));
  readDTO.setSex(rentPost.getSex());
  readDTO.setStatus(rentPost.getStatus());
  readDTO.setUser(UserWriteDTO.transtoDTO(rentPost.getUser()));
  return readDTO;
}
}
