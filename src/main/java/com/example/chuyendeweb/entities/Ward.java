package com.example.chuyendeweb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name ;
    String prefix;
    @ManyToOne
    @JoinColumn(name="Distric_id")
    Distric distric;
}
