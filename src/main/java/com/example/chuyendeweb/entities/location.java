package com.example.chuyendeweb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int distric;
    int ward;
    String detail;
    @OneToOne( mappedBy = "location")
    RentPost rentPost;
}
