package com.example.chuyendeweb.DTO.rentPost;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListRentPost {
    private List<RentPostReadDTO> list ;
    private int totalPage ;
    private int currentPage ;
}
