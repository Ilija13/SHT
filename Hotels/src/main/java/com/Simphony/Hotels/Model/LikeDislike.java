package com.Simphony.Hotels.Model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class LikeDislike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String userId;


    @Column(name = "review")
    private Long reviewId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private LikeEnum like;
}
