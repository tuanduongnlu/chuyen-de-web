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
    Long id;
    @OneToOne
            @JoinColumn(name="Distric_id")
          Distric distric;
    @OneToOne
            @JoinColumn(name="Ward_id")
     Ward ward;
    String detail;
    @OneToOne
    @JoinColumn(name="rentPost_id")
    RentPost rentPost;
}
