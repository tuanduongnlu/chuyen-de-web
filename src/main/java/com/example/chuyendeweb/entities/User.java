package com.example.chuyendeweb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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
    int state;
    String role ;
}
