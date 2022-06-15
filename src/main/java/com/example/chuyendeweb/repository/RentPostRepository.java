package com.example.chuyendeweb.repository;

import com.example.chuyendeweb.entities.RentPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentPostRepository extends JpaRepository<RentPost,Integer> {
    List<RentPost> findAllByPriceBetween(int startPrice,int endPrice);
    List<RentPost> findAllByRoomType_idOrderByTimePostDesc (long RoomType_id);
    List<RentPost> findAllByOrderByPriceAsc();
    List<RentPost> findAllByOrderByPriceDesc();
    List<RentPost> findAllByOrderByTimePostAsc();
    List<RentPost> findAllByOrderByTimePostDesc();
    List<RentPost> findByStatusOrderByTimePostDesc(String status);
    RentPost findByUser_id(Long id);
    @Query(value = "select r.* from rent_post as r join location as l " +
            "on r.location_id = l.id where l.distric= :distric_id and l.ward= :ward_id" +
            " and r.room_type_id= :roomtype and r.price between :startPrice and :endPrice" +
            " and r.acreage between :startArea and :endArea ",nativeQuery = true)
    List<RentPost> search (@Param("distric_id") int distric_id,@Param("ward_id") int ward_id,
                                     @Param("roomtype") int roomtype, @Param("startPrice") int startPrice,
                                     @Param("endPrice") int endPrice, @Param("startArea") int startArea,
                                     @Param("endArea") int endArea);
    @Query(value = "select r.* from rent_post as r join location as l " +
            "on r.location_id = l.id where l.distric= :distric_id and l.ward= :ward_id" +
            "  and r.price between :startPrice and :endPrice" +
            " and r.acreage between :startArea and :endArea ",nativeQuery = true)
    List<RentPost> searchNotRoomtype (@Param("distric_id") int distric_id,@Param("ward_id") int ward_id,
                                     @Param("startPrice") int startPrice,
                                     @Param("endPrice") int endPrice, @Param("startArea") int startArea,
                                     @Param("endArea") int endArea);
    @Query(value = "select r.* from rent_post as r join location as l " +
            "on r.location_id = l.id where " +
            " r.room_type_id= :roomtype and r.price between :startPrice and :endPrice" +
            " and r.acreage between :startArea and :endArea ",nativeQuery = true)
    List<RentPost> searchNotLocation (@Param("roomtype") int roomtype, @Param("startPrice") int startPrice,
                           @Param("endPrice") int endPrice, @Param("startArea") int startArea,
                           @Param("endArea") int endArea);

    @Query(value = "select r.* from rent_post as r join location as l " +
            "on r.location_id = l.id where l.distric= :distric_id " +
            " and r.room_type_id= :roomtype and r.price between :startPrice and :endPrice" +
            " and r.acreage between :startArea and :endArea ",nativeQuery = true)
    List<RentPost> searchNotWard (@Param("distric_id") int distric_id,
                           @Param("roomtype") int roomtype, @Param("startPrice") int startPrice,
                           @Param("endPrice") int endPrice, @Param("startArea") int startArea,
                           @Param("endArea") int endArea);

    @Query(value = "select r.* from rent_post as r join location as l " +
            "on r.location_id = l.id where l.distric= :distric_id " +
            " and r.price between :startPrice and :endPrice" +
            " and r.acreage between :startArea and :endArea ",nativeQuery = true)
    List<RentPost> searchNotWardAndRoomType (@Param("distric_id") int distric_id, @Param("startPrice") int startPrice,
                           @Param("endPrice") int endPrice, @Param("startArea") int startArea,
                           @Param("endArea") int endArea);
}
