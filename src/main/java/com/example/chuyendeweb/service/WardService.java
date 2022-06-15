package com.example.chuyendeweb.service;

import com.example.chuyendeweb.entities.Ward;
import com.example.chuyendeweb.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardService {
    @Autowired
    WardRepository repository;
    public List<Ward> wards() {
        return repository.findAll();
    }
    public List<Ward> getWardsByDictric_id(int id) {
        return repository.findAllByDistric_id(id);
    }
}
