package com.example.chuyendeweb.repository;

import com.example.chuyendeweb.entities.RentPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentPostRepository extends JpaRepository<RentPost,Long> {
    List<RentPost> findAllByPriceBetween(int startPrice,int endPrice);
    List<RentPost> findAllByRoomType_idOrderByTimePostDesc (long RoomType_id);
    List<RentPost> findAllByOrderByPriceAsc();
    List<RentPost> findAllByOrderByPriceDesc();
    List<RentPost> findAllByOrderByTimePostAsc();
    List<RentPost> findAllByOrderByTimePostDesc();
    List<RentPost> findByStatusOrderByTimePostDesc(String status);
    RentPost findByUser_id(Long id);
    @Query(value = "select r.* from rent_post as r join location as l " +
            "on r.id = l.rent_post_id where l.distric_id= :distric_id and l.ward_id= :ward_id" +
            " and l.detail like %:detail% ",nativeQuery = true)
    List<RentPost> searchByLocation (@Param("distric_id") long distric_id,@Param("ward_id") long ward_id,
                                     @Param("detail") String detail);

}
