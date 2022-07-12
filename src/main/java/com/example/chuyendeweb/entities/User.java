package com.example.chuyendeweb.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private static final long serialVersionUID = 7385741327704693623L;
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
    @Column(name = "zalo", unique = true, length = 10)
    String zalo;
    String facebook;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    Collection<RentPost> rentPosts;

}
