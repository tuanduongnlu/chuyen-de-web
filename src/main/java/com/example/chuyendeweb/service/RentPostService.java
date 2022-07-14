package com.example.chuyendeweb.service;

import com.example.chuyendeweb.DTO.rentPost.RentPostReadDTO;
import com.example.chuyendeweb.entities.RentPost;
import com.example.chuyendeweb.entities.User;
import com.example.chuyendeweb.repository.RentPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
        RentPostReadDTO readDTO = RentPostReadDTO.transtoDTO(repository.findById(id));
        return readDTO ;
    }
    public RentPost findById(int id ){
        return repository.findById(id);
    }

    public List<RentPostReadDTO> findByUser(User user) {
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.findAllByUser(user)) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    }

    //tim kiem theo loai phong
    public List<RentPostReadDTO> searchByTypeRoom(int idRoom,Pageable pageable){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.findAllByRoomType_idOrderByTimePostDesc(idRoom,pageable)) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    }
    public int totalListSearhByTypeRoom(int idRoom) {
        return repository.findAllByRoomType_idOrderByTimePostDesc(idRoom).size();
    }

    public List<RentPostReadDTO> listWithTypeRoomSortByPriceDesc(int idRoom,Pageable pageable){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.findAllByRoomType_idOrderByPriceDesc(idRoom,pageable)) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    }

    public List<RentPostReadDTO> listWithTypeRoomSortByPriceAsc(int idRoom,Pageable pageable){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.findAllByRoomType_idOrderByPriceAsc(idRoom,pageable)) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    }

    public List<RentPostReadDTO> listWithTypeRoomAndStatus(int idRoom,String status,Pageable pageable){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.findAllByRoomType_idAndStatusOrderByTimePostDesc(idRoom,status,pageable)) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    }
    // sap xep theo gia giam
    public List<RentPostReadDTO> sortByPriceDesc(Pageable pageable){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.findAllByOrderByPriceDesc(pageable)) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
     return rentPostReadDTOS;
    }
    // sap xep theo gia tang
    public List<RentPostReadDTO> sortByPriceAsc(Pageable pageable){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.findAllByOrderByPriceAsc(pageable)) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    }
    // sap xep theo ngay dang tang
    public List<RentPostReadDTO> sortByTimePostDesc(Pageable pageable){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.findAllByOrderByTimePostDesc(pageable)) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    }
    // sap xep theo ngay dang giam
    public List<RentPostReadDTO> sortByTimePostAsc(Pageable pageable){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.findAllByOrderByTimePostAsc(pageable)) {
            rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
        }
        return rentPostReadDTOS;
    }
    //tim kiem theo tinh trang
    public List<RentPostReadDTO> searchByStatus(String status,Pageable pageable){
        List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
        for (RentPost e:repository.findByStatusOrderByTimePostDesc(status,pageable)) {
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
    // tim phong tuong tu
 public List<RentPostReadDTO> roomTuongTu(int dictric,int roomType) {
     List<RentPostReadDTO> rentPostReadDTOS = new ArrayList<>();
     for (RentPost e:repository.roomTuongTu(dictric,roomType)) {
         rentPostReadDTOS.add(RentPostReadDTO.transtoDTO(e));
     }
     return rentPostReadDTOS;
 }


    public int count() {
        return (int) repository.count();
    }
}
