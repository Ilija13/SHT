package com.Simphony.Hotels.Service.impl;

import com.Simphony.Hotels.Model.LikeDislike;
import com.Simphony.Hotels.Model.LikeEnum;
import com.Simphony.Hotels.Model.UserEntity;
import com.Simphony.Hotels.Repository.LikeDislikeRepository;
import com.Simphony.Hotels.Repository.ReviewRepository;
import com.Simphony.Hotels.Service.LikeDislikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LikeDislikeServiceImpl implements LikeDislikeService {

    @Autowired
    private LikeDislikeRepository likeDislikeRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public String like(Long reviewId) throws Exception {
        if (!reviewRepository.findById(reviewId).isPresent()) {
            throw new Exception("THE REVIEW DOES NOT EXIST");
        }
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (likeDislikeRepository.existsByUserIdAndReviewId(user.getDisplayname(), reviewId)) {
            if (likeDislikeRepository.findLikeDislikeByUserIdAndReviewId(user.getDisplayname(), reviewId).getLike().equals(
                    LikeEnum.LIKE)) {
                likeDislikeRepository.deleteById(
                        likeDislikeRepository.findLikeDislikeByUserIdAndReviewId(user.getDisplayname(), reviewId).getId());
                return "YOU JUST REMOVED YOUR LIKE FROM THIS REVIEW";
            }
            if (likeDislikeRepository.findLikeDislikeByUserIdAndReviewId(user.getDisplayname(), reviewId).getLike().equals(
                    LikeEnum.DISLIKE)) {
                LikeDislike like = likeDislikeRepository.findLikeDislikeByUserIdAndReviewId(user.getDisplayname(), reviewId);
                like.setLike(LikeEnum.LIKE);
                likeDislikeRepository.save(like);
                return "YOU JUST TURNED YOUR DISLIKE TO LIKE";
            }

        }
        LikeDislike like = new LikeDislike();
        like.setLike(LikeEnum.LIKE);
        like.setReviewId(reviewRepository.findById(reviewId).get().getId());
        like.setUserId(user.getDisplayname());
        likeDislikeRepository.save(like);
        return "YOU JUST LIKED THIS REVIEW";
    }

    @Override
    public String dislike(Long reviewId) throws Exception {

        if (!reviewRepository.findById(reviewId).isPresent()) {
            throw new Exception("THE REVIEW DOES NOT EXIST");
        }
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (likeDislikeRepository.existsByUserIdAndReviewId(user.getDisplayname(), reviewId)) {
            if (likeDislikeRepository.findLikeDislikeByUserIdAndReviewId(user.getDisplayname(), reviewId).getLike().equals(
                    LikeEnum.DISLIKE)) {
                likeDislikeRepository.deleteById(
                        likeDislikeRepository.findLikeDislikeByUserIdAndReviewId(user.getDisplayname(), reviewId).getId());
                return "YOU JUST REMOVED YOUR LIKE FROM THIS REVIEW";
            }
            if (likeDislikeRepository.findLikeDislikeByUserIdAndReviewId(user.getDisplayname(), reviewId).getLike().equals(
                    LikeEnum.LIKE)) {

                LikeDislike like = likeDislikeRepository.findLikeDislikeByUserIdAndReviewId(user.getDisplayname(), reviewId);
                like.setLike(LikeEnum.DISLIKE);
                likeDislikeRepository.save(like);
                return "YOU JUST TURNED YOUR LIKE TO DISLIKE";
            }

        }

        LikeDislike like = new LikeDislike();
        like.setLike(LikeEnum.DISLIKE);
        like.setReviewId(reviewRepository.findById(reviewId).get().getId());
        like.setUserId(user.getDisplayname());
        likeDislikeRepository.save(like);
        return "YOU JUST DISLIKED THIS REVIEW";
    }
}
