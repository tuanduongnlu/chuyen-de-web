package com.example.chuyendeweb.repository;

import com.example.chuyendeweb.entities.RentPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentPostRepository extends JpaRepository<RentPost,Long> {
    List<RentPost> findAllByPriceBetween(int startPrice,int endPrice);
    List<RentPost> findAllByRoomType_idOrderByTimePostDesc (long RoomType_id);
    List<RentPost> findAllByOrderByPriceAsc();
    List<RentPost> findAllByOrderByPriceDesc();
    List<RentPost> findAllByOrderByTimePostAsc();
    List<RentPost> findAllByOrderByTimePostDesc();
    List<RentPost> findByStatusOrderByTimePostDesc(String status);

}
