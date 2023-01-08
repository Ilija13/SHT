package com.Simphony.Hotels.Controller;

import com.Simphony.Hotels.Model.Review;
import com.Simphony.Hotels.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ControllerAdvice
@RestController
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/review/{hotelId}")
    public ResponseEntity<Review> createReview(@PathVariable("hotelId") Long hotelId,
                                               @RequestBody Review review) {

        return new ResponseEntity<>(reviewService.createReview(hotelId, review), HttpStatus.CREATED);
    }


    @GetMapping("/user/review/hotel/{id}")
    public List<Review> hotelReviews(@PathVariable("id") Long id) throws Exception {
        return reviewService.getReviews(id);
    }

    @GetMapping("/user/review/likes/{id}")
    public List<String> getLikes(@PathVariable("id") Long reviewId) throws Exception {
        return reviewService.getLikes(reviewId);
    }


    @GetMapping("/user/review/dislikes/{id}")
    public List<String> getDislikes(@PathVariable("id") Long reviewId) throws Exception {
        return reviewService.getDislikes(reviewId);
    }

}
