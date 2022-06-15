package com.example.chuyendeweb.repository;

import com.example.chuyendeweb.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward,Integer> {
    public List<Ward> findAllByDistric_id(int id);
}
