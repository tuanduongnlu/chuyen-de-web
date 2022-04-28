package com.example.chuyendeweb.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RentPost {
    @Id
    @GeneratedValue
    Long id ;
    Date timePost;
    @OneToOne
            @JoinColumn(name="RoomType_id")
            RoomType roomType;
    String title;
    String detail;
    String linkImage;
    int price;
    double acreage ;
    String sex ;
    String status;
    @ManyToOne
    @JoinColumn(name="User_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    User user;

}
