package com.Simphony.Hotels.Service;

import com.Simphony.Hotels.Model.Review;

import java.util.List;

public interface ReviewService {

    Review createReview(Long hotelId, Review review);

    List<Review> getReviews(Long id) throws Exception;

    List<String> getLikes(Long reviewId) throws Exception;

    List<String> getDislikes(Long reviewId) throws Exception;
}
