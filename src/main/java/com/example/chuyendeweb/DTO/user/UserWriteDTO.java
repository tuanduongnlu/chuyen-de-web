package com.example.chuyendeweb.DTO.user;

import com.example.chuyendeweb.entities.Role;
import com.example.chuyendeweb.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWriteDTO {
    private String name ;
    private String phone;
    private String email;
    private String password;

    public  User transUser() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setState(true);
        user.setPhone(this.phone);
        user.setCreate_date(new Date());
        user.getRoles().add(new Role(1l,"user"));
        return user;
    }
    public static UserWriteDTO transtoDTO(User user){
        return new UserWriteDTO(user.getName(), user.getPhone(), user.getEmail(),user.getPassword());
    }
}
