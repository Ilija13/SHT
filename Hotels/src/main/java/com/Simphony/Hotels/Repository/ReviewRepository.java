package com.Simphony.Hotels.Repository;

import com.Simphony.Hotels.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.hotel.id = ?1")
    double averageRating(Long hotelId);

    List<Review> findAllByHotelId(Long id);
}
