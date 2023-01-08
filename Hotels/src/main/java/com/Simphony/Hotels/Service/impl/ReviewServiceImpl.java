package com.Simphony.Hotels.Service.impl;

import com.Simphony.Hotels.Model.Hotel;
import com.Simphony.Hotels.Model.LikeEnum;
import com.Simphony.Hotels.Model.Review;
import com.Simphony.Hotels.Model.UserEntity;
import com.Simphony.Hotels.Repository.HotelRepository;
import com.Simphony.Hotels.Repository.LikeDislikeRepository;
import com.Simphony.Hotels.Repository.ReviewRepository;
import com.Simphony.Hotels.Repository.UserRepository;
import com.Simphony.Hotels.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final HotelRepository hotelRepository;
    private final LikeDislikeRepository likeDislikeRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, HotelRepository hotelRepository, LikeDislikeRepository likeDislikeRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.hotelRepository = hotelRepository;
        this.likeDislikeRepository = likeDislikeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Review createReview(Long hotelId, Review review) {

        //review.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getDisplayname();
        review.setAuthor(name);

        Hotel hotel = hotelRepository.findById(hotelId).get();
        review.setHotel(hotel);
        reviewRepository.save(review);
        double rating = reviewRepository.averageRating(hotelId);

        hotel.setRating(rating);
        hotelRepository.save(hotel);
        return review;
    }

    @Override
    public List<Review> getReviews(Long id) throws Exception {
        if (reviewRepository.findAllByHotelId(id).isEmpty()) {
            throw new Exception("THERE ARE NO REVIEWS FOR THIS HOTEL");
        }
        return reviewRepository.findAllByHotelId(id);
    }

    @Override
    public List<String> getLikes(Long reviewId) throws Exception {
        if (likeDislikeRepository.findByReviewId(reviewId, LikeEnum.LIKE).isEmpty()) {
            throw new Exception("THERE ARE NO LIKES FOR THIS REVIEW");

        }
        return likeDislikeRepository.findByReviewId(reviewId, LikeEnum.LIKE);

    }

    @Override
    public List<String> getDislikes(Long reviewId) throws Exception {
        if (likeDislikeRepository.findByReviewId(reviewId, LikeEnum.DISLIKE).isEmpty()) {
            throw new Exception("THERE ARE NO DISLIKES FOR THIS REVIEW");

        }
        return likeDislikeRepository.findByReviewId(reviewId, LikeEnum.DISLIKE);
    }
}
