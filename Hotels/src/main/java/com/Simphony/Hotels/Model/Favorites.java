package com.Simphony.Hotels.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;


    @JoinColumn(name = "idHotel")
    @ManyToOne
    private Hotel hotel;

}
