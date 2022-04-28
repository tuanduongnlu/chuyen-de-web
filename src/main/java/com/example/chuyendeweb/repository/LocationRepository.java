package com.example.chuyendeweb.repository;

import com.example.chuyendeweb.entities.location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<location,Long> {
}
