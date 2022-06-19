package com.example.chuyendeweb.service;

import com.example.chuyendeweb.entities.RoomType;
import com.example.chuyendeweb.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {
    @Autowired
    RoomTypeRepository repository;
    public List<RoomType> roomTypes () {
        return repository.findAll();
    }
    public RoomType getById(int id) {
        return repository.findById(id);
    }
}
