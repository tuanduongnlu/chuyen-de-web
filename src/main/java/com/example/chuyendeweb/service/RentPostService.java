package com.example.chuyendeweb.service;

import com.example.chuyendeweb.DTO.rentPost.RentPostReadDTO;
import com.example.chuyendeweb.entities.RentPost;
import com.example.chuyendeweb.repository.RentPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public RentPostReadDTO getById(int id) {
        RentPostReadDTO readDTO = RentPostReadDTO.transtoDTO(repository.getById(id));
        return readDTO ;
    }

    //tim kiem theo loai phong ,co sap xep ngay dang
    public List<RentPostReadDTO> searchByTypeRoom(Long idRoom){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.findAllByRoomType_idOrderByTimePostDesc(idRoom)) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    }
    // sap xep theo gia giam
    public List<RentPostReadDTO> sortByPriceDesc(){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.findAllByOrderByPriceDesc()) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
     return rentPostReadDTOS;
    }
    // sap xep theo gia tang
    public List<RentPostReadDTO> sortByPriceAsc(){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.findAllByOrderByPriceAsc()) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    }
    // sap xep theo ngay dang tang
    public List<RentPost> sortByTimePostDesc(){
        return repository.findAllByOrderByTimePostDesc();
    }
    // sap xep theo ngay dang giam
    public List<RentPostReadDTO> sortByTimePostAsc(){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.findAllByOrderByTimePostAsc()) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    }
    //tim kiem theo tinh trang
    public List<RentPostReadDTO> searchByStatus(String status){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.findByStatusOrderByTimePostDesc(status)) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    }
    // tim kiem theo userId
    public RentPost searchByUserId(long id) {
        return  repository.findByUser_id(id);
    }
    //search
    public List<RentPostReadDTO> search ( int distric_id,  int ward_id,
                          int roomtype,  int startPrice,
                            int endPrice,  int startArea,
                           int endArea){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.search(distric_id,ward_id,roomtype,startPrice,endPrice,startArea,endArea)) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    };

    public List<RentPostReadDTO> searchNotRoomtype ( int distric_id, int ward_id,
                                       int startPrice,
                                      int endPrice, int startArea,
                                       int endArea){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.searchNotRoomtype(distric_id,ward_id,startPrice,endPrice,startArea,endArea)) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    };

    public List<RentPostReadDTO> searchNotLocation ( int roomtype,int startPrice,
                                      int endPrice, int startArea,
                                     int endArea) {
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.searchNotLocation(roomtype,startPrice,endPrice,startArea,endArea)) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    };

    public List<RentPostReadDTO> searchNotWard ( int distric_id,
                                  int roomtype,  int startPrice,
                                  int endPrice,  int startArea,
                                  int endArea){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.searchNotWard(distric_id,roomtype,startPrice,endPrice,startArea,endArea)) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    };
    public List<RentPostReadDTO> searchNotWardAndRoomType(int distric_id, int startPrice,
                                                   int endPrice, int startArea,
                                                   int endArea){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.searchNotWardAndRoomType(distric_id,startPrice,endPrice,startArea,endArea)) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    };
}
