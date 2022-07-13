package com.example.chuyendeweb.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment implements Serializable {
    private static final long serialVersionUID = 7385741327704693623L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id ;
    @CreatedDate
    @Column(name="timePost", nullable = false)
    java.util.Date timePost = new Date();
    String comment;
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "find_post_id", nullable = false)
    @JsonManagedReference
    FindPost find_post;
    @ManyToOne
    @JoinColumn(name="user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference
    User user;



}
