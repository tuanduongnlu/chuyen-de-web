package com.example.chuyendeweb.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FindPost implements Serializable {
    private static final long serialVersionUID = 7385741327704693623L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id ;
    @CreatedDate
    @Column(name="timePost", nullable = false)
    java.util.Date timePost = new Date();
    String title;
    String detail;
    @ManyToOne
    @JoinColumn(name = "roomType_id")
    @JsonManagedReference
    RoomType roomType;
    @OneToMany(mappedBy = "find_post", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonBackReference
    List<Comment> comments;
    @ManyToOne
    @JoinColumn(name="user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference
    User user;
}
