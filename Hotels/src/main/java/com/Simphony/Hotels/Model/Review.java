package com.Simphony.Hotels.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity


public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = READ_ONLY)
    private String author;

    @Range(min = 1, max = 10, message = "The rating is from 1 to 10")
    private double rating;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @JsonBackReference(value = "reviews")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

}
