package com.example.chuyendeweb.service;

import com.example.chuyendeweb.entities.RentPost;
import com.example.chuyendeweb.repository.RentPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class RentPostService {

    private final  RentPostRepository repository;

    // tra ve danh sach
    List<RentPost> rentPosts (){
        return repository.findAll();
    }
    // xoa theo id
    void delete(long id){
        repository.deleteById(id);
    }
    // luu hoac update
    void saveOrUpdate(RentPost rentPost){
        repository.save(rentPost);
    }
    //tim kiem theo gia ,co sap xep theo ngay dang
    List<RentPost> searchByPrice(int priceStart ,int priceEnd) {
        return  null;
    }
    //tim kiem theo loai phong ,co sap xep ngay dang
    List<RentPost> searchByTypeRoom(int idRoom){
        return  repository.findAllByRoomType_idOrderByTimePostDesc(idRoom);
    }
    // sap xep theo gia
    List<RentPost> sortByPriceDesc(){
     return repository.findAllByOrderByPriceDesc();
    }
    // sap xep theo gia
    List<RentPost> sortByPriceAsc(){
        return repository.findAllByOrderByPriceAsc();
    }
    // sap xep theo ngay dang
    List<RentPost> sortByTimePostDesc(){
        return repository.findAllByOrderByTimePostDesc();
    }
    // sap xep theo ngay dang
    List<RentPost> sortByTimePostAsc(){
        return repository.findAllByOrderByTimePostAsc();
    }
    //tim kiem theo tinh trang
    List<RentPost> searchByStatus(String status){
        return repository.findByStatusOrderByTimePostDesc(status);
    }
    // tim kiem theo dia chi

}
