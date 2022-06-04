package com.example.chuyendeweb.DTO.user;

import com.example.chuyendeweb.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String name ;
    private String phone;
    private String email;
    private String password;

    public User transUser() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setState(1);
        user.setPhone(this.phone);
        user.setCreate_date(new Date());
        user.setRole("user");
        return user;
    }
}
