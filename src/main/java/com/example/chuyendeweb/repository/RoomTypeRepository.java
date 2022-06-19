package com.example.chuyendeweb.repository;

import com.example.chuyendeweb.entities.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType,Integer> {
    public RoomType findById (int id);
}
