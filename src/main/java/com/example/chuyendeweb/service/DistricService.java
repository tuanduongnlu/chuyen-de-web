package com.example.chuyendeweb.service;

import com.example.chuyendeweb.entities.Distric;
import com.example.chuyendeweb.repository.DistricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistricService {
@Autowired
    DistricRepository repository;
    public List<Distric> districs () {
        return repository.findAll();
    }
    public  Distric findById(int id) {
        return repository.findById(id);
    }
}
