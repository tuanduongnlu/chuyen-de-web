package com.example.chuyendeweb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;
    String name;
    String password;
    @Column(name = "phone", unique = true, nullable = false, length = 10)
    String phone;
    String email;
    Date create_date;
    boolean state;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Collection<RentPost> rentPosts;
}
