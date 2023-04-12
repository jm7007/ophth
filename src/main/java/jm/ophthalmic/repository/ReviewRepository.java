package jm.ophthalmic.repository;

import java.util.List;
import java.util.Optional;

import jm.ophthalmic.domain.Review;

public interface ReviewRepository {
    
    Review save(Review review);
    Optional<Review> findbyId(Long review_id);
    List<Review> findbyUserId(Long user_id);
    List<Review> findAll();
    Long delete(Long review_id);
    Optional<Review> modify(Review review, Review updates);
}
