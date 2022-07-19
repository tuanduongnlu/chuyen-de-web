package com.example.chuyendeweb.DTO.user;

import com.example.chuyendeweb.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReadDTO {
    private long id;
    private String name ;
    private String phone;
    private String email;
    private String zalo;
    private String facebook;
    private String date;
    private boolean state;
    public static UserReadDTO transtoDTO(User user){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return new UserReadDTO(user.getId(),user.getName(), user.getPhone(), user.getEmail(),
                user.getZalo(), user.getFacebook(),formatter.format(user.getCreate_date()), user.isState());
    }
}
