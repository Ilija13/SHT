package com.Simphony.Hotels.Model;

import com.Simphony.Hotels.HotelsApplication;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonView(HotelsApplication.View.FavoritesBack.class)

public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String address;

    private String description;

    @Column(unique = true, nullable = false)
    private String geolocation;

    @JsonProperty(access = READ_ONLY)
    private double rating = 0;


    @JsonManagedReference(value = "reviews")
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();


}
