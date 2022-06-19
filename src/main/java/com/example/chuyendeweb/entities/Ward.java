package com.example.chuyendeweb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Ward implements Serializable {
    private static final long serialVersionUID = 7385741327704693623L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name ;
    String prefix;
    @ManyToOne
    @JoinColumn(name="Distric_id")
    @JsonManagedReference
    Distric distric;
}
