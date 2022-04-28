package com.example.chuyendeweb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
   // @Size(min = 3, max = 25, message = "Tên phải từ 3 đến 25 ký tự")
    String name;
    String password;
    //@Column(name = "phone", unique = true, nullable = false, length = 10)
    //@Size( min = 10, max=10,message = "Số điện thoại phải 10 chữ số")
    String phone;
    String email;
    String state;
    String role ;
}
