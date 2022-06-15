package com.example.chuyendeweb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
    Collection<RentPost> rentPosts;
}
