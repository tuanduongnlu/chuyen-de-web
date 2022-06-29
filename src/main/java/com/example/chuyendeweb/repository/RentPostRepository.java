package com.example.chuyendeweb.repository;

import com.example.chuyendeweb.entities.RentPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentPostRepository extends JpaRepository<RentPost,Integer> {
    RentPost findById(int id);
    List<RentPost> findAllByPriceBetween(int startPrice,int endPrice);
    List<RentPost> findAllByRoomType_idOrderByTimePostDesc (long RoomType_id);
    List<RentPost> findAllByOrderByPriceAsc(Pageable pageable);
    List<RentPost> findAllByOrderByPriceDesc(Pageable pageable);
    List<RentPost> findAllByOrderByTimePostAsc(Pageable pageable);
    List<RentPost> findAllByOrderByTimePostDesc(Pageable pageable);
    List<RentPost> findByStatusOrderByTimePostDesc(String status,Pageable pageable);
    RentPost findByUser_id(Long id);
    @Query(value = "select r.* from rent_post as r join location as l " +
            "on r.location_id = l.id where l.dictric_id= :distric_id and l.ward_id= :ward_id" +
            " and r.room_type_id= :roomtype and r.price between :startPrice and :endPrice" +
            " and r.acreage between :startArea and :endArea order by r.time_post DESC ",nativeQuery = true)
    List<RentPost> search (@Param("distric_id") int distric_id,@Param("ward_id") int ward_id,
                                     @Param("roomtype") int roomtype, @Param("startPrice") int startPrice,
                                     @Param("endPrice") int endPrice, @Param("startArea") int startArea,
                                     @Param("endArea") int endArea);
    @Query(value = "select r.* from rent_post as r join location as l " +
            "on r.location_id = l.id where l.dictric_id= :distric_id and l.ward_id= :ward_id" +
            "  and r.price between :startPrice and :endPrice" +
            " and r.acreage between :startArea and :endArea order by r.time_post DESC",nativeQuery = true)
    List<RentPost> searchNotRoomtype (@Param("distric_id") int distric_id,@Param("ward_id") int ward_id,
                                     @Param("startPrice") int startPrice,
                                     @Param("endPrice") int endPrice, @Param("startArea") int startArea,
                                     @Param("endArea") int endArea);
    @Query(value = "select r.* from rent_post as r join location as l " +
            "on r.location_id = l.id where " +
            " r.room_type_id= :roomtype and r.price between :startPrice and :endPrice" +
            " and r.acreage between :startArea and :endArea order by r.time_post DESC ",nativeQuery = true)
    List<RentPost> searchNotLocation (@Param("roomtype") int roomtype, @Param("startPrice") int startPrice,
                           @Param("endPrice") int endPrice, @Param("startArea") int startArea,
                           @Param("endArea") int endArea);

    @Query(value = "select r.* from rent_post as r join location as l " +
            "on r.location_id = l.id where l.dictric_id= :distric_id " +
            " and r.room_type_id= :roomtype and r.price between :startPrice and :endPrice" +
            " and r.acreage between :startArea and :endArea order by r.time_post DESC ",nativeQuery = true)
    List<RentPost> searchNotWard (@Param("distric_id") int distric_id,
                           @Param("roomtype") int roomtype, @Param("startPrice") int startPrice,
                           @Param("endPrice") int endPrice, @Param("startArea") int startArea,
                           @Param("endArea") int endArea);

    @Query(value = "select r.* from rent_post as r join location as l " +
            "on r.location_id = l.id where l.dictric_id= :distric_id " +
            " and r.price between :startPrice and :endPrice" +
            " and r.acreage between :startArea and :endArea order by r.time_post DESC",nativeQuery = true)
    List<RentPost> searchNotWardAndRoomType (@Param("distric_id") int distric_id, @Param("startPrice") int startPrice,
                           @Param("endPrice") int endPrice, @Param("startArea") int startArea,
                           @Param("endArea") int endArea);
    @Query(value = "select r.* from rent_post as r join location as l " +
            "on r.location_id = l.id where l.dictric_id= :distric_id " +
            " and r.room_type_id= :roomtype order by r.time_post DESC",nativeQuery = true)
    List<RentPost> roomTuongTu (@Param("distric_id") int distric_id, @Param("roomtype") int roomType);
}
