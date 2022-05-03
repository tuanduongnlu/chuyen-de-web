package com.example.chuyendeweb.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RentPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id ;
    @CreatedDate
    @Column(name="timePost", nullable = false)
    java.util.Date timePost = new Date();
    @OneToOne
            @JoinColumn(name="RoomType_id")
            RoomType roomType;
    String title;
    String detail;
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
