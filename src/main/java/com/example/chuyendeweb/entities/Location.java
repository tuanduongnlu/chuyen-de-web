package com.example.chuyendeweb.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Location implements Serializable {
    private static final long serialVersionUID = 7385741327704693623L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "dictric_id", referencedColumnName = "id")
    @JsonManagedReference
    Distric distric;
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "ward_id", referencedColumnName = "id")
    @JsonManagedReference
    Ward ward;
    String detail;
    @OneToOne( mappedBy = "location")
    @JsonBackReference
    RentPost rentPost;
}
