package com.Simphony.Hotels.Repository;

import com.Simphony.Hotels.Model.LikeDislike;
import com.Simphony.Hotels.Model.LikeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeDislikeRepository extends JpaRepository<LikeDislike, Long> {

    boolean existsByUserIdAndReviewId(String userId, Long reviewId);

    LikeDislike findLikeDislikeByUserIdAndReviewId(String userId, Long reviewId);


    @Query("select l.userId from LikeDislike l where l.reviewId = ?1 and l.like=?2")
    List<String> findByReviewId(Long reviewId, LikeEnum likeEnum);
}
