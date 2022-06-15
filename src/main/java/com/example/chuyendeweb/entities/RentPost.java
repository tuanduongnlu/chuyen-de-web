package com.example.chuyendeweb.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RentPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id ;
    @CreatedDate
    @Column(name="timePost", nullable = false)
    java.util.Date timePost = new Date();
    @ManyToOne
    @JoinColumn(name = "roomType_id")
    RoomType roomType;
    String title;
    String detail;
    int price;
    double acreage ;
    @OneToMany(mappedBy = "rentpost", cascade = CascadeType.ALL)
    Collection<Image> images;
    String sex ;
    String status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    location location;
    @ManyToOne
    @JoinColumn(name="User_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    User user;


}
