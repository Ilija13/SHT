package com.Simphony.Hotels.Controller;

import com.Simphony.Hotels.Service.LikeDislikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LikeDislikeController {

    private final LikeDislikeService likeDislikeService;

    @Autowired
    public LikeDislikeController(LikeDislikeService likeDislikeService) {
        this.likeDislikeService = likeDislikeService;
    }

    @PostMapping("/review/{id}/like")
    public String like(@PathVariable("id") Long reviewId) throws Exception {
        return likeDislikeService.like(reviewId);
    }

    @PostMapping("/review/{id}/dislike")
    public String dislike(@PathVariable("id") Long reviewId) throws Exception {
        return likeDislikeService.dislike(reviewId);
    }
}
