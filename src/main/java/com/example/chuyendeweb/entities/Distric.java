package com.example.chuyendeweb.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Distric implements Serializable {
    private static final long serialVersionUID = 7385741327704693623L ;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    String prefix;
    @OneToMany( mappedBy = "distric",cascade = CascadeType.ALL)
    @JsonBackReference
    List<Location> locations;


}
