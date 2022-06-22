package com.example.chuyendeweb.repository;

import com.example.chuyendeweb.entities.Distric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistricRepository extends JpaRepository<Distric,Integer> {
    public  Distric findById (int id);
}
