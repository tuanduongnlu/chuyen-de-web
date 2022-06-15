package com.example.chuyendeweb.service;

import com.example.chuyendeweb.entities.RentPost;
import com.example.chuyendeweb.repository.RentPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class RentPostService {

    private final  RentPostRepository repository;

    // tra ve danh sach
    public List<RentPost> rentPosts (){
        return repository.findAll();
    }
    // xoa theo id
    public void delete(int id){
        repository.deleteById(id);
    }
    // luu hoac update
    public void saveOrUpdate(RentPost rentPost){
        repository.save(rentPost);
    }
    //tim kiem theo gia ,co sap xep theo ngay dang
    public List<RentPost> searchByPrice(int priceStart ,int priceEnd) {
        return  repository.findAllByPriceBetween(priceStart,priceEnd);
    }
    //tim kiem theo loai phong ,co sap xep ngay dang
    public List<RentPost> searchByTypeRoom(Long idRoom){
        return  repository.findAllByRoomType_idOrderByTimePostDesc(idRoom);
    }
    // sap xep theo gia giam
    public List<RentPost> sortByPriceDesc(){
     return repository.findAllByOrderByPriceDesc();
    }
    // sap xep theo gia tang
    public List<RentPost> sortByPriceAsc(){
        return repository.findAllByOrderByPriceAsc();
    }
    // sap xep theo ngay dang tang
    public List<RentPost> sortByTimePostDesc(){
        return repository.findAllByOrderByTimePostDesc();
    }
    // sap xep theo ngay dang giam
    public List<RentPost> sortByTimePostAsc(){
        return repository.findAllByOrderByTimePostAsc();
    }
    //tim kiem theo tinh trang
    public List<RentPost> searchByStatus(String status){
        return repository.findByStatusOrderByTimePostDesc(status);
    }
    // tim kiem theo userId
    public RentPost searchByUserId(long id) {
        return  repository.findByUser_id(id);
    }
    //search
    public List<RentPost> search ( int distric_id,  int ward_id,
                          int roomtype,  int startPrice,
                            int endPrice,  int startArea,
                           int endArea){
        return repository.search(distric_id,ward_id,roomtype,startPrice,endPrice,startArea,endArea);
    };

    public List<RentPost> searchNotRoomtype ( int distric_id, int ward_id,
                                       int startPrice,
                                      int endPrice, int startArea,
                                       int endArea){
        return repository.searchNotRoomtype(distric_id,ward_id,startPrice,endPrice,startArea,endArea);
    };

    public List<RentPost> searchNotLocation ( int roomtype,int startPrice,
                                      int endPrice, int startArea,
                                     int endArea) {
        return repository.searchNotLocation(roomtype,startPrice,endPrice,startArea,endArea);
    };

    public List<RentPost> searchNotWard ( int distric_id,
                                  int roomtype,  int startPrice,
                                  int endPrice,  int startArea,
                                  int endArea){
        return repository.searchNotWard(distric_id,roomtype,startPrice,endPrice,startArea,endArea);
    };
    public List<RentPost> searchNotWardAndRoomType(int distric_id, int startPrice,
                                                   int endPrice, int startArea,
                                                   int endArea){
        return repository.searchNotWardAndRoomType(distric_id,startPrice,endPrice,startArea,endArea);
    };
}
