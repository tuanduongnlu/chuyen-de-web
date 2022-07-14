package com.example.chuyendeweb.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class  RoomType implements Serializable {
    private static final long serialVersionUID = 7385741327704693623L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
    @JsonBackReference
    Collection<RentPost> rentPosts;
    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
    @JsonBackReference
    Collection<FindPost> findPosts ;

}
