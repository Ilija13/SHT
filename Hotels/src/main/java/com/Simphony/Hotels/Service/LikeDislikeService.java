package com.Simphony.Hotels.Service;

public interface LikeDislikeService {
    String like(Long reviewId) throws Exception;

    String dislike(Long reviewId) throws Exception;
}
